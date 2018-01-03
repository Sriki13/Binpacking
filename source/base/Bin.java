package base;

public interface Bin extends Comparable<Bin> {

    boolean fits(int object);

    void add(int object);

    int getCapacityLeft();

    int getIndex();

    boolean isFull();

}
