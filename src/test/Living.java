package test;

import gameEngine.*;
import processing.core.PVector;
import processing.event.MouseEvent;

/**
 * Created by roscale on 4/20/17.
 */
abstract class Living extends GameObject implements ICollider, IInput
{
	public Living()
	{
		addComponent(SpriteRenderer.class).setFrameRate(15);
		getComponent(SpriteRenderer.class).repeating(true);
		addComponent(Input.class);
		addComponent(Collider.class);
	}

	// gameEngine.IInput
	@Override
	public void mouseDragged(MouseEvent event)
	{
		Transform transform = getComponent(Transform.class);
		transform.setPosition(PVector.add(transform.getPosition(), Helper.getRelativeMousePos(World.p)));
	}
}