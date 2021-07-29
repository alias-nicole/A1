package com.mycompany.a1;

public abstract class FixedGameObject extends GameObjects
{
	//this is for fixed in place GameObjects(Flag, FoodStation)
    public FixedGameObject(float x, float y)
    {
        super.setLocation(x,y);
    }
   

}

