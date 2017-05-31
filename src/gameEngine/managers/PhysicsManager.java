package gameEngine.managers;

import gameEngine.Component;
import gameEngine.ComponentManager;
import gameEngine.components.Physics;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by roscale on 5/22/17.
 */
public class PhysicsManager extends ComponentManager
{
	private static PhysicsManager instance;
	public static PhysicsManager instance()
	{
		if (instance == null)
			instance = new PhysicsManager();
		return instance;
	}

	//////////////////////////////////////////////////////////////

//	private ExecutorService executor = Executors.newFixedThreadPool(8);
//	private CountDownLatch latch;

	private ArrayList<Physics> physics = new ArrayList<>();

	@Override
	public boolean containsComponent(Component component)
	{
		return physics.contains(component);
	}

	@Override
	public void addComponent(Component component)
	{
		physics.add((Physics) component);
	}

	@Override
	public void removeComponent(Component component)
	{
		physics.remove(component);
	}

	@Override
	public void updateComponents()
	{
		for (int i = 0; i < physics.size(); i++)
			physics.get(i).step();

//		latch = new CountDownLatch(physics.size());
//
//		for (Physics physics : physics)
//			executor.submit(new UpdatePhysicsComponent(physics, latch));
//
//		try
//		{
//			latch.await(5, TimeUnit.SECONDS);
//		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
	}

	@Override
	public void debug()
	{
		for (int i = 0; i < physics.size(); i++)
			physics.get(i).debug();
	}
}

class UpdatePhysicsComponent implements Runnable
{
	private Physics physics;
	private CountDownLatch latch;

	public UpdatePhysicsComponent(Physics physics, CountDownLatch latch)
	{
		this.physics = physics;
		this.latch = latch;
	}

	@Override
	public void run()
	{
		physics.step();
		latch.countDown();
	}
}