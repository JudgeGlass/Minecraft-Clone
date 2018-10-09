package net.sytes.judgeglass.lwjgl.renderEngine.tools;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardHandler extends GLFWKeyCallback {

	public static boolean[] keys = new boolean[65536];
	private static List<Integer> pressed = new ArrayList<Integer>();

	// The GLFWKeyCallback class is an abstract method that
	// can't be instantiated by itself and must instead be extended
	//
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
		try {
			keys[key] = action != GLFW_RELEASE;
			if(action == GLFW_RELEASE && pressed.contains(new Integer(key))) pressed.remove(new Integer(key));
		}catch(Exception e) {
			return;
		}
	}

	public static boolean isPressed(int key) {
		return keys[key];
	}

	public static boolean isClicked(int key) {
		if (!keys[key])
			return false;
		if (pressed.contains(new Integer(key)))
			return false;
		pressed.add(new Integer(key));
		return true;
	}

}