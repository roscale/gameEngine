package gameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by roscale on 5/6/17.
 */
public class DrawingManager extends ComponentManager
{
	private static DrawingManager instance;
	public static DrawingManager instance()
	{
		if (instance == null)
			instance = new DrawingManager();
		return instance;
	}

	//////////////////////////////////////////////////////////////

	public List<Component> components = Collections.synchronizedList(new ArrayList<Component>());

	@Override
	public boolean containsComponent(Component component)
	{
		return components.contains(component);
	}

	@Override
	public void addComponent(Component component) { components.add(component); sort(); }

	@Override
	public void removeComponent(Component component)
	{
		components.remove(component);
	}

	public void sort() { components.sort(Component::compareTo); }

	public void show()
	{
		for (int i = 0; i < components.size(); i++)
			((IDrawable) components.get(i)).draw();
	}

	@Override
	public void debug()
	{
		// TODO Implement debug() in DrawingManager
	}
}
