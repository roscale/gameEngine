package gameEngine;

import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by roscale on 4/20/17.
 */
public abstract class Component extends Object implements Comparable<Component>
{
	public GameObject gameObject;
	public ArrayList<ComponentManager> managers = new ArrayList<>();
	public PVector offset = new PVector();
	public boolean enabled = true;
	public Class c = getClass();

	Component(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}

	public PVector getPosition() { return PVector.add(gameObject.transform.getPosition(), offset); }

	public <T extends Component> T setOffset(PVector offset) { return setOffset(offset.x, offset.y); }
	public <T extends Component> T setOffset(float x, float y) { this.offset.set(x, y); return (T) this; }

	public void addToManager(ComponentManager manager)
	{
		manager.addComponent(this);
		managers.add(manager);
	}

	public void enable()
	{
		if (enabled)
			return;

		enabled = true;
		for (int i = 0; i < managers.size(); i++)
			managers.get(i).addComponent(this);
	}

	public void disable()
	{
		if (!enabled)
			return;

		enabled = false;
		for (int i = 0; i < managers.size(); i++)
			managers.get(i).removeComponent(this);
	}

	@Override
	public void destroy()
	{
		disable();
		gameObject.components.remove(this);
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
