package algo;

import base.Bin;

public class BestFitStrategy extends TreeStrategy {

    @Override
    public Bin getNextBin(int size) {
        return tree.searchBestBin(size);
    }

    @Override
    public String toString() {
        return "Best fit";
    }

}
