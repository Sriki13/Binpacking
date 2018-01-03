package base;

public class ConcreteBin implements Bin {

    private int capacityLeft;
    private int index;

    public ConcreteBin(int capacity, int index) {
        capacityLeft = capacity;
        this.index = index;
    }

    public boolean fits(int object) {
        return capacityLeft - object > 0;
    }

    public void add(int object) {
        capacityLeft -= object;
    }

    public int getCapacityLeft() {
        return capacityLeft;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Bin o) {
        int val = this.capacityLeft - ((ConcreteBin) o).capacityLeft;
        if (val == 0) {
            return this.index - ((ConcreteBin) o).index;
        }
        return val;
    }

    @Override
    public String toString() {
        return "ConcreteBin{" +
                "capacityLeft=" + capacityLeft +
                ", index=" + index +
                '}';
    }
}