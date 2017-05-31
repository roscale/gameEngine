package gameEngine.util;

/**
 * Created by roscale on 5/3/17.
 */
public class Counter
{
	private int max;
	private int value = 0;
	private boolean wrapAround = true;

	public Counter(int max) { this.max = max; }

	public void wrapAround(boolean wrapAround) { this.wrapAround = wrapAround; }

	public void increment()
	{
		if (!last())
			value++;
		else
			if (wrapAround)
				reset();
	}

	public int max() { return max; }
	public int value() { return value; }
	public boolean last() { return value == max-1; }

	public void reset() { value = 0; }
}
