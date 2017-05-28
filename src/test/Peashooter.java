package test;

import gameEngine.*;
import processing.core.PVector;

/**
 * Created by roscale on 4/22/17.
 */
class Peashooter extends Living
{
	Peashooter()
	{
		getComponent(Transform.class).setZ(-1);
		getComponent(SpriteRenderer.class).setSprite(Globals.peashooterSprite);
//		getComponent(SpriteRenderer.class).setFrameRate(10);
		getComponent(SpriteRenderer.class).setOffset(-100, -100);
		getComponent(Collider.class).setSize(getComponent(SpriteRenderer.class).getSpriteSize());
		getComponent(Input.class).setSize(getComponent(SpriteRenderer.class).getSpriteSize());

		addComponent(Physics.class);
	}

	@Override
	public void update()
	{
		PVector center = Helper.centerOf(getComponent(Transform.class).getPosition(), getComponent(SpriteRenderer.class).getSpriteSize());

		if (!Raycast.cast(center, new PVector(1, 0), 20, new ZombieRaycastCondition()).isEmpty())
			 getComponent(SpriteRenderer.class).nextFrame();
	}

	class ZombieRaycastCondition implements IRaycastCondition
	{
		@Override
		public boolean eval(GameObject obj)
		{
			if (obj.getClass() == Zombie.class)
				return true;
			return false;
		}
	}

	@Override
	public void onCollisionExitWith(GameObject other)
	{
		System.out.println(other);
	}
}
