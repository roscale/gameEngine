package gameEngine.gameObjects;

import gameEngine.GameObject;
import gameEngine.Object;
import gameEngine.util.Sprite;
import gameEngine.components.Physics;
import gameEngine.components.SpriteRenderer;

/**
 * Created by roscale on 5/25/17.
 */
public class Particle extends GameObject
{
	public Particle(Sprite sprite, float fps, boolean repeat, long lifetime)
	{
		addComponent(SpriteRenderer.class).setSprite(sprite);
		getComponent(SpriteRenderer.class).setFrameRate(fps);
		getComponent(SpriteRenderer.class).repeating(repeat);
		addComponent(Physics.class);

		Object.destroy(this, lifetime);
	}
}
