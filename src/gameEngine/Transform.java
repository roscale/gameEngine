package gameEngine;

import processing.core.PVector;

/**
 * Created by roscale on 4/20/17.
 */
public class Transform extends Component
{
	public Transform(GameObject gameObject) { super(gameObject); }

	@Override
	public PVector getPosition() { return offset; }
	public Transform setPosition(PVector pos) { return setPosition(pos.x, pos.y); }
	public Transform setPosition(float x, float y) { setOffset(x, y); return this; }
	public Transform setZ(float z)
	{
		this.offset.z = z;
		DrawingManager.instance().sort();
		return this;
	}

	public Transform translate(PVector vec) { return translate(vec.x, vec.y); }
	public Transform translate(float x, float y) { setPosition(PVector.add(getPosition(), new PVector(x, y))); return this; }

	@Override
	public void debug()
	{
		// Show
		Helper.drawDebuggingBox(World.p, getPosition(), new PVector(3, 3), new PVector(0, 0, 0), true);

		// Print
		System.out.println(Helper.colorify("[" + getClass().getSimpleName() + "]", Helper.Colors.FG_BLUE, null));
		System.out.println("Position: " + getPosition());
	}
}
