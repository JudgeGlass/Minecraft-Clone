package net.sytes.judgeglass.lwjgl.renderEngine.tools;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import net.sytes.judgeglass.lwjgl.renderEngine.test.GameLoop;

public class MouseHandler extends GLFWCursorPosCallback {

	private double mouseX;
    private double mouseY;
    private static double mouseDX;
    private static double mouseDY;
    private boolean wasFirst;
 
    @Override
    public void invoke(long window, double xpos, double ypos) {
        if (!wasFirst) {
            wasFirst = true;
        } else {
        	if(!GameLoop.inventoryOpen) {
	            mouseDX += xpos - mouseX;
	            mouseDY += ypos - mouseY;
        	}
        }
        mouseX = xpos;
        mouseY = ypos;
    }
 
    public static double getDX() {
        double result = mouseDX;
        mouseDX = 0;
        return result;
    }
 
    public static double getDY() {
        double result = mouseDY;
        mouseDY = 0;
        return result;
    }
}