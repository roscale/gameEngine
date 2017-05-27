package gameEngine;

import processing.core.PVector;

/**
 * Created by roscale on 4/20/17.
 */
public class Transform extends Component
{
	private PVector pos = new PVector();

	public Transform(GameObject gameObject) { super(gameObject); }

	public PVector getPosition() { return pos; }
	public void setPosition(PVector pos) { setPosition(pos.x, pos.y); }
	public void setPosition(float x, float y) { this.pos.set(x, y); }

	public void setZ(float z)
	{
		this.pos.z = z;
		DrawingManager.instance().sort();
	}

	@Override
	public void debug()
	{
		// Show
		Helper.drawDebuggingBox(World.p, pos, new PVector(3, 3), new PVector(0, 0, 0), true);

		// Print
		System.out.println(Helper.colorify("[" + getClass().getSimpleName() + "]", Helper.Colors.FG_BLUE, null));
		System.out.println("Position: " + pos);
	}
}
