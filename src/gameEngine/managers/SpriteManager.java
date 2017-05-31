package gameEngine.managers;

import gameEngine.Component;
import gameEngine.ComponentManager;
import gameEngine.components.SpriteRenderer;

import java.util.ArrayList;

/**
 * Created by roscale on 5/1/17.
 */
public class SpriteManager extends ComponentManager
{
	private static SpriteManager instance;
	public static SpriteManager instance()
	{
		if (instance == null)
			instance = new SpriteManager();
		return instance;
	}

	//////////////////////////////////////////////////////////////

	public ArrayList<SpriteRenderer> spriteRenderers = new ArrayList<>();

	@Override
	public boolean containsComponent(Component component)
	{
		return spriteRenderers.contains(component);
	}

	@Override
	public void addComponent(Component component)
	{
		spriteRenderers.add((SpriteRenderer) component);
	}

	@Override
	public void removeComponent(Component component)
	{
		spriteRenderers.remove(component);
	}

	@Override
	public void updateComponents()
	{
		for (int i = 0; i < spriteRenderers.size(); i++)
			spriteRenderers.get(i).step();
	}

	@Override
	public void debug()
	{
		for (int i = 0; i < spriteRenderers.size(); i++)
			spriteRenderers.get(i).debug();
	}
}
