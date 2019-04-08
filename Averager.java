
/**
 * Calculates the average 
 *
 * @author (Eli Lainez)
 * @version (10/3/18)
 */
public class Averager
{
    // instance variables - replace the example below with your own
    private int count;
    private double sum; 
    /**
     * Constructor for objects of class Averager
     */
    public Averager()
    {
        count = 0;
        sum = 0;
    }

    /**
     * add a number to the total average 
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addNumber(double value)
    {
        count++;
        sum+= value; 
    }
    
    public double average(){
        if(count == 0){
            return 0;
        }
        else return sum/count;
    }
    
    public int getCount(){
        return count;
    }
}
