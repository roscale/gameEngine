package gameEngine;

import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by roscale on 4/22/17.
 */
public abstract class Raycast
{
	public static boolean debugging = false;

	public static int stepAmount = 50;

	public static ArrayList<GameObject> cast(PVector from, PVector dir, int steps, IRaycastCondition condition)
	{
		ArrayList<GameObject> hitGameObjects = new ArrayList<>();
		dir.normalize();

		if (debugging)
		{
			PVector end = PVector.add(from, PVector.mult(dir, steps * stepAmount));
			World.p.line(from.x, from.y, end.x, end.y);
		}

		for (int step = 0; step < steps; step++)
		{
			PVector lerpedPoint = PVector.add(from, PVector.mult(dir, step* stepAmount));

			// Check every collider
			for (int i = 0; i < CollisionManager.instance().colliders.size(); i++)
			{
				Collider otherCol = CollisionManager.instance().colliders.get(i);
				if (otherCol.enabled && otherCol.containsPoint(lerpedPoint) && condition.eval(otherCol.gameObject))
					hitGameObjects.add(otherCol.gameObject);
			}
		}
		return hitGameObjects;
	}
}

