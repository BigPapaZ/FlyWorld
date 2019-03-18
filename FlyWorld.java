/**
 * FlyWorld.java
 * Provides all the functionalites of a World
 * Part of HWK2 Midpoint.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

class FlyWorld
{
    protected int numRows;
    protected int numCols;

    protected GridLocation [][] world;

    protected GridLocation start;
    protected GridLocation goal;

    protected ArrayList<Predator> predators;
    
    protected Fly mosca;

    
    /**
     * Constructor for the FlyWorld class
     * 
     * @param the name of the input file
     */
    public FlyWorld(String fileName)
        {

        File inFile = new File(fileName);
        Scanner input =  null;
    
        //Had to be put to prevent the fnfe errors
        try
            {
            input =  new Scanner(inFile);
            }
            
        catch (FileNotFoundException fnfe)
            {
                System.err.println("Input file not found");    
                System.exit(1);
            }
            
        //Scanning the first line of the file as a string
        String FirstRow=input.nextLine();
        
        //Storing the index of the whitespace to be used to divide the first line un
        int White_space=FirstRow.indexOf(" ");
        

        predators=new ArrayList<Predator>();

        //Storing the number of rows and columns
        numRows=Integer.parseInt(FirstRow.substring(0,White_space));
        numCols=Integer.parseInt(FirstRow.substring(White_space+1, FirstRow.length()));
        
        //Creating the grid
        world=new GridLocation[numRows][numCols];

        //Creating an array with length equal to the number of rows in it
        String[] Remaining_lines=new String[numRows];
        
        //Iterating through the rest of the input file and storing each line into the array
        for(int i=0;i<numRows;i++)
            {
                Remaining_lines[i]=input.nextLine();    
            }
            
        //Iterating through each element of the String array and parsing it to determine each points gridlocation's niche
        for (int i=0; i<numRows; i++)
            {
                for (int x=0; x<numCols; x++)
                {

		    // READER:  No need for substring, charAt is fine
                    if(Remaining_lines[i].substring(x,x+1).equals("s"))
                        {
                            world[i][x]=new GridLocation(i,x);//Initialising a grid location at very point of the grid (aka double array)
                            world[i][x].setBackgroundColor(Color.GREEN);//Setting the home location's color to green
                            start=world[i][x];//Setting the home location
                            mosca=new Fly(start,this);//Putting the fly at the start location
                        }
                    else if(Remaining_lines[i].substring(x,x+1).equals("h"))
                        {
                            world[i][x]=new GridLocation(i,x);// READER: this is repetitive, do it oustside the if/else ifs
                            world[i][x].setBackgroundColor(Color.RED);
                            goal=world[i][x];//Setting the End location
                        }
                    else if(Remaining_lines[i].substring(x,x+1).equals("f"))
                       {
                            world[i][x]=new GridLocation(i,x);
                            Frog A=new Frog(world[i][x],this);//Creating a new Frog Obj.
                            predators.add(A);//Adding the Frog to the predators list
                        }
                    else if(Remaining_lines[i].substring(x,x+1).equals("a"))
                       {
                            world[i][x]=new GridLocation(i,x);
                            Spider A=new Spider(world[i][x],this);//Creating a new Frog Obj.
                            predators.add(A);//Adding the Frog to the predators list
                        }
                    else if(Remaining_lines[i].substring(x,x+1).equals("b"))
                       {
                            world[i][x]=new GridLocation(i,x);
                            Bird A=new Bird(world[i][x],this);//Creating a new Frog Obj.
                            predators.add(A);//Adding the Frog to the predators list
                        }
                    else
                        {
                            world[i][x]=new GridLocation(i,x);
                        }
                }
            }
        }

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows()
    {
    return numRows;
    }

    /**
     * @return int, the number of columns in the world
     */
    public int getNumCols()
    {
    return numCols;
    }

    /**
     * Deterimes if a specific row/column location is 
     * a valid location in the world (i.e., it is not out of bounds)
     *
     * @param r, a row
     * @param c, a column
     *
     * @return boolean
     */
    public boolean isValidLoc(int r, int c)
    {
        if((r<numRows&&r>=0)&&(c<numCols&&c>=0))
            {return true;}
        else
            {return false;}
    }

    /**
     * Returns a specific location based on the given row and column
     *
     * @param r, the row
     * @param c, the column
     *
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c)
    {
    return world[r][c];
    }

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation()
    {
    return mosca.getLocation();
    }

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     *
     * @param direction, the direction, N,S,E,W to move
     *
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return -1
     */
    public int moveFly(int direction)
    {
	// READER:  This is a bit of a mess.  You were given comments about this
	// At the midway check and didn't change anything.
	// There is no need for variable a
        mosca.update(direction);
        GridLocation m=mosca.getLocation();
        //int a=1;
        if (m.equals(goal)){return FlyWorldGUI.ATHOME;}

        else
        {
            //Iterating through each frog and checking if they can eat the fly
            for(Predator s: predators)
            {
                if(s.eatsFly()) {//a=0;}
		    return FlyWOrld.EATEN;  // READER: added this line
            }
        }

        //Had to put the return statements outside the for loop
        //if(a==0) {return FlyWorldGUI.EATEN;}
        else{return FlyWorldGUI.NOACTION;}
    }
    
    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     *
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators()
    {
	// READER:  Same thing here regarding variable x as above for variable a
        int x=1;
        
        //Iterating through each frog in the array
        for(Predator s: predators)
        {   
            s.update();//Changes the frog's position
            
            //Checking to see if the frog can eat the fly from its new location
            if(s.eatsFly())
            {
                x=0;
            }
        }
        
        //had to put the return statements ousside of the for loop
        if(x==0){return true;}
        else{return false;}
    }
}
