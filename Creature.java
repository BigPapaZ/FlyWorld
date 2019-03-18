/**
 * Creature.java
 * The super class for the prey and the predators
 * Provides all the functionalites of a Creature
 * Part of HWK2 final check.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

/**
 * Provides all the functionalites common to the subclasses of the creature type
 */
public class Creature
{
    protected GridLocation location;
    protected FlyWorld world;
    private String imgFile;
    protected BufferedImage image;
    private boolean predatorStatus;
    /**
     * Creates a new creature object.<br>
     *
     * @param loc, a GridLocation
     * @param fw, the FlyWorld the creature is in
     */
    public Creature(GridLocation loc, FlyWorld fw, String img, boolean t)
    {
        location = loc;
        world=fw;
        imgFile=img;
        predatorStatus = t;
        
        try {
            image=ImageIO.read(new File(imgFile));
            }
        catch(IOException ioe)   {
            System.out.println("Unable to read the ImgFile:"+imgFile);
        }

        location.setCreature(this);
    }
    
    
    /**
     * This method should:<br>
     * 1. Remove the fly from its current location<br>
     * 2. Add it to the new location<br>
     * 3. Update the current location to be the new location
     *
     * @param newLocation, a new GridLocation
     */
    public void setLocation(GridLocation newlocation)
    {
        this.getLocation().removeCreature();
        location=newlocation;
        this.getLocation().setCreature(this);
    }

    public BufferedImage getImage()
    {
        return image;
    }

    
    public GridLocation getLocation()
    {
        return location;
    }
    
    
    /**
     * Created to determine the niche of a Creature.
     * Created here arbitararily, so that it could be over ridden in the proper subclasses.
     */
    //READER: Changed this to return predatorStatus isntead of always true.
    public boolean isPredator()
    {return predatorStatus;}
}
