package gameEngine;

/**
 * Created by roscale on 5/6/17.
 */
public class CustomDraw extends Component implements IDrawable
{
	CustomDraw(GameObject gameObject)
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
