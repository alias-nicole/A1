package com.mycompany.a1;

import static com.codename1.ui.CN.*;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class Game extends Form
{

    private Form current;
    private GameWorld gw;
    private boolean check = false;

    public Game()
    {
        gw = new GameWorld();
        gw.init();
        play();
    }

    private void play()
    {
        //code here to accept and
        //execute user commands that
        //operate on the game world
        Label myLabel=new Label("Enter a Command:");
        this.addComponent(myLabel);
        final TextField myTextField=new TextField();
        this.addComponent(myTextField);
        this.show();
        myTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String sCommand=myTextField.getText().toString();
                myTextField.clear();
                if(sCommand.length() != 0)
                {
	                if(check==true)	//checks if user wants to quit
	                {
	                	switch(sCommand.charAt(0))
	                	{
	                	case 'y':
	                		System.exit(0);
	                		break;
	                	case 'n':
	                		check = false;
	                		break;
	                		default: 
	                			System.out.println("Enter y or n");
	                	}
	                }
                }
                if(sCommand.length() != 0)
                    switch (sCommand.charAt(0)) {
                        case 'a':
                            gw.accelerate();
                            break;
                        case 'b':
                            gw.brake();
                            break;
                        case 'l':
                            gw.left();
                            break;
                        case 'r':
                            gw.right();
                            break;
                        case 'g':
                            gw.spiderCollision();
                            break;
                        case 'f':
                            gw.foodStationCollision();
                            break;
                        case '1':
                        	gw.flagCollision(1);
                        	break;
                        case '2':
                        	gw.flagCollision(2);
                        	break;
                        case '3':
                        	gw.flagCollision(3);
                        	break;
                        case '4':
                        	gw.flagCollision(4);
                        	break;
                        case '5':
                        	gw.flagCollision(5);
                        	break;
                        case '6':
                        	gw.flagCollision(6);
                        	break;
                        case '7':
                        	gw.flagCollision(7);
                        	break;
                        case '8':
                        	gw.flagCollision(8);
                        	break;
                        case '9':
                        	gw.flagCollision(9);
                        	break;
                        case 't':
                            gw.clockTick();
                            break;
                        case 'd':
                            gw.display();
                            break;
                        case 'm':
                            gw.map();
                            break;
                        case 'x':
                        	System.out.println("Do you want to quit?");
                        	check = true;
                        case 'y':
                        	if (check)
                        	{
                        		System.exit(1);
                        	}
                        case 'n':
                        	if (check)
                        	{
                        		System.exit(0);
                        	}
                        default:
                            System.out.println("Invalid keyboard input");

                    }
                } //actionPerformed
            } //new ActionListener()
            ); //addActionListener
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("Hi World", BoxLayout.y());
        hi.add(new Label("Hi World"));
        hi.show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
