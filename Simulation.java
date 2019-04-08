import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *This is a simulation of an airport with one landing strip
 *
 * @author (Eli Lainez)
 * @version (10/01/2018)
 */
public class Simulation
{
    /**
     * This main method activates the Airplane Simulation with user chosen parameters
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter minutes to land: ");
        double minutesToLand = input.nextDouble(); 

        System.out.print("Enter amount of minutes to take off: ");
        double minutesToTakeOff = input.nextDouble();

        System.out.print("Enter probability of arrival during a minute: ");
        double probabilityOfArrival = input.nextDouble();

        System.out.print("Enter average amount of time between planes to land: ");;
        double timeBetweenPlanesToLand = input.nextDouble();

        System.out.print("Enter probability of departure during a minute: ");
        double probabilityOfDeparture = input.nextDouble();

        System.out.print("Enter Average amount of time between planes to take off: ");
        double timeBetweenPlanesToTakeoff = input.nextDouble();

        System.out.print("Enter maximum amount of time in the air before crashing: ");
        double timeInAirBeforeCrashing = input.nextDouble();

        System.out.print("Enter total simulation runtime: "); 
        double runTime = input.nextDouble();

        ActivateSimulation(minutesToLand,minutesToTakeOff,
            probabilityOfArrival,timeBetweenPlanesToLand, probabilityOfDeparture, 
            timeBetweenPlanesToTakeoff,timeInAirBeforeCrashing,runTime);
    }

    public static void ActivateSimulation(double minutesToLand,double minutesToTakeOff,
    double probabilityOfArrival, double timeBetweenPlanesToLand,double probabilityOfDeparture, 
    double timeBetweenPlanesToTakeoff, double timeInAirBeforeCrashing, double runTime){
        Queue<Plane> arrivingPlanes = new LinkedList<Plane>();
        Queue<Plane> departingPlanes = new LinkedList<Plane>();
        double next;
        BooleanSource arrival = new BooleanSource(probabilityOfArrival);
        BooleanSource departure = new BooleanSource(probabilityOfDeparture); 
        Boolean isEmpty = true;
        Averager arrivalTime = new Averager(); 
        Averager departureTime = new Averager(); 
        double currentSecond;
        int planesCrashed = 0;
        int planesLeft = 0;
        int planesArrived = 0;
        for(currentSecond = 0; currentSecond < runTime; currentSecond++)
        {
            if(arrival.query())
                arrivingPlanes.add(new Plane(minutesToLand, currentSecond));
            if(departure.query())
                departingPlanes.add(new Plane(minutesToTakeOff, currentSecond));
            //let planes depart
            if((isEmpty) && (arrivingPlanes.isEmpty()) && (!departingPlanes.isEmpty()))
            {
                if(departingPlanes.peek().getTimeLeftToLand() == 0)
                {
                    departureTime.addNumber(currentSecond - departingPlanes.peek().getArrivalTime());
                    departingPlanes.remove();
                    planesLeft++;
                }
                else
                {
                    departingPlanes.peek().reduceTimeToLand();
                }
            } 
            //let planes land
            else if(isEmpty && !arrivingPlanes.isEmpty())
            {
                if(arrivingPlanes.peek().getTimeLeftToLand() == 0)
                {
                    if(arrivingPlanes.peek().getTimeInAir() >= timeInAirBeforeCrashing)
                    {
                        planesCrashed++;
                    }
                    else {
                        arrivalTime.addNumber(currentSecond - arrivingPlanes.peek().getArrivalTime());
                        planesArrived++;
                    }
                    arrivingPlanes.remove();
                }
                else
                {   
                    arrivingPlanes.peek().reduceTimeToLand();
                }
            }
            for(Plane iterator: arrivingPlanes)
            {
                iterator.increaseTimeInAir();
            }
        }
        //Check to see how many planes left in the queue crashed
        for(Plane iterator: arrivingPlanes)
        {
            if(iterator.getTimeInAir() >= timeInAirBeforeCrashing){
                planesCrashed++;
            }
        }
        
        System.out.println("Number of planes taken off: " + planesLeft);
        System.out.println("Number of planes landed: " + planesArrived);
        System.out.println("Number of planes crashed: " + planesCrashed);
        System.out.println("Average waiting time for taking off: " + departureTime.average());
        System.out.println("Average waiting time for landing: " + arrivalTime.average());
    }
}
