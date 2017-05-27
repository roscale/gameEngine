package gameEngine;

import processing.core.PVector;

/**
 * Created by roscale on 4/21/17.
 */

public class Input extends Component
{
	PVector relPos = new PVector(0, 0);
	PVector size = new PVector(0, 0);

	Input(GameObject gameObject)
	{
		super(gameObject);
		addToManager(InputManager.instance());
	}

	public PVector getPosition() { return PVector.add(gameObject.transform.getPosition(), relPos); }
	public PVector getSize() { return size; }

	public void setRelativePosition(PVector newPos) { relPos.set(newPos); }
	public void setSize(PVector newSize) { size.set(newSize); }

	@Override
	public void debug() // Show
	{
		Helper.drawDebuggingBox(World.p, getPosition(), getSize(), new PVector(255, 255, 0), false);
	}
}


