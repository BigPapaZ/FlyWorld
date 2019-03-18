/**
 * Spider.java
 * Provides all the functionalites of a Spider
 * Part of HWK2.
 */
//READER: Unneeded import
//import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
/**
 * Provides all the functionalites for a spider object
 */
public class Spider extends Predator
{
    /**
     * Creates a new Spider object.<br>
     * The image file for a Spider is spider.jpg<br>
     * 
     * @param loc, a GridLocation class object
     * @param fw, a FlyWorld class object in which the Spider is in
     * 
     */
    private static final String imgFile = "spider.jpg";
    public Spider(GridLocation loc, FlyWorld fw)
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
        GridLocation m=this.getLocation();
        
        int row_fly=world.getFlyLocation().getRow();
        int col_fly=world.getFlyLocation().getColumn();
        
        int row_spi=m.getRow();
        int col_spi=m.getColumn();
        
        //if the rows of the Spider and the fly are equal
        if(row_spi==row_fly)
        {  //Determining whether the fly is located in relation to the spider
            if (col_spi>col_fly)
            {
                if (world.getLocation(row_spi,col_spi-1).hasPredator()==false) //Determining whether a particular position for the spider to move has a predator in or it
                {stuff.add(world.getLocation(row_spi,col_spi-1));}
            }
            
            if (col_spi<col_fly)
            {
                if (world.getLocation(row_spi,col_spi+1).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi,col_spi+1));}
            }
        }
        
        //if the cols of the Spider and the fly are equal
        else if(col_spi==col_fly)
        {
            if (row_spi>row_fly)
            {
                if (world.getLocation(row_spi-1,col_spi).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi-1,col_spi));}
            }
            
            if (row_spi<row_fly)
            {
                if (world.getLocation(row_spi+1,col_spi).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi+1,col_spi));}            
            }            
        }
        
        //if neither the rows nor the column of the Spider and the fly are equal
        else
        {   
            
            //Locating the fly and determining the various ways to move towards it
            if(col_spi<col_fly&&row_spi<row_fly)
            {
                if(world.getLocation(row_spi+1,col_spi).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi+1,col_spi));}
                
                if(world.getLocation(row_spi,col_spi+1).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi,col_spi+1));}
            }
            else if(col_spi>col_fly&&row_spi>row_fly)
            {
                if(world.getLocation(row_spi-1,col_spi).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi-1,col_spi));}
                
                if(world.getLocation(row_spi,col_spi-1).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi,col_spi-1));}                
            }
            else if(col_spi<col_fly&&row_spi>row_fly)
            {
                if(world.getLocation(row_spi-1,col_spi).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi-1,col_spi));}

                if(world.getLocation(row_spi,col_spi+1).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi,col_spi+1));}                
            }
            else if (col_spi>col_fly&&row_spi<row_fly)
            {
                if(world.getLocation(row_spi+1,col_spi).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi+1,col_spi));}

                if(world.getLocation(row_spi,col_spi-1).hasPredator()==false)
                {stuff.add(world.getLocation(row_spi,col_spi-1));}                
            }
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
        //Determining if the fly can be eaten or not
        if(this.getLocation().equals(world.getFlyLocation()))
        {return true;}
        else{return false;}
    }
}
