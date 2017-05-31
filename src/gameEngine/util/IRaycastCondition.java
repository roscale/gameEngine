package gameEngine.util;

import gameEngine.GameObject;

/**
 * Created by roscale on 4/30/17.
 */
public interface IRaycastCondition
{
	boolean eval(GameObject obj);
}
