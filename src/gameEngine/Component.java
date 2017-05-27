package gameEngine;

import java.util.ArrayList;

/**
 * Created by roscale on 4/20/17.
 */
public abstract class Component implements Comparable<Component>
{
	public GameObject gameObject;
	public ArrayList<ComponentManager> managers = new ArrayList<>();

	public boolean enabled = true;

	Component(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}

	public void addToManager(ComponentManager manager)
	{
		manager.addComponent(this);
		managers.add(manager);
	}

	public void enable()
	{
		if (!enabled)
		{
			enabled = true;

			for (int i = 0; i < managers.size(); i++)
				managers.get(i).addComponent(this);
		}
	}

	public void disable()
	{
		if (enabled)
		{
			enabled = false;

			for (int i = 0; i < managers.size(); i++)
				managers.get(i).removeComponent(this);
		}
	}

	public abstract void debug();

	@Override
	public int compareTo(Component other)
	{
		float diff = this.gameObject.transform.getPosition().z - other.gameObject.transform.getPosition().z;
		if (diff > 0) return 1;
		if (diff < 0) return -1;
		return 0;
	}
}
