package gameEngine;

import gameEngine.managers.DrawingManager;
import gameEngine.util.Raycast;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by roscale on 4/30/17.
 */
public abstract class World
{
	public static PApplet p;
	public static void setPApplet(PApplet p) { World.p = p; }

	public static List<GameObject> list = Collections.synchronizedList(new ArrayList<GameObject>());

	public static void addGameObject(GameObject obj) { list.add(obj); }// list.sort(Component::compareTo); }
	public static void removeGameObject(GameObject obj) { list.remove(obj); }

	public static void update()
	{
		ComponentManager.updateAll();

		for (int i = 0; i < list.size(); i++)
			list.get(i).update(); // User callback
	}

	public static void show()
	{
//		ComponentManager.showAll();
		DrawingManager.instance().show();
	}

	public static void debug()
	{
		Raycast.debugging = true;

		for (int i = 0; i < list.size(); i++)
			list.get(i).debug();
	}
}
