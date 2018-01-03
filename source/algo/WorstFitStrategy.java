package algo;

import base.Bin;

public class WorstFitStrategy extends TreeStrategy {

    @Override
    public Bin getNextBin(int size) {
        return tree.searchWorstBin(size);
    }

    @Override
    public String toString() {
        return "Worst fit";
    }

}
