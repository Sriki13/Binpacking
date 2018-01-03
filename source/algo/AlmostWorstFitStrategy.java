package algo;

import base.Bin;
import base.BinFactory;
import base.Context;

import java.util.ArrayList;
import java.util.List;

public class AlmostWorstFitStrategy implements BinPackingStrategy {

    @Override
    public List<Bin> pack(Context context, BinFactory binFactory) {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Almost worst fit";
    }

}
