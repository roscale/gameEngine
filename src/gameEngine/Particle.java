package gameEngine;

/**
 * Created by roscale on 5/25/17.
 */
public class Particle extends GameObject implements Runnable
{
	private long timespan;

	public Particle(Sprite sprite, float fps, boolean repeat, long timespan)
	{
		addComponent(SpriteRenderer.class).setSprite(sprite);
		getComponent(SpriteRenderer.class).setFrameRate(fps);
		getComponent(SpriteRenderer.class).repeating(repeat);
		addComponent(Physics.class);

		this.timespan = timespan;
		new Thread(this).start();
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(timespan);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		destroy();
	}
}
