package base;

/**
 * @author Guillaume Andre
 */
public class ConcreteBin implements Bin {

    private int capacityLeft;

    public ConcreteBin(int capacity) {
        capacityLeft = capacity;
    }

    public boolean fits(int object) {
        return capacityLeft - object > 0;
    }

    public void add(int object) {
        capacityLeft -= object;
    }

    @Override
    public int compareTo(Bin o) {
        return this.capacityLeft - ((ConcreteBin) o).capacityLeft;
    }
}
