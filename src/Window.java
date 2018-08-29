import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	public Window() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 480));
			Display.setTitle("LWJGL - TEST");
			Display.create();
		}catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		// Init OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()) {
			//Render 
			render();
			
			Display.update();
			Display.sync(120);
		}
		
		Display.destroy();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glBegin(GL_QUADS);
			glVertex2f(400.0f, 400.0f); // Upper-left
			glVertex2f(450.0f, 400.0f); // Upper-right
			glVertex2f(450.0f, 450.0f); // Bottom-right
			glVertex2f(400.0f, 450.0f); // Bottom-left
		glEnd();
		
		glBegin(GL_LINES);
			glVertex2i(100, 100);
			glVertex2i(200, 200);
		glEnd();
	}
	
	public static void main(String args[]) {
		new Window();
	}
}
