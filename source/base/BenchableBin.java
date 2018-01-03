package base;

public class BenchableBin implements Bin {

    private Bin bin;
    private int nbWrite;
    private int nbRead;

    public BenchableBin(Bin bin) {
        this.bin = bin;
        nbWrite = 1;
        nbRead = 0;
    }

    public boolean fits(int object) {
        nbRead++;
        return bin.fits(object);
    }

    public void add(int object) {
        nbWrite++;
        bin.add(object);
    }

    @Override
    public int getCapacityLeft() {
        return bin.getCapacityLeft();
    }

    @Override
    public int getIndex() {
        return bin.getIndex();
    }

    @Override
    public boolean isFull() {
        nbRead++;
        return bin.isFull();
    }

    public int getNbRead() {return nbRead;}

    public int getNbWrite() {return nbWrite;}

    @Override
    public int compareTo(Bin o) {
        if (o instanceof ConcreteBin) {
            return bin.compareTo(o);
        }
        else {
            return bin.compareTo(((BenchableBin) o).bin);
        }
    }
}
