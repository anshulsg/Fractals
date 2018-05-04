import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;


public class MSet {
	public static void main(String[] args) throws Exception
	{
		int width=1*1920, height=1*1080, max=400;
		BufferedImage br= new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				double c_re,c_im,mag, x, y;
				c_re= (col - width/2)*4.9/width ;
				c_im= (row- height/2)*2.8/height;
				x=0;y=0;
				mag= 0;
				int iterations=0;
				while(iterations<max && mag<4)
				{
					double x_temp= x*x -y*y + c_re;
					y= 2*x*y + c_im;
					x= x_temp;
					mag= x*x + y*y;
					iterations++;
				}
				if(iterations==max)
				{
					br.setRGB(col, row, 0 );
				}
				else
				{
					int color= (iterations*1000);
					br.setRGB(col, row, color);
				}
				
			}
		}
		ImageIO.write(br, "png", new File("mandelbrot.png"));
	}
}
