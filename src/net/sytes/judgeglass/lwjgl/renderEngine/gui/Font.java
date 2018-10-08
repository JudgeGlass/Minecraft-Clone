package net.sytes.judgeglass.lwjgl.renderEngine.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import net.sytes.judgeglass.lwjgl.renderEngine.models.Texture;

public class Font {
	private int[] charWidths = new int[256];
	  private int fontTexture = 0;
	  
	  public Font(String name, Texture textures)
	  {
		BufferedImage img;
	    try
	    {
	      img = ImageIO.read(new File(name));
	    }
	    catch (IOException e)
	    {
	      //BufferedImage img;
	      throw new RuntimeException(e);
	    }
	    
	    int w = img.getWidth();
	    int h = img.getHeight();
	    int[] rawPixels = new int[w * h];
	    img.getRGB(0, 0, w, h, rawPixels, 0, w);
	    for (int i = 0; i < 128; i++)
	    {
	      int xt = i % 16;
	      int yt = i / 16;
	      
	      int x = 0;
	      boolean emptyColumn = false;
	      for (; (x < 8) && (!emptyColumn); x++)
	      {
	        int xPixel = xt * 8 + x;
	        emptyColumn = true;
	        for (int y = 0; (y < 8) && (emptyColumn); y++)
	        {
	          int yPixel = (yt * 8 + y) * w;
	          int pixel = rawPixels[(xPixel + yPixel)] & 0xFF;
	          if (pixel > 128) {
	            emptyColumn = false;
	          }
	        }
	      }
	      if (i == 32) {
	        x = 4;
	      }
	      this.charWidths[i] = x;
	    }
	    this.fontTexture = textures.loadTexture(name, 9728);
	  }
	  
	  public void drawShadow(String str, int x, int y, int color)
	  {
	    draw(str, x + 1, y + 1, color, true);
	    draw(str, x, y, color);
	  }
	  
	  public void draw(String str, int x, int y, int color)
	  {
	    draw(str, x, y, color, false);
	  }
	  
	  public void draw(String str, int x, int y, int color, boolean darken)
	  {
	    char[] chars = str.toCharArray();
	    if (darken) {
	      color = (color & 0xFCFCFC) >> 2;
	    }
	    GL11.glEnable(3553);
	    GL11.glBindTexture(3553, this.fontTexture);
	    Tesselator t = Tesselator.instance;
	    t.begin();
	    t.color(color);
	    int xo = 0;
	    for (int i = 0; i < chars.length; i++)
	    {
	      if (chars[i] == '&')
	      {
	        int cc = "0123456789abcdef".indexOf(chars[(i + 1)]);
	        int br = (cc & 0x8) * 8;
	        int b = (cc & 0x1) * 191 + br;
	        int g = ((cc & 0x2) >> 1) * 191 + br;
	        int r = ((cc & 0x4) >> 2) * 191 + br;
	        color = r << 16 | g << 8 | b;
	        i += 2;
	        if (darken) {
	          color = (color & 0xFCFCFC) >> 2;
	        }
	        t.color(color);
	      }
	      int ix = chars[i] % '\020' * 8;
	      int iy = chars[i] / '\020' * 8;
	      t.vertexUV(x + xo, y + 8, 0.0F, ix / 128.0F, (iy + 8) / 128.0F);
	      t.vertexUV(x + xo + 8, y + 8, 0.0F, (ix + 8) / 128.0F, (iy + 8) / 128.0F);
	      t.vertexUV(x + xo + 8, y, 0.0F, (ix + 8) / 128.0F, iy / 128.0F);
	      t.vertexUV(x + xo, y, 0.0F, ix / 128.0F, iy / 128.0F);
	      
	      xo += this.charWidths[chars[i]];
	    }
	    t.end();
	    GL11.glDisable(3553);
	  }
	  
	  public int width(String str)
	  {
	    char[] chars = str.toCharArray();
	    int len = 0;
	    for (int i = 0; i < chars.length; i++) {
	      if (chars[i] == '&') {
	        i++;
	      } else {
	        len += this.charWidths[chars[i]];
	      }
	    }
	    return len;
	  }
}
