package gameEngine;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

/**
 * Created by roscale on 4/22/17.
 */
public abstract class Helper
{
	public static PVector getMousePos(MouseEvent event)
	{ return new PVector(event.getX(), event.getY()); }

	public static PVector getMousePos(PApplet p)
	{ return new PVector(p.mouseX, p.mouseY); }

	public static PVector getRelativeMousePos(PApplet p)
	{ return new PVector(p.mouseX - p.pmouseX, p.mouseY - p.pmouseY); }

	public static boolean containsPoint(PVector pos, PVector size, PVector point)
	{
		PVector bottomRightCorner = PVector.add(pos, size);
		return (point.x >= pos.x) && (point.x < bottomRightCorner.x) &&
				(point.y >= pos.y) && (point.y < bottomRightCorner.y);
	}

	public static boolean doCollide(PVector thisPos, PVector thisSize, PVector otherPos, PVector otherSize)
	{
		// Box collision
		return !(thisPos.x + thisSize.x <= otherPos.x ||
				thisPos.x >= otherPos.x + otherSize.x ||
				thisPos.y + thisSize.y <= otherPos.y ||
				thisPos.y >= otherPos.y + otherSize.y);
	}

	public static PVector centerOf(PVector pos, PVector size) { return PVector.add(pos, PVector.div(size, 2)); }

	public static void drawDebuggingBox(PApplet p, PVector pos, PVector size, PVector color, boolean filled)
	{
		p.pushStyle();

		if (filled)
			p.fill(color.x, color.y, color.z);
		else
			p.noFill();

		p.strokeWeight(1);
		p.stroke(color.x, color.y, color.z);

		p.rect(pos.x, pos.y, size.x, size.y);

		p.popStyle();
	}

	static class Colors
	{
		public static final int FG_BLACK = 30;
		public static final int FG_RED = 31;
		public static final int FG_GREEN = 32;
		public static final int FG_YELLOW = 33;
		public static final int FG_BLUE = 34;
		public static final int FG_MAGENTA = 35;
		public static final int FG_CYAN = 36;
		public static final int FG_WHITE = 37;

		public static final int BG_BLACK = 40;
		public static final int BG_RED = 41;
		public static final int BG_GREEN = 42;
		public static final int BG_YELLOW = 43;
		public static final int BG_BLUE = 44;
		public static final int BG_MAGENTA = 45;
		public static final int BG_CYAN = 46;
		public static final int BG_WHITE = 47;
	}

	public static String colorify(String text, Integer fg, Integer bg)
	{
		if (bg == null)
			return ((char) 27 + "[" + fg + "m" + text + (char) 27 + "[0m");
		return ((char)27 + "[" + fg + ";" + bg + "m" + text + (char)27 + "[0m");
	}
}
