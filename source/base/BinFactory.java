package base;

public abstract class BinFactory {

    protected int index = 0;

    public abstract Bin createBin(int binSize);

}
