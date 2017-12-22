package base;

public class ConcreteBinFactory implements BinFactory {
    @Override
    public Bin createBin(int binSize) {
        return new ConcreteBin(binSize);
    }
}
