package gameEngine.components;

import gameEngine.*;
import gameEngine.GameObject;
import gameEngine.components.callbacks.IDrawable;
import gameEngine.managers.DrawingManager;
import gameEngine.managers.SpriteManager;
import gameEngine.util.Counter;
import gameEngine.util.Helper;
import gameEngine.util.Sprite;
import gameEngine.util.Timer;
import processing.core.PVector;

/**
 * Created by roscale on 4/20/17.
 */
public class SpriteRenderer extends Component implements IDrawable
{
	public Sprite sprite;
	Timer frameRateTimer = null;
	Counter frameCounter = null;
	boolean repeat = true;

	public SpriteRenderer(GameObject gameObject)
	{
		super(gameObject);
		addToManager(DrawingManager.instance());
		addToManager(SpriteManager.instance());
	}

	public void setSprite(Sprite sprite)
	{
		if (sprite == this.sprite)
			return;

		this.sprite = sprite;

		if (frameRateTimer == null)
			setFrameRate(60);

		frameCounter = new Counter(sprite.getFrameCount());
		frameCounter.wrapAround(repeat);
	}

	public PVector getSpriteSize() { return sprite.frameSize; }

	public float getFrameRate() { return frameRateTimer.fps(); }
	public void setFrameRate(float fps) { frameRateTimer = new Timer(fps); }

	public void repeating(boolean repeat)
	{
		this.repeat = repeat;
		if (frameCounter != null)
			frameCounter.wrapAround(repeat);
	}

	public void nextFrame() { frameCounter.increment(); }

	public boolean lastFrame() { return frameCounter.last(); }

	public void step()
	{
		if (frameRateTimer == null)
			return;

		if (frameRateTimer.isTime())
			nextFrame();
	}

	@Override
	public void draw()
	{
		if (sprite == null)
			return;

		PVector pos = getPosition();
		World.p.image(sprite.getFrameAt(frameCounter.value()), pos.x, pos.y);
	}

	@Override
	public void debug()
	{
		// Show
		Helper.drawDebuggingBox(World.p, getPosition(), sprite.frameSize, new PVector(0, 0, 0), false);

		// Print
		System.out.println(Helper.colorify("[" + getClass().getSimpleName() + "]", Helper.Colors.FG_BLUE, null));
		if (sprite != null)
			sprite.debugPrint();
		System.out.println("Current frame: " + frameCounter.value());
	}
}