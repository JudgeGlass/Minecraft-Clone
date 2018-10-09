package net.sytes.judgeglass.lwjgl.renderEngine.models;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL30.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class OldTexture {
	private HashMap<String, Integer> idMap = new HashMap();
	  
	  public int loadTexture(String resourceName, int mode)
	  {
	    try
	    {
	      if (this.idMap.containsKey(resourceName)) {
	        return ((Integer)this.idMap.get(resourceName)).intValue();
	      }
	      IntBuffer ib = BufferUtils.createIntBuffer(1);
	      ib.clear();
	      GL11.glGenTextures(ib);
	      int id = ib.get(0);
	      this.idMap.put(resourceName, Integer.valueOf(id));
	      
	      GL11.glBindTexture(3553, id);
	      
	      GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, mode);
	      GL11.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, mode);
	      
	      BufferedImage img = ImageIO.read(new File(resourceName));
	      int w = img.getWidth();
	      int h = img.getHeight();
	      
	      ByteBuffer pixels = BufferUtils.createByteBuffer(w * h * 4);
	      int[] rawPixels = new int[w * h];
	      byte[] newPixels = new byte[w * h * 4];
	      img.getRGB(0, 0, w, h, rawPixels, 0, w);
	      for (int i = 0; i < rawPixels.length; i++)
	      {
	        int a = rawPixels[i] >> 24 & 0xFF;
	        int r = rawPixels[i] >> 16 & 0xFF;
	        int g = rawPixels[i] >> 8 & 0xFF;
	        int b = rawPixels[i] & 0xFF;
	        
	        newPixels[(i * 4 + 0)] = ((byte)r);
	        newPixels[(i * 4 + 1)] = ((byte)g);
	        newPixels[(i * 4 + 2)] = ((byte)b);
	        newPixels[(i * 4 + 3)] = ((byte)a);
	      }
	      pixels.put(newPixels);
	      pixels.position(0).limit(newPixels.length);
	      glGenerateMipmap(GL_TEXTURE_2D);
	      
	      return id;
	    }
	    catch (IOException e)
	    {
	      throw new RuntimeException("!! " + e.getMessage());
	    }
	  }

}
