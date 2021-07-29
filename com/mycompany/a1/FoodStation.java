package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends FixedGameObject
{
    private int capacity;
    
    //constructor for FoodStation, sets location, size, capacity, and color
    public FoodStation(float x, float y, int size)
    {
        super(x,y);
        this.setSize(size);
        this.setCapacity(size);
        this.setColor(ColorUtil.GREEN);
    }
    //@param capacity
    public void setCapacity(int c)
    {
        this.capacity = c;
    }
    //@return capacity
    public int getCapacity()
    {
        return this.capacity;
    }

    //toString method to return the food station coordinates & capacity
    public String toString()
    {
        String objDesc = super.toString();
        String localDesc = "Capacity = " + capacity;
        return "FoodStation: " + objDesc + localDesc;
    }
}
