package gameEngine.components.callbacks;

import processing.event.MouseEvent;

/**
 * Created by roscale on 4/21/17.
 */
public interface IInput
{
	// User callbacks
	default void mouseMoved(MouseEvent event) {}
	default void mousePressed(MouseEvent event) {}
	default void mouseClicked(MouseEvent event) {}
	default void mouseDragged(MouseEvent event) {}
	default void mouseReleased(MouseEvent event) {}
	default void mouseWheel(MouseEvent event) {}
}
