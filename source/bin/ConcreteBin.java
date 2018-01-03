package bin;

public class ConcreteBin implements Bin {

    private int capacityLeft;
    private int index;

    public ConcreteBin(int capacity, int index) {
        capacityLeft = capacity;
        this.index = index;
    }

    public boolean fits(int object) {
        return capacityLeft - object >= 0;
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
    public boolean isFull() {
        return capacityLeft == 0;
    }

    @Override
    public int compareTo(Bin o) {
        int val = this.capacityLeft - o.getCapacityLeft();
        if (val == 0) {
            return this.index - o.getIndex();
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