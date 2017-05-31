package test;

import gameEngine.components.Collider;
import gameEngine.GameObject;
import gameEngine.components.callbacks.ICollider;
import processing.core.PVector;

/**
 * Created by roscale on 5/3/17.
 */
public class Cell extends GameObject implements ICollider
{
	public Peashooter plant = null;

	public Cell(float x, float y, float w, float h)
	{
		addComponent(Collider.class).setSize(new PVector(w, h));
//		addComponent(Collider.class);
		transform.setPosition(x, y);
	}

	boolean isEmpty() { return plant == null; }

	@Override
	public void onCollisionEnterWith(GameObject other)
	{
		destroy();
	}
}
