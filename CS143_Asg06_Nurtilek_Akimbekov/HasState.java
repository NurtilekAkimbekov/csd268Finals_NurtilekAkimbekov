/**
 * @author Nurtilek Akimbekov
*/

import java.util.Random;

public class HasState {
    
    // The state variable stores a random integer value.
    protected int state;

    /**
     * Constructor. 
     * Initializes the state with a random integer value in the range [1, 1000].
     */
    public HasState() {
        Random rand = new Random();
        state = rand.nextInt(1000) + 1; // range [1, 1000]
    }

    /**
     * Getter method for the state variable.
     * @return The current state value.
     */
    public int getObjState() {
        return state;
    }

    /**
     * Method to change the state to a new random value.
     * It generates a new random integer value in the range [1, 1000].
     */
    public void changeState() {
        Random rand = new Random();
        state = rand.nextInt(1000) + 1; // range [1, 1000]
    }
}




