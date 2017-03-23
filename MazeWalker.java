import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MazeWalker extends Actor
{
    private final int NORTH = 270;
    private final int EAST = 0;
    private final int SOUTH = 90;
    private final int WEST = 180;
    
    public MazeWalker ()
    {
       GreenfootImage mazeWalkerImage = new GreenfootImage(40, 40);
       
       mazeWalkerImage.setColor(Color.BLUE);
       mazeWalkerImage.fillRect(0, 0, 40, 40);
       
       setImage(mazeWalkerImage);
       setRotation(90);
    }  
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkWin();
        
        if( wallOnLeft() == true )
        {
            if( canMoveForward() == true ){
                move(1);
            }
            else
            {
              setRotation( getRotation() + 90 );  
            }
        }
        else
        {
           setRotation( getRotation() - 90 );
           
           if( canMoveForward() == true )
           
           move(1);
        }
    }
    
    /**
     * wallOnLeft check whether there is a wall on the player's left
     * 
     * @param there are no parameters
     * @return a boolean stating whether or not there is a wall on the left
     */
    private boolean wallOnLeft()
    {
        int xOffset = 0;
        int yOffset = 0;
        
        boolean wallLeft = false;
        
        if( getRotation() == NORTH )
        {
            xOffset = -1;
        }
        else if( getRotation() == SOUTH )
        {
            xOffset = 1;
        }
        else if( getRotation() == EAST )
        {
            yOffset = -1;
        }
        else
        {
            yOffset = 1;
        }
        
        if( getOneObjectAtOffset( xOffset, yOffset, Wall.class ) != null )
        {
            wallLeft = true;
        }
        return wallLeft;
    }
    
    /**
     * canMoveForward checks whether Player can move forward
     * 
     * @param There are no parmaters
     * @return a boolean stating whether or not we can move forward
     */
    private boolean canMoveForward()
    {
        int xOffset = 0;
        int yOffset = 0;
        
        boolean moveForward = false;
        
       
        if( getRotation() == NORTH )
        {
            yOffset = -1;
        }
        else if( getRotation() == SOUTH )
        {
            yOffset = 1;
        }
        else if( getRotation() == EAST )
        {
            xOffset = 1;
        }
        else
        {
            xOffset = -1;
        }
        
        if( getOneObjectAtOffset( xOffset, yOffset, Wall.class ) == null )
        {
            moveForward = true;
        }
        
        return moveForward;
    }
    
    /**
     * checkWin will check if playter has reached WinningSpace and show a
     * message to say the maze has been completed
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkWin()
    {
        if( getOneIntersectingObject(WinningSpace.class) != null )
        {
           getWorld().showText( "MAZE COMPLETED!", getWorld().getWidth()/2, getWorld().getHeight()/2 );
           Greenfoot.stop();
        }
    }
    
}