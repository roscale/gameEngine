package gameEngine;

import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by roscale on 4/21/17.
 */

public class Collider extends Component
{
	public PVector relPos = new PVector(0, 0);
	public PVector size = new PVector(0, 0);

	public ArrayList<GameObject> collisions = new ArrayList<>();

	public Collider(GameObject gameObject)
	{
		super(gameObject);
		addToManager(CollisionManager.instance());
	}

	public PVector getPosition() { return PVector.add(gameObject.transform.getPosition(), relPos); }
	public PVector getSize() { return size; }

	public void setRelativePosition(PVector newPos) { relPos.set(newPos); }
	public void setSize(PVector newSize) { size.set(newSize); }

	public boolean containsPoint(PVector point) { return Helper.containsPoint(getPosition(), getSize(), point); }

	public ArrayList<GameObject> getCollidingGameObjects() { return collisions; }
	public boolean isColliding() { return !collisions.isEmpty(); }
	public boolean isCollidingWith(GameObject other) { return collisions.contains(other); }
	public boolean isCollidingWithClass(Class<? extends GameObject> c)
	{
		for (GameObject gameObject : collisions)
			if (c.isAssignableFrom(gameObject.getClass()))
				return true;
		return false;
	}

	@Override
	public void debug()
	{
		// Show
		Helper.drawDebuggingBox(World.p, getPosition(), getSize(), new PVector(255, 0, 0), false);

		// Print
		System.out.println(Helper.colorify("[" + getClass().getSimpleName() + "]", Helper.Colors.FG_BLUE, null));
		System.out.println("Size: " + getSize());
		System.out.println("Status: " + (isColliding() ? "colliding" : "NOT colliding"));
	}
}