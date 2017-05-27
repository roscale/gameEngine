package gameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by roscale on 5/22/17.
 */
public abstract class ComponentManager
{
	private static List<ComponentManager> managers = Collections.synchronizedList(new ArrayList<>());

	public static void updateAll()
	{
		for (int i = 0; i < managers.size(); i++)
			managers.get(i).updateComponents();
	}

	public ComponentManager() { managers.add(this); }

	public abstract boolean containsComponent(Component component);
	public abstract void addComponent(Component component);
	public abstract void removeComponent(Component component);

	public void updateComponents() {}

	public abstract void debug();
}
