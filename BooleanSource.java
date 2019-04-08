import java.util.*;
/**
 * Probability Calculator
 *
 * @author (Eli Lainez)
 * @version (10/02/18)
 */
public class BooleanSource
{
    // instance variables - replace the example below with your own
    private double probability;

    /**
     * Constructor for objects of class BooleanSource
     */
    public BooleanSource(double probability)
    {
        this.probability = probability; 
    }

    public boolean query()
    {
        return (Math.random() < probability);
    }
}
