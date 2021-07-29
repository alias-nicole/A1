package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Ant extends MoveableGameObject implements IMoveable, ISteerable
{
    private int maximumSpeed = 50;
    private int steeringDirection = 5;
    private int foodLevel = 25;
    private int healthLevel = 10;
    private int lastFlagReached = 1;
    private int foodConsumptionRate = 2;
    private boolean isDead = false;
    
    //constructor for Ant, sets location, size, speed, and color
    public Ant(float x, float y)
    {
        super(0);
        this.setLocation(x,y);
        this.setSize(40);
        this.setSpeed(0);
        this.setColor(ColorUtil.BLACK);

    }
    
    //change direction ant is heading in
    //@param newDirection
    public void changeDirection(int newDirection)
    {
        this.setDirection(this.getDirection() + newDirection);

    }
    
    //change ant's speed, making sure it's less than max speed
    //@param speed
    public void setAntSpeed(int speed)
    {
        if (speed < this.maximumSpeed)
        {
            this.speed = speed;
        }
        else
        {
            System.out.println("Ant cannot go beyond maximum speed!");
        }
    }
    
    //ant speed is updated after colliding with a spider or having low food level
    public void updateAntSpeed()
    {
    	if (this.getSpeed() < this.getMaximumSpeed() *(10-this.getHealthLevel()))
    	{
    		
    	}
    	else
    	{
    		this.setSpeed(this.getMaximumSpeed() * (this.getHealthLevel()/2));
    	}
    	
    	this.checkHealthLevel();


    }

    //Getters and Setters
    //@return steeringDirection
    public int getSteeringDirection() { return steeringDirection; }
    //@param newSteeringDirection
    public void setSteeringDirection(int newSteeringDirection) { this.steeringDirection = newSteeringDirection; }
    //@return maximumSpeed
    public int getMaximumSpeed() { return maximumSpeed; }
    //@param maximumSpeed
    public void setMaximumSpeed(int maxSpeed) { this.maximumSpeed = maxSpeed; }
    //@return foodLevel
    public int getFoodLevel() { return foodLevel; }
    //@param foodLevel
    public void setFoodLevel(int foodLevel) { this.foodLevel = foodLevel; }
    //@return foodConsumptionRate
    public int getFoodConsumptionRate() { return foodConsumptionRate; }
    //@param foodConsumptionRate
    public void setFoodConsumptionRate(int rate) { this.foodConsumptionRate = rate; }
    //@return healthLevel
    public int getHealthLevel() { return healthLevel; }
    //@param healthLevel
    public void setHealthLevel(int healthLevel) { this.healthLevel = healthLevel; }
    //@return lastFlagReached
    public int getLastFlagReached() { return lastFlagReached; }
    //@param lastFlagReached	
    public void setLastFlagReached(int lastFlag) { this.lastFlagReached = lastFlag; }
    //@return isDead
    public boolean getIsDead() { return isDead; }
    //@param isDead
    public void setIsDead(boolean isDead) { this.isDead = isDead; }

    //ant's speed reduces depending on healthLevel and foodLevel
    //collisions with spiders reduce healthLevel
    //if ant's foodLevel or healthLevel is zero it cannot move
    public void checkHealthLevel()
    {
    	//this.updateAntSpeed();
        if (this.getHealthLevel() == 0 ) {this.setSpeed(0);}
        if (this.getFoodLevel() <= 0 ) {this.setSpeed(0);}
        //if ant's food level is half or less, it's speed is reduced by 1/2
        if (this.getFoodLevel() <= 12) {this.setFoodLevel(this.getFoodLevel()/2);}
        //if ant's speed is 0, that means it's health and food is 0
        //means game is over
        if (this.getSpeed() == 0) {this.isDead = true;}   
    }

    //health and speed of ant are reduced after collision
    public void collisionWithSpider()
    {
        this.healthLevel -=1;
        this.speed -=5;
        //this.updateAntSpeed();
    }

    //method to reduce ant's food level by foodConsumptionRate
    public void reduceFoodLevel()
    {
        this.foodLevel = this.foodLevel - this.foodConsumptionRate;
        if(this.foodLevel <= 0)
        {
            this.setAntSpeed(0);
            this.isDead = true;
        }
    }

    //after the ant's health level reaches 0, ant is reset
    public void reset()
    {
        this.setColor(ColorUtil.LTGRAY);
        this.setDirection(0);
        this.setSpeed(0);
        this.setLastFlagReached(1);
        this.setLocation(0,0);
        this.setFoodLevel(25);
        this.setHealthLevel(10);
        this.setIsDead(false);

    }

    public String toString()
    {
        String objDesc = super.toString();
        String localDesc = " maxSpeed=" + maximumSpeed + " steeringDirection=" + getSteeringDirection() +
                " foodLevel=" + foodLevel  + " healthLevel=" + healthLevel;
        return "Ant:" + objDesc + localDesc;
    }

}