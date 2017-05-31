package test;

import gameEngine.components.*;
import gameEngine.GameObject;
import processing.core.PVector;
import processing.event.MouseEvent;

/**
 * Created by roscale on 4/20/17.
 */
class Zombie extends Living
{
	public Zombie()
	{
		addComponent(Physics.class);
		getComponent(Physics.class).velocity.set(new PVector(-0.5f, 0));

		getComponent(Transform.class).setZ(1);
		getComponent(SpriteRenderer.class).setSprite(Globals.zombieSprite);
		getComponent(SpriteRenderer.class).setFrameRate(60);

//		getComponent(Collider.class).setRelativePosition(new PVector(90, 15));
		getComponent(Collider.class).setSize(new PVector(50, 135));

//		getComponent(Collider.class).setSize(getComponent(SpriteRenderer.class).getSpriteSize());
		getComponent(Input.class).setOffset(new PVector(120, 20));
		getComponent(Input.class).setSize(new PVector(20, 20));
	}

	@Override
	public void update()
	{

	}

	@Override
	public void onCollisionEnterWith(GameObject other)
	{
		if (getComponent(SpriteRenderer.class).sprite != Globals.zombieAttackSprite &&
				getComponent(Collider.class).isCollidingWithClass(Peashooter.class))
			getComponent(SpriteRenderer.class).setSprite(Globals.zombieAttackSprite);

//		destroy();
	}

	@Override
	public void onCollisionStayWith(GameObject other)
	{

	}

	@Override
	public void onCollisionExitWith(GameObject other)
	{
		if (getComponent(SpriteRenderer.class).sprite == Globals.zombieAttackSprite && !getComponent(Collider.class).isCollidingWithClass(Peashooter.class))
			getComponent(SpriteRenderer.class).setSprite(Globals.zombieSprite);
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
//		destroy();
	}
}
