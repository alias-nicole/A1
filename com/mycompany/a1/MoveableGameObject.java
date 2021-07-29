package com.mycompany.a1;

public abstract class MoveableGameObject extends GameObjects
{
    protected int speed;
    protected int direction;

    public MoveableGameObject(int direction)
    {
        this.direction = direction;
    }
    
    //move method, inherited by all MoveableGameObjects
    public void move()
    {
        //need to convert polar to Cartesian
        //Speed = radius (1 unit/tick)
        float r = this.getSpeed();
        //North = 0 degrees
        double theta = Math.toRadians(90-this.getDirection());

        float cx = (float) (r * Math.cos(theta));
        float cy = (float) (r * Math.sin(theta));


        float newX = this.getX() + cx;
        float newY = this.getY() + cy;

        this.setLocation(newX, newY);

    }

    //getters and setters
    //@return speed
    public int getSpeed()
    {
        return this.speed;
    }
    //@param newSpeed
    public void setSpeed(int newSpeed)
    {
        this.speed = newSpeed;
    }
    //@return direction
    public int getDirection()
    {
        return this.direction;
    }
    //@param newDirection
    public void setDirection(int newDirection)
    {
        this.direction = newDirection;
    }

    public String toString()
    {
        String s = super.toString();
        String ss = " direction= " + direction + " speed = " + speed;
        return s + ss;
    }
}