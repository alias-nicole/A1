package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;

public class Spider extends MoveableGameObject implements IMoveable
{
    Random r = new Random();
    private static final int MAX_DEGREES = 360;
    
    //constructor for spider class, sets location, size, speed, direction, and color
    public Spider(float x, float y, int speed, int size, int direction)
    {
        super(direction);
        this.setLocation(x,y);
        this.setSize(size);
        this.speed = speed;
        this.setColor(ColorUtil.BLACK);

    }
    
    //spider has a random heading at time of instantiation
    public void randomDirection()
    {
        this.setDirection(r.nextInt(MAX_DEGREES-1));
    }
    
    public void setNewDirection()
    {
    	this.setDirection(r.nextInt(5+5)-5);
    }
    //@return direction
    public int getDirection()
    {
    	return this.direction;
    }
    public void reverseDirection()
    {
    	this.setDirection(this.getDirection() - 180);
    }
    //public void setSize(int size) {}
    //public void setColor(int color) {}

    public String toString()
    {
        return "Spider:" + super.toString();
    }


}