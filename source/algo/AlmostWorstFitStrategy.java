package algo;

import base.Bin;
import base.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class AlmostWorstFitStrategy implements BinPackingStrategy {

    @Override
    public List<Bin> pack(Context context) {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Almost worst fit";
    }

}
