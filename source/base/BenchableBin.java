package base;

public class BenchableBin implements Bin {

    private Bin bin;
    private int nbWrite;
    private int nbRead;

    public BenchableBin(Bin bin) {
        this.bin = bin;
        nbWrite = 0;
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

    public int getNbRead() {return nbRead;}

    public int getNbWrite() {return nbWrite;}

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Bin o) {
        return 0;
    }

}
