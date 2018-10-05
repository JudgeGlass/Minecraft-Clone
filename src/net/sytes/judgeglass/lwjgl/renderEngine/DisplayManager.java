package net.sytes.judgeglass.lwjgl.renderEngine;

import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;

import net.sytes.judgeglass.lwjgl.renderEngine.test.GameLoop;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.GameStatus;

public class DisplayManager {

	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;
	private static final int MAX_FPS = 240;
	private static Frame frame;
	private static Canvas canvas;
	private static ContextAttribs attribs;
	private static Panel menuPanel;
	private static Panel gamePanel;
	
	public static int cH = 0;
	public static int cW = 0;

	public static boolean awtCloseRequested;

	public static void createDisplay() {
		frame = new Frame();
		canvas = new Canvas();
		menuPanel = new Panel();
		gamePanel = new Panel();
		
		gamePanel.add(canvas, BorderLayout.CENTER);

		attribs = new ContextAttribs(3, 2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);

		frame.addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				canvas.requestFocusInWindow();
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				awtCloseRequested = true;
				if(!GameLoop.playing) {
					frame.dispose();
				}
			}
		});
		
		frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	cH = canvas.getHeight();
		    	cW = canvas.getWidth();
		    	GameLoop.updateView = true;
		    }
		});

		// Display.setFullscreen(true);
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Minecraft Clone v" + GameStatus.VERSION + " (Hunter Wilcox)");
		frame.setVisible(true);
		
		Button btn = new Button("Play");
		
		btn.addActionListener((ActionEvent) -> {
			frame.remove(menuPanel);
		});
		
		menuPanel.add(btn, BorderLayout.CENTER);
		
		frame.add(canvas, BorderLayout.CENTER);
		new Thread(() -> play()).start();
	}

	public static void play() {
		
		try {
			Display.setParent(canvas);

			Display.create(new PixelFormat(), attribs);

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Mouse.setGrabbed(true);

		glViewport(0, 0, canvas.getWidth(), canvas.getHeight());
		GameLoop.start();
	}

	public static void updateDisplay() {
		Display.sync(MAX_FPS);
		Display.update();
	}

	public static void closeDisplay() {
		Display.destroy();
	}

	public static void closeAWT() {
		frame.dispose();
	}
}
