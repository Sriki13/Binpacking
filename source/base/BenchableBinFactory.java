package base;

public class BenchableBinFactory implements BinFactory{
    @Override
    public Bin createBin(int binSize) {
        return new BenchableBin(new ConcreteBin(binSize));
    }
}
