package gameEngine;

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

	private ArrayList<Physics> componentList = new ArrayList<>();

	@Override
	public boolean containsComponent(Component component)
	{
		return false;
	}

	@Override
	public void addComponent(Component component)
	{
		componentList.add((Physics) component);
	}

	@Override
	public void removeComponent(Component component)
	{
		componentList.remove(component);
	}

	@Override
	public void updateComponents()
	{
		for (int i = 0; i < componentList.size(); i++)
			componentList.get(i).step();

//		latch = new CountDownLatch(componentList.size());
//
//		for (Physics physics : componentList)
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
		for (int i = 0; i < componentList.size(); i++)
			componentList.get(i).debug();
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