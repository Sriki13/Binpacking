package base;

import base.Bin;

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

}
