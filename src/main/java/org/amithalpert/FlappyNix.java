package org.amithalpert;// update 1-9-2022
import java.awt.*;

public class FlappyNix extends Thread
{
	  int x,y,width;
	  Color color;
	  GameScreen panel;
	
	public FlappyNix(int x, int y, int width, Color color, GameScreen p)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.color=color;
		this.panel=p;
	}


	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x-width/2,y-width/2,width,width);
	}
	
	public void run()
	{

		
		while(true)
		{
			try {
				Thread.sleep(7);
				} catch (InterruptedException e) {}
			
		}	
	}

	public static double distance(int a,int b)
	{
		return Math.sqrt(Math.pow(a,2.0)+Math.pow(b,2.0));
	}

}
