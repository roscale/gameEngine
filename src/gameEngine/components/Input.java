package gameEngine.components;

import gameEngine.Component;
import gameEngine.GameObject;
import gameEngine.util.Helper;
import gameEngine.World;
import gameEngine.managers.InputManager;
import processing.core.PVector;

/**
 * Created by roscale on 4/21/17.
 */

public class Input extends Component
{
	private PVector size = new PVector(0, 0);

	public Input(GameObject gameObject)
	{
		super(gameObject);
		addToManager(InputManager.instance());
	}

	public PVector getSize() { return size; }
	public void setSize(PVector newSize) { size.set(newSize); }

	@Override
	public void debug() // Show
	{
		Helper.drawDebuggingBox(World.p, getPosition(), getSize(), new PVector(255, 255, 0), false);
	}
}


