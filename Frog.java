/**
 * Frog.java
 * Provides all the functionalites of a Frog
 * Part of HWK2 Midpoint.
 */
 
//READER: Unneeded import
//import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

 /**
  * Contains several methods that aid in the displaying and movement and other functionalities of Frog
  */
public class Frog extends Predator
{
    
    private static final String imgFile = "frog.jpg";
    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     * 
     * @param loc, a GridLocation class object
     * @param fw, a FlyWorld class object in which the Frog is in
     * 
     */
    public Frog(GridLocation loc, FlyWorld fw)
    {
    	//READER: In order to fix your Creature.isPredator(), I changed this. It inherets predator, so no need to pass the boolean.
        //super(loc,fw,imgFile, false);
        super(loc, fw, imgFile);
    }

    /**
     * This method should:<br>
     * 1. Remove the frog from its current location<br>
     * 2. Add it to the new location<br>
     * 3. Update the current location to be the new location
     *
     * @param newLocation, a new GridLocation
     */
     
     //READER: In order to fix your Creature.isPredator(), I got rid of this
//    public boolean isPredator()
//    {
//    return true;
//    }

    /**
     * Generates a list of ALL possible legal moves for a frog.<br>
     * Frogs can move one space in any of the four cardinal<br>
     * directions as long as it does not move them off the board AND<br>
     * it the square does not already have a frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a square already or not.
     *
     * @return a list of legal grid locations for the frog to move to
     */
    public ArrayList<GridLocation> generateLegalMoves()
    {
        ArrayList<GridLocation> stuff = new ArrayList<GridLocation>();
        GridLocation frg=this.getLocation();
        int x_frog=frg.getRow();
        int y_frog=frg.getColumn();
        
        //Checking to see that whether the 4 cardinal gridLocation around the frogs current location are valid or not
        //Does that by basically calling the isValidLoc function and seeing that no adjacent blocs have frogs in them
        
        if(world.isValidLoc(x_frog-1,y_frog) && (world.getLocation(x_frog-1,y_frog).hasPredator()==false))
        {
            stuff.add(world.getLocation(x_frog-1,y_frog));
        }
        if(world.isValidLoc(x_frog+1,y_frog) && (world.getLocation(x_frog+1,y_frog).hasPredator()==false))
        {
            stuff.add(world.getLocation(x_frog+1,y_frog));
        }
        if(world.isValidLoc(x_frog,y_frog-1) && (world.getLocation(x_frog,y_frog-1).hasPredator()==false))
        {
            stuff.add(world.getLocation(x_frog,y_frog-1));
        }
        if(world.isValidLoc(x_frog,y_frog+1) && (world.getLocation(x_frog,y_frog+1).hasPredator()==false))
        {
            stuff.add(world.getLocation(x_frog,y_frog+1));
        }
        return stuff;
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is 0 or 1 spaces away in one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {

        //Storing the current coordinates of the Fly
        int r=world.getFlyLocation().getRow();
        int c=world.getFlyLocation().getColumn();

        //Generating the cardinal Grid locations
        GridLocation upper=new GridLocation(r-1,c);
        GridLocation lower=new GridLocation(r+1,c);
        GridLocation right=new GridLocation(r,c+1);
        GridLocation left=new GridLocation(r,c-1);
        
        
        //Determining if the fly can be eaten or not
        if(this.getLocation().equals(world.getFlyLocation()) || this.getLocation().equals(upper) || this.getLocation().equals(lower) || this.getLocation().equals(right) || this.getLocation().equals(left))
        {return true;}
        else{return false;}
    }
}
    
