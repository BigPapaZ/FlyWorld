/**
 * Bird.java
 * Provides all the functionalites of a Bird
 * Part of HWK2.
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
 * Provides all the functionalites for a bird object
 */
public class Bird extends Predator
{
    /**
     * Creates a new Bird object.<br>
     * The image file for a Bird is bird.jpg<br>
     * 
     * @param loc, a GridLocation class object
     * @param fw, a FlyWorld class object in which the Bird is in
     */
    private static final String imgFile = "bird.jpg";
    
    public Bird(GridLocation loc, FlyWorld fw)
    {
        //READER: In order to fix your Creature.isPredator(), I changed this. It inherets predator, so no need to pass the boolean, we know it's a predator and that's expressed by our Predator constructor definition.
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

	// READER:  This method only generates possible legal moves
	//          There should be no random choosing here
	//          All locations without a predator already on them
	//          is a possible legal move
        ArrayList<GridLocation> stuff = new ArrayList<GridLocation>();
        GridLocation m=this.getLocation();
        Random rand=new Random();
        
        int nrow=world.getNumRows();
        int ncol=world.getNumCols();
        
        int x_new=rand.nextInt(nrow);
        int y_new=rand.nextInt(ncol);
        
        int x_bird=m.getRow();
        int y_bird=m.getColumn();
        
        
        //Generating a random coordinate for the Bird to move.....
        while(world.getLocation(x_new,y_new).hasPredator()==true||(x_bird==x_new&&y_bird==y_new))
        {
            x_new=rand.nextInt(nrow);
            y_new=rand.nextInt(ncol);
        }

        stuff.add(world.getLocation(x_new,y_new));
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
        //Determining if the fly can be eaten or not
        if(this.getLocation().equals(world.getFlyLocation()))
        {return true;}
        else{return false;}
    }
}
