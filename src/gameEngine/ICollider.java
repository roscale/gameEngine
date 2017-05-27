package gameEngine;

/**
 * Created by roscale on 4/21/17.
 */

public interface ICollider
{
	// User callbacks
	default void onCollisionEnterWith(GameObject other) {}
	default void onCollisionStayWith(GameObject other) {}
	default void onCollisionExitWith(GameObject other) {}
}
