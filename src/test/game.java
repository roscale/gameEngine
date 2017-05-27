package test;

import gameEngine.*;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.util.ArrayList;

/**
 * Created by roscale on 4/20/17.
 */
public class game extends PApplet
{
	ArrayList<Zombie> zombies = new ArrayList<>();
	ArrayList<Peashooter> peashooters = new ArrayList<>();
	Grid g;
	// Animation a;

	@Override
	public void settings() { size(800, 800); }

	@Override
	public void setup()
	{
		World.setPApplet(this);

		 Globals.loadSprites();

		g = new Grid(400, 400, 5, 5, 50, 50);

//		Zombie zombie1 = new Zombie();
//		zombies.addComponent(zombie1);
//		zombie1.getComponent(Transform.class).setPosition(3, 5);

		for (int i = 0; i < 10; i++)
		{
			Peashooter peashooter = new Peashooter();
			peashooters.add(peashooter);
			peashooter.getComponent(Transform.class).setPosition(200, 200);
		}

		// a = new Animation(Globals.peashooterSprite, 3, 1);
		new Particle(Globals.zombieSprite, 60, false, 5000).transform.setPosition(20, 20);
	}

	@Override
	public void draw()
	{
		background(200);

		World.update();
		World.show();
		World.debug();
//		System.out.println(frameRate);
	}

	@Override public void mouseMoved(MouseEvent event) { InputManager.instance().mouseMoved(event); }
	@Override public void mousePressed(MouseEvent event) { InputManager.instance().mousePressed(event); }
	@Override public void mouseClicked(MouseEvent event) { InputManager.instance().mouseClicked(event); }
	@Override public void mouseDragged(MouseEvent event) { InputManager.instance().mouseDragged(event); }
	@Override public void mouseReleased(MouseEvent event) { InputManager.instance().mouseReleased(event); }
	@Override public void mouseWheel(MouseEvent event) { InputManager.instance().mouseWheel(event); }
}
