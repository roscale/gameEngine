package gameEngine.components;

import gameEngine.Component;
import gameEngine.GameObject;
import gameEngine.components.callbacks.IDrawable;
import gameEngine.managers.DrawingManager;

/**
 * Created by roscale on 5/6/17.
 */
public class CustomDraw extends Component implements IDrawable
{
	public CustomDraw(GameObject gameObject)
	{
		super(gameObject);
		addToManager(DrawingManager.instance());
	}

	@Override
	public void draw()
	{
		((IDrawable) gameObject).draw();
	}

	@Override
	public void debug()
	{

	}
}
