
/**
 * Plane Object
 *
 * @author (Eli Lainez)
 * @version (10/03/18
 */
public class Plane
{
    // instance variables - replace the example below with your own
    private int timeInAir;//Percentage of fuel remaining; 
    private double timeLeftToLand;
    private double arrivalTime;
    /**
     * Constructor for objects of class Plane
     */
    public Plane(double timeLeftToLand,double arrivalTime)
    {
        timeInAir = 0;
        this.arrivalTime = arrivalTime;
        this.timeLeftToLand = timeLeftToLand; 
    }

    public void increaseTimeInAir()
    {
        timeInAir++;
    }
    
    public int getTimeInAir(){
        return timeInAir;
    }
    
    public double getTimeLeftToLand(){
        return timeLeftToLand;
    }
    
    public double getArrivalTime(){
        return arrivalTime; 
    }
    
    public void reduceTimeToLand()
    {
        timeLeftToLand--;
    }
    
}
