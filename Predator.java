/**
 * Predator.java
 * Provides all the functionalites common to the predator class
 * Part of HWK2.
 */

import java.util.ArrayList;
import java.util.Random;


/**
 * Provides all the functionalites common to the subclasses of the predator typpe type
 */

public abstract class Predator extends Creature
{
    /**
     * Constructor for the Abstract class Predator
     * 
     * @param loc, a GridLocation class object
     * @param fw, a FlyWorld class object in which the predator is in
     */
     
     
     //READER: In order to fix your Creature.isPredator(), I got rid the additional boolean parameter 't' you had for the Predator constructor. A Predator shouldn't be able to not be a Predator, which is why I removed that.
    public Predator(GridLocation loc, FlyWorld fw, String image)
    {
    	//READER: In order to fix your Creature.isPredator(), I got rid of this
        //super(loc,fw,image,t);
        super(loc, fw, image, true);
    }
    
    public abstract ArrayList<GridLocation> generateLegalMoves();
    public abstract boolean eatsFly();

    public void update()
    {
        //Making an ArrayList for the legal moves for a predator
        ArrayList<GridLocation> list=this.generateLegalMoves();

        Random rand=new Random();
        int list_size=list.size();
        //Randomly selecting a possible grid location to move the predator to if moving is possible
        if (list_size!=0)
        {
        int y=rand.nextInt(list_size);
        
        //Moving the frog to the new location
        this.setLocation(list.get(y));
        }
    }

//READER: In order to fix your Creature.isPredator(), I got rid of this    
//    @Override
//    public boolean isPredator()
//    {return true;}
}
