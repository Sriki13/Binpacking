package base;

public class BenchableBinFactory extends BinFactory {

    @Override
    public Bin createBin(int binSize) {
        return new BenchableBin(new ConcreteBin(binSize,index++));
    }

}
