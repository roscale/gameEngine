package gameEngine.util;

import gameEngine.World;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by roscale on 4/30/17.
 */
public class Sprite
{
	private String path;
	private ArrayList<PImage> frames = new ArrayList<>();
	public PVector frameSize;

	public Sprite(String path, String ext)
	{
		this.path = path;

		String framePath;
		for (int i = 0; new File(framePath = path + i + "." + ext).exists(); i++)
			frames.add(World.p.loadImage(new File(framePath).getAbsolutePath()));

		if (frames.isEmpty())
			throw new RuntimeException("No sprites found at " + path);

		frameSize = new PVector(frames.get(0).width, frames.get(0).height);
	}

	// Copy ctor, reuse resources
	public Sprite(Sprite sprite) { frames = sprite.frames; }

	public int getFrameCount() { return frames.size(); }
	public PImage getFrameAt(int index) { return frames.get(index); }

	public void debugPrint() { System.out.println("Sprite: " + path + ": " + frames.size() + " frames"); }
}
