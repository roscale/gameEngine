package gameEngine;

import java.util.ArrayList;

/**
 * Created by roscale on 4/30/17.
 */
public class CollisionManager extends ComponentManager
{
	private static CollisionManager instance;
	public static CollisionManager instance()
	{
		if (instance == null)
			instance = new CollisionManager();
		return instance;
	}

	//////////////////////////////////////////////////////////////

	public ArrayList<Collider> colliders = new ArrayList<>();
	public ArrayList<Pair> activeCollisions = new ArrayList<>();

	@Override
	public boolean containsComponent(Component component)
	{
		return colliders.contains(component);
	}

	@Override
	public void addComponent(Component component)
	{
		colliders.add((Collider) component);
	}

	@Override
	public void removeComponent(Component component)
	{
		Collider col = (Collider) component;

		colliders.remove(col);

		int i = 0;
		while (i < activeCollisions.size())
		{
			Pair pair = activeCollisions.get(i);
			if (pair.contains(col))
			{
				activeCollisions.remove(pair);
				pair.getOther(col).collisions.remove(col.gameObject);
			}
			else
				i++;
		}

		// Update gameobjects already in collision
		for (GameObject obj : col.collisions)
		{
			ICollider callback = (ICollider) obj;
			callback.onCollisionExitWith(col.gameObject);
		}
	}

	@Override
	public void updateComponents() // Detect collisions
	{
		// Go until second to last
		for (int icurrent = 0; icurrent < colliders.size()-1; icurrent++)
			for (int iother = icurrent+1; iother < colliders.size(); iother++)
			{
				Pair pair = new Pair(colliders.get(icurrent), colliders.get(iother));

				ICollider firstObj = (ICollider) pair.first.gameObject;
				ICollider secondObj = (ICollider) pair.second.gameObject;

				if (Helper.doCollide(pair.first.getPosition(), pair.first.getSize(),
						pair.second.getPosition(), pair.second.getSize()))
				{
					if (!activeCollisions.contains(pair))
					{
						activeCollisions.add(pair);

						// Retain collision info per collider
						pair.first.collisions.add(pair.second.gameObject);
						pair.second.collisions.add(pair.first.gameObject);

						firstObj.onCollisionEnterWith((GameObject) secondObj);
						secondObj.onCollisionEnterWith((GameObject) firstObj);
					}
					else
					{
						firstObj.onCollisionStayWith((GameObject) secondObj);
						secondObj.onCollisionStayWith((GameObject) firstObj);
					}
				}
				else if (activeCollisions.contains(pair))
				{
					activeCollisions.remove(pair);

					pair.first.collisions.remove(pair.second.gameObject);
					pair.second.collisions.remove(pair.first.gameObject);

					firstObj.onCollisionExitWith((GameObject) secondObj);
					secondObj.onCollisionExitWith((GameObject) firstObj);
				}
			}
	}

	@Override
	public void debug()
	{
		for (Collider col : colliders)
			col.debug();
	}
}

class Pair
{
	public Collider first;
	public Collider second;

	public Pair(Collider first, Collider second)
	{
		this.first = first;
		this.second = second;
	}

	public boolean contains(Collider col) { return first == col || second == col; }

	public Collider getOther(Collider col)
	{
		if (first == col)
			return second;
		if (second == col)
			return first;
		return null;
	}

	@Override
	public boolean equals(Object other)
	{
		Pair p = (Pair) other;
		if (p == null)
			return false;

		return (first == p.first && second == p.second) ||
				(first == p.second && second == p.first);
	}
}

//	public ArrayList<Collider> refresh()
//	{
//		colliders = new ArrayList<>();
//
//		for (int i = 0; i < World.list.size(); i++)
//		{
//			Collider col;
//			if ((col = World.list.get(i).getComponent(Collider.class)) != null)
//				colliders.addComponent(col);
//		}
//		return colliders;
//	}