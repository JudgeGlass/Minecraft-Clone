package net.sytes.judgeglass.lwjgl.renderEngine.gui;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

public class Tesselator {
	private static final int MAX_MEMORY_USE = 4194304;
	  private static final int MAX_FLOATS = 524288;
	  private FloatBuffer buffer = BufferUtils.createFloatBuffer(524288);
	  private float[] array = new float[524288];
	  private int vertices = 0;
	  private float u;
	  private float v;
	  private float r;
	  private float g;
	  private float b;
	  private boolean hasColor = false;
	  private boolean hasTexture = false;
	  private int len = 3;
	  private int p = 0;
	  private boolean noColor = false;
	  public static Tesselator instance = new Tesselator();
	  
	  public void end()
	  {
	    if (this.vertices > 0)
	    {
	      this.buffer.clear();
	      this.buffer.put(this.array, 0, this.p);
	      this.buffer.flip();
	      if ((this.hasTexture) && (this.hasColor)) {
	        glInterleavedArrays(GL_T2F_C3F_V3F, 0, this.buffer);
	      } else if (this.hasTexture) {
	        glInterleavedArrays(GL_T2F_V3F, 0, this.buffer);
	      } else if (this.hasColor) {
	        glInterleavedArrays(GL_C3F_V3F, 0, this.buffer);
	      } else {
	        glInterleavedArrays(GL_V3F, 0, this.buffer);
	      }
	      glEnableClientState(GL_VERTEX_ARRAY);
	      if (this.hasTexture) {
	        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	      }
	      if (this.hasColor) {
	        glEnableClientState(GL_COLOR_ARRAY);
	      }
	      glDrawArrays(7, 0, this.vertices);
	      
	      glDisableClientState(GL_VERTEX_ARRAY);
	      if (this.hasTexture) {
	        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	      }
	      if (this.hasColor) {
	        glDisableClientState(GL_COLOR_ARRAY);
	      }
	    }
	    clear();
	  }
	  
	  private void clear()
	  {
	    this.vertices = 0;
	    
	    this.buffer.clear();
	    this.p = 0;
	  }
	  
	  public void begin()
	  {
	    clear();
	    this.hasColor = false;
	    this.hasTexture = false;
	    this.noColor = false;
	  }
	  
	  public void tex(float u, float v)
	  {
	    if (!this.hasTexture) {
	      this.len += 2;
	    }
	    this.hasTexture = true;
	    this.u = u;
	    this.v = v;
	  }
	  
	  public void color(int r, int g, int b)
	  {
	    color((byte)r, (byte)g, (byte)b);
	  }
	  
	  public void color(byte r, byte g, byte b)
	  {
	    if (this.noColor) {
	      return;
	    }
	    if (!this.hasColor) {
	      this.len += 3;
	    }
	    this.hasColor = true;
	    this.r = ((r & 0xFF) / 255.0F);
	    this.g = ((g & 0xFF) / 255.0F);
	    this.b = ((b & 0xFF) / 255.0F);
	  }
	  
	  public void vertexUV(float x, float y, float z, float u, float v)
	  {
	    tex(u, v);
	    vertex(x, y, z);
	  }
	  
	  public void vertex(float x, float y, float z)
	  {
	    if (this.hasTexture)
	    {
	      this.array[(this.p++)] = this.u;
	      this.array[(this.p++)] = this.v;
	    }
	    if (this.hasColor)
	    {
	      this.array[(this.p++)] = this.r;
	      this.array[(this.p++)] = this.g;
	      this.array[(this.p++)] = this.b;
	    }
	    this.array[(this.p++)] = x;
	    this.array[(this.p++)] = y;
	    this.array[(this.p++)] = z;
	    
	    this.vertices += 1;
	    if ((this.vertices % 4 == 0) && (this.p >= 524288 - this.len * 4)) {
	      end();
	    }
	  }
	  
	  public void color(int c)
	  {
	    int r = c >> 16 & 0xFF;
	    int g = c >> 8 & 0xFF;
	    int b = c & 0xFF;
	    color(r, g, b);
	  }
	  
	  public void noColor()
	  {
	    this.noColor = true;
	  }
}
