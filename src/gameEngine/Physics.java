package gameEngine;

import processing.core.PVector;

/**
 * Created by roscale on 4/28/17.
 */
public class Physics extends Component
{
	public PVector velocity = new PVector();
	public PVector acceleration = new PVector();
	public float drag = 1f;

	Physics(GameObject gameObject)
	{
		super(gameObject);
		addToManager(PhysicsManager.instance());
	}

	@Override
	public void enable() { enabled = true; }

	@Override
	public void disable() { enabled = false; }

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
