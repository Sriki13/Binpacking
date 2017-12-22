package algo;

import base.Bin;
import base.BinFactory;
import base.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class NextFitStrategy implements BinPackingStrategy {

    @Override
    public List<Bin> pack(Context context, BinFactory binFactory) {
        List<Bin> bins = new ArrayList<>();
        Bin current = binFactory.createBin(context.binSize);
        for (int object : context.objects) {
            if (current.fits(object)) {
                current.add(object);
            } else {
                bins.add(current);
                current = binFactory.createBin(context.binSize);
            }
        }
        return bins;
    }

    @Override
    public String toString() {
        return "Next fit";
    }

}
