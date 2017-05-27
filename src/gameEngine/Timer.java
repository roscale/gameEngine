package gameEngine;

/**
 * Created by roscale on 5/27/17.
 */
public class Timer
{
	private float fps;
	private float interval;
	private long previous = System.currentTimeMillis();

	public Timer(float fps)
	{
		this.fps = fps;
		this.interval = 1000f / fps;
	}

	public boolean isTime()
	{
		if (System.currentTimeMillis() - previous >= interval)
		{
			reset();
			return true;
		}
		return false;
	}

	public void reset() { previous = System.currentTimeMillis(); }

	public float fps() { return fps; }
}
