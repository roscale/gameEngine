package gameEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roscale on 5/27/17.
 */
public class Layers
{
	private static Layers instance;
	private static Layers instance()
	{
		if (instance == null)
			instance = new Layers();
		return instance;
	}

	public static void add(String key, Float value) { instance().map.put(key, value); }
	public static float get(String key) { return instance().map.get(key); }

	/////////////////////////////////////////////////

	private Map<String, Float> map = new HashMap<>();

	private Layers()
	{
		map.put("Default", 0f);
		map.put("UI", 10f);
	}
}
