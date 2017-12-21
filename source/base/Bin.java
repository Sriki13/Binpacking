package base;

/**
 * @author Guillaume Andre
 */
public class Bin {

    private int capacityLeft;

    public Bin(int capacity) {
        capacityLeft = capacity;
    }

    public boolean fits(int object) {
        return capacityLeft - object > 0;
    }

    public void add(int object) {
        capacityLeft -= object;
    }

}
