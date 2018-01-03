package base;

public class ConcreteBinFactory extends BinFactory {

    @Override
    public Bin createBin(int binSize) {
        Bin bin = new ConcreteBin(binSize, index++);
        created.add(bin);
        return bin;
    }

}
