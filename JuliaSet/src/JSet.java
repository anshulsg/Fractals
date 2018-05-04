import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

public class JSet {
	
	
	public static void main(String[] args)throws Exception {
		
		double const_r,const_img, z_r, z_img;
		const_r= -0.68;
		const_img= 0.52;
		int width= 1920;
		int height= 1080;
		BufferedImage br= new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		int color;
		for(int i=0; i<height; i++)
		{
			double y= 2.8*(i-height/2)/height;
			for(int j=0; j<width; j++)
			{
				double x= 4.9*(j-width/2)/width;
				z_r=x;
				z_img=y;
				double mag= x*x+y*y;
				int iter=0;
				while(iter<1000 && mag<6)
				{
					double x_temp= z_r*z_r- z_img*z_img + const_r;
					z_img = 2*z_r*z_img + const_img;
					z_r= x_temp;
					iter++;
					mag= z_r*z_r+ z_img*z_img;
				}
				if(iter==1000)
					color=0;
				else
				{
					color= (iter+5)*1000;
				}
				br.setRGB(j, i, color);
			}
		}
		ImageIO.write(br, "png", new File("julia.png"));
	}

}
