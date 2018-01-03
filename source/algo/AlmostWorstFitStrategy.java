package algo;

import base.Bin;

public class AlmostWorstFitStrategy extends TreeStrategy {

    @Override
    public Bin getNextBin(int size) {
        return tree.searchAlmostWorstBin(size);
    }

    @Override
    public String toString() {
        return "Almost worst fit";
    }

}
