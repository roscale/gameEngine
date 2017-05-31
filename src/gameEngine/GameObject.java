package gameEngine;

import gameEngine.components.Transform;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by roscale on 4/20/17.
 */
public abstract class GameObject extends Object
{
	public ArrayList<Component> components = new ArrayList<>();
	public Transform transform; // Shortcut

	public GameObject()
	{
		transform = addComponent(Transform.class);

		World.addGameObject(this);
	}

	@Override
	public void destroy()
	{
		// User callback
		onDestroy();

		World.removeGameObject(this);

		for (Component component : components)
			component.disable();
	}

	// User callback
	public void update() {}
	public void onDestroy() {}

	public <T extends Component> T addComponent(Class<T> c)
	{
		try
		{
			T component = c.getDeclaredConstructor(GameObject.class).newInstance(this);

			components.add(component);
			return component;
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
		{
			e.getCause().printStackTrace();
			return null;
		}
	}

	public <T extends Component> T getComponent(Class<T> c)
	{
		for (int i = 0; i < components.size(); i++)
			if (c.isAssignableFrom(components.get(i).getClass()))
				return (T) components.get(i);
		return null;
	}

	public void debug()
	{
		System.out.println();
		System.out.println(this);
		for (Component component : components)
			if (component.enabled)
				component.debug();
	}
}

