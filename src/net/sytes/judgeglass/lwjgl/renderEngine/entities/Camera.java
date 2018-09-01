package net.sytes.judgeglass.lwjgl.renderEngine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0, 5, 1);
	private float pitch;
	private float yaw;
	private float roll;
	
	private float rotX;
	private float rotY;
	private float rotZ;
	private float moveAt;
	private float moveSpeed;

	public Camera(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public void move() {
		System.out.println("Camera angle: X: " + rotX + " Y: " + rotY);
		
		if(rotX < 90)
			rotX += -Mouse.getDY() * .07f;
		else
			rotX -= 3;
		rotY += Mouse.getDX() * .07f;
		
		
		float dx = (float) -(moveAt * Math.sin(Math.toRadians(rotY)));
		float dy = (float) (moveAt * Math.sin(Math.toRadians(rotX)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotY)));
		
		position.x += dx;
		position.y += dy;
		position.z += dz;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveAt = -moveSpeed;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveAt = moveSpeed;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x -= 0.02f;
		}else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x += 0.02f;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += 0.02f;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.02f;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			Mouse.setGrabbed(false);
		}else {
			moveAt = 0;
		}
	}
	
	
	
	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rot) {
		this.rotZ = rot;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

}
