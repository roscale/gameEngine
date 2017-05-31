package gameEngine.components;

import gameEngine.Component;
import gameEngine.GameObject;
import gameEngine.util.Helper;
import gameEngine.managers.PhysicsManager;
import processing.core.PVector;

/**
 * Created by roscale on 4/28/17.
 */
public class Physics extends Component
{
	public PVector velocity = new PVector();
	public PVector acceleration = new PVector();
	public float drag = 1f;

	public Physics(GameObject gameObject)
	{
		super(gameObject);
		addToManager(PhysicsManager.instance());
	}

	public void step()
	{
		if (!enabled)
			return;

		velocity.add(acceleration);
		velocity.mult(drag);
		gameObject.transform.getPosition().add(velocity);

		acceleration.mult(0);
	}

	public void applyForce(PVector force) { acceleration.add(force); }

	@Override
	public void debug()
	{
		System.out.println(Helper.colorify("[" + getClass().getSimpleName() + "]", Helper.Colors.FG_BLUE, null));
		System.out.println("Velocity: " + velocity);
		System.out.println("Acceleration: " + acceleration);
	}
}
