package base;

public class ConcreteBinFactory extends BinFactory {


    @Override
    public Bin createBin(int binSize) {
        return new ConcreteBin(binSize, index++);
    }

}
