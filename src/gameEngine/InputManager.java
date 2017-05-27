package gameEngine;

import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.ArrayList;

/**
 * Created by roscale on 4/30/17.
 */
public class InputManager extends ComponentManager
{
	private static InputManager instance;
	public static InputManager instance()
	{
		if (instance == null)
			instance = new InputManager();
		return instance;
	}

	//////////////////////////////////////////////////////////////

	public ArrayList<Input> inputs = new ArrayList<>();
	public IInput focusedObject;
	public boolean isDragged = false;

	@Override
	public boolean containsComponent(Component component)
	{
		return inputs.contains(component);
	}

	@Override
	public void addComponent(Component component)
	{
		inputs.add((Input) component);
	}

	@Override
	public void removeComponent(Component component)
	{
		inputs.remove(component);
	}

	@Override
	public void debug()
	{
		// TODO Implement debug() in InputManager
	}

	public void sort() { inputs.sort(Input::compareTo); }

	public ArrayList<Input> getInputsAt(PVector point)
	{
		ArrayList<Input> list = new ArrayList<>();

		// Nearest objects first(>z)
		for (int i = inputs.size()-1; i >= 0; i--)
		{
			PVector pos = inputs.get(i).getPosition();
			PVector size = inputs.get(i).getSize();
			if (Helper.containsPoint(pos, size, point))
				list.add(inputs.get(i));
		}
		return list;
	}

	public Input getFirstInputAt(PVector point)
	{
		ArrayList<Input> inputs = getInputsAt(point);
		return !inputs.isEmpty() ? inputs.get(0) : null;
	}

	public void mouseMoved(MouseEvent event)
	{
		PVector mousePos = Helper.getMousePos(event);

		Input firstInput;
		if ((firstInput = getFirstInputAt(mousePos)) != null)
		{
			IInput obj = (IInput) firstInput.gameObject;
			obj.mouseMoved(event);
		}
	}

	public void mousePressed(MouseEvent event)
	{
		PVector mousePos = Helper.getMousePos(event);

		Input input;
		if ((input = getFirstInputAt(mousePos)) != null)
		{
			IInput obj = (IInput) input.gameObject;
			focusedObject = obj;
			obj.mousePressed(event);
		}
	}

	public void mouseClicked(MouseEvent event)
	{
		if (focusedObject == null)
			return;

		focusedObject.mouseClicked(event);
		focusedObject = null;
	}

	public void mouseDragged(MouseEvent event)
	{
		if (focusedObject == null)
			return;

		isDragged = true;
		focusedObject.mouseDragged(event);
	}

	public void mouseReleased(MouseEvent event)
	{
		if (focusedObject == null)
			return;

		focusedObject.mouseReleased(event);

		if (isDragged)
		{
			focusedObject = null;
			isDragged = false;
			// No mouseClicked() will occur
		}
	}

	public void mouseWheel(MouseEvent event)
	{
		PVector mousePos = Helper.getMousePos(event);

		Input input;
		if ((input = getFirstInputAt(mousePos)) != null)
		{
			IInput obj = (IInput) input.gameObject;
			obj.mouseWheel(event);
		}
	}
}
