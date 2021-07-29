package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld
{
    private int t;
    private int lives;
    private ArrayList<GameObjects> worldObjects;
    Random r = new Random();
    Ant ant = new Ant(0,0);
    private int lastFlag = 4;
    private static final int MAX_WORLD_HEIGHT = 1000;

    public void init()
    {
        //code here to create the
        //initial game objects/setup
        lives = 3;
        t = 0;
        worldObjects = new ArrayList<GameObjects>();
        addAnt();
        addFlags();
        for (int i = 0; i < 3; i++ )
        {
	        addFoodStations();
	        addSpiders();
        }
        

    }

    //additional methods here to manipulate
    //world objects and related game state data

    public float randCoord()
    {
        int randNum = r.nextInt(MAX_WORLD_HEIGHT + 1);
        return randNum;
    }
    public int randSize()
    {
        //size is between 10 and 50
        int randNum = r.nextInt((50-10)+1) +10;
        return randNum;
    }
    int randSpeed()
    {
        int randNum = r.nextInt((10-5) + 1) +5;
        return randNum;
    }
    public int randDirection()// Generating the random number between 0 and 359
    {
        int randomNum =  r.nextInt((359) + 1);
        return randomNum;
    }

    //methods to add GameObjects to the worldObjects array
    public void addAnt()
    {
        worldObjects.add(ant);
    }
    public void addSpiders()
    {
        worldObjects.add(new Spider(randCoord(),randCoord(), randSpeed(), 20, randDirection()));
    }
    public void addFoodStations()
    {
        worldObjects.add(new FoodStation(randCoord(),randCoord(), randSize()));
    }
    public void addFlags()
    {
        worldObjects.add(new Flag(0,0,1));
        worldObjects.add(new Flag(150,200,2));
        worldObjects.add(new Flag(230,500,3));
        worldObjects.add(new Flag(700,600,4));

    }
    public void accelerate()
    {
        int speedIncrease = 5;
        ant.setSpeed(ant.getSpeed() + speedIncrease);
        //ant.checkHealthLevel();
        if (ant.getFoodLevel() > 12)
        {
        	System.out.println("Ant's speed has been increased by 5!");
        }
        else
        {
        	System.out.println("Ant's speed has been increased by 2.5!");
        }
    }

    public void brake()
    {
        int speedDecrease = -5;
        ant.setSpeed(ant.getSpeed() + speedDecrease);
        System.out.println("Ant's speed has been decreased by 5!");

    }
    public void left()
    {
        int leftShift = -5;
        ant.changeDirection(leftShift);
        System.out.println("Ant has shifted left by 5 degrees");
    }
    public void right()
    {
        int rightShift = 5;
        ant.changeDirection(rightShift);
        System.out.println("Ant has shifted right by 5 degrees");
    }
    
    //every clock tick...
    public void clockTick()
    {
        t++;
        for (GameObjects o: worldObjects)
        {
        	//if the GameObject is a Spider
            if (o instanceof Spider)
            {
            	//if the spider is within world boundaries,
            	//it shifts direction between -5 and 5 degrees
            	if (  o.getX()  < 1000 &&  o.getY() < 1000 )
            	{
            		((Spider) o).setNewDirection();
            	}
            	//otherwise, spider reverses direction
            	else
            	{
            		((Spider) o).reverseDirection();
            	}
            }
            //if the GameObject is Moveable
            if (o instanceof MoveableGameObject)
            {
            	//moveable game objects move
                ((MoveableGameObject) o).move();
            }
        }
        
        //ant's food level is reduced by its food consumption rate
        ant.reduceFoodLevel();
        ant.checkHealthLevel();
        //if ant health level is 0, 1 (out of 3) life is lost
        if (ant.getHealthLevel() == 0 || ant.getFoodLevel() <= 0 )
        {
        	lives -=1;
        	if (lives == 0)
        	{
        		 System.out.println("You are out of lives, game over!");
        		 System.exit(0);
        	}
             
        }
        if(ant.getLastFlagReached() == lastFlag)
        {
                System.out.println("Congratulations, you have found all of the flags! Total time: " + this.t);
        }
        
        System.out.println("Time has increased by 1 tick");

    }
    
    public void flagCollision(int x)
    {
        System.out.println("Colliding with flag " + x);
        if ((x-ant.getLastFlagReached() == 1))
        {
            ant.setLastFlagReached(x);
            System.out.println("Collision with Flag #" + x + " has occurred");
        }
    }
    public void foodStationCollision()
    {
        GameObjects foodStation = null;
        for(GameObjects o : worldObjects)
        {
            if(o instanceof FoodStation)
            {
                //if the FoodStation object has >0 capacity,
                //assign it to local var
                if(((FoodStation) o).getCapacity() != 0)
                {
                    foodStation = o;
                }
            }
        }
        
        //set ant's new food level to the capacity of the FoodStation added to its current level
        ant.setFoodLevel(ant.getFoodLevel() + ((FoodStation) foodStation).getCapacity());
        //set new capacity of FoodStation to zero
        ((FoodStation) foodStation).setCapacity(0);
        //change food station color to light green
        ((FoodStation) foodStation).setColor(ColorUtil.rgb(0, 120, 0));
        worldObjects.add(new FoodStation(randCoord(), randCoord(), randSize()));
        System.out.println("Ant has collided with Food Station");

    }
    public void spiderCollision()
    {
        ant.collisionWithSpider();
        System.out.println("Ant and spider have collided.");
    }
    public void display()
    {
        System.out.println("\nNumber of lives left is: " + ant.getHealthLevel() );
        System.out.println("Current value of clock is: " + t);
        System.out.println("The highest flag number reached is: " + ant.getLastFlagReached());
        System.out.println("Food level of ant is: " +  ant.getFoodLevel());

    }
    public void map()
    {
        System.out.println();
        for(GameObjects o: worldObjects)
        {
            System.out.println(o.toString());
        }

    }

}