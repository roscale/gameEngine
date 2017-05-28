package gameEngine;

/**
 * Created by roscale on 5/28/17.
 */
public abstract class Object
{
	public String name;
	private long instanceID;

	public Object()
	{
		instanceID = InstanceIDGenerator.getNextID();
	}

	public long getInstanceID() { return instanceID; }

	abstract public void destroy();

	@Override
	public String toString()
	{
		if (name != null)
			return name;
		return super.toString();
	}

	//////////////////////////////////////////////////

	public static void destroy(Object obj) { destroy(obj, 0); }
	public static void destroy(Object obj, long t)
	{
		new Thread(() ->
		{
			try
			{
				Thread.sleep(t);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			obj.destroy();
		}).start();
	}


	public static abstract class InstanceIDGenerator
	{
		public static long nextID = 0;
		public static long getNextID() { return nextID++; }
	}
}
