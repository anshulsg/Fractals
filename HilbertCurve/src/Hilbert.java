import java.applet.Applet;
import java.awt.*;

@SuppressWarnings("serial")
public class Hilbert extends Applet {

	String XStr,YStr,FStr;
	int len;
	int angle;
	int x,y;
	int depth;
	Graphics prim= null;
	public void init()
	{
		prim= getGraphics();
		len=512;
		depth=8;
		XStr= "-YF+XFX+FY-";
		YStr= "+XF-YFY-FX+";
		FStr= "F";

		this.setSize(600, 600);
		len= len/(int)Math.pow(2, depth);
	}
	public void start()
	{
		angle=0;
		x=44;
		y=600-44;
		
	}
	public void paint(Graphics G)
	{
		produceString("X", depth);
	}
	void produceString( String str, int order)
	{
		for(int i=0;i<str.length();i++)
		{
			char test= str.charAt(i);
			switch(test)
			{
			case '+':
				angle-=90;
				if(angle==-270)
					angle=90;
				break;
			case '-':
				angle+=90;
				if(angle==270)
					angle=-90;
				break;
			case 'F':
				if(order>0)
					produceString(FStr, order-1);
				else
					drawLine();
				break;
			case 'X':
				if(order>0)
					produceString(XStr, order-1);
				break;
			case 'Y':
				if(order>0)
					produceString(YStr, order-1);
				break;
			}
		}
	}
	void drawLine(){
		int x2,y2;
		x2=x;
		y2=y;
		switch(angle)
		{
		case -180:
			x=x-len;
			break;
		case -90:
			y=y+len;
			break;
		case 0:
			x=x+len;
			break;
		case 90:
			y=y-len;
			break;
		case 180:
			x=x-len;
			break;
		}
		prim.drawLine(x2, y2, x, y);
	}
}
