/**
 * Fly.java
 * Provides all the functionalites of a Fly
 * Part of HWK2 Midpoint.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains several methods that aid in the<br>
 * displaying and movement of Mosca
 */
public class Fly extends Creature
{
    private static final String imgFile = "fly.jpg";
    /**
     * Creates a new Fly object.<br>
     * The image file for a fly is fly.jpg<br>
     *
     * @param loc, a GridLocation
     * @param fw, the FlyWorld the fly is in
     */
    public Fly(GridLocation loc, FlyWorld fw)
    {
        super(loc,fw,imgFile, false);
    }

    
//    @Override
    /**
     * Mosca is not a predator so always return false
     */
//READER: In order to fix your Creature.isPredator(), I got rid of this     
//    public boolean isPredator()
//    {return false;}
  
    
    /**
     * This method updates the fly's position<br>
     * The fly can move in one of the four cardinal (N, S, E, W)<br>
     * directions.  You need to make sure that the new location<br>
     * is within the bounds of the world before setting the new<br>
     * location of the fly.
     *
     * @param direction, one of the four cardinal directions
     */
    public void update(int direction)
    {
        GridLocation mosca=this.getLocation();
        int mosca_row=mosca.getRow();
        int mosca_col=mosca.getColumn();
        
        //At first the direction to be moved is determined. If the direction is valid then the fly object is moved
        if(direction==FlyWorldGUI.NORTH)
        {
        if(world.isValidLoc(mosca_row-1,mosca_col))
            {
                this.setLocation(world.getLocation(mosca_row-1,mosca_col));
            }
        }
        else if(direction==FlyWorldGUI.SOUTH)
        {
            if(world.isValidLoc(mosca_row+1,mosca_col))
                {
                    this.setLocation(world.getLocation(mosca_row+1,mosca_col));
                }
        }
        else if(direction==FlyWorldGUI.EAST)
        {
            if(world.isValidLoc(mosca_row,mosca_col+1))
                {
                    this.setLocation(world.getLocation(mosca_row,mosca_col+1));
                }
        }
        else if(direction==FlyWorldGUI.WEST)
        {
            if(world.isValidLoc(mosca_row,mosca_col-1))
                {
                    this.setLocation(world.getLocation(mosca_row,mosca_col-1));
                }
        }
    }
}
