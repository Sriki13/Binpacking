package base;

public class BenchableBinFactory extends BinFactory {

    @Override
    public Bin createBin(int binSize) {
        Bin bin = new BenchableBin(new ConcreteBin(binSize, index++));
        created.add(bin);
        return bin;
    }

}
