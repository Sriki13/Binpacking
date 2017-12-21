package algo;

import base.Bin;
import base.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class NextFitStrategy implements BinPackingStrategy {

    @Override
    public List<Bin> pack(Context context) {
        List<Bin> bins = new ArrayList<>();
        Bin current = new Bin(context.binSize);
        for (int object : context.objects) {
            if (current.fits(object)) {
                current.add(object);
            } else {
                bins.add(current);
                current = new Bin(context.binSize);
            }
        }
        return bins;
    }

    @Override
    public String toString() {
        return "Next fit";
    }

}
