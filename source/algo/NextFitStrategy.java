package algo;

import base.Bin;
import base.BinFactory;
import base.Context;

public class NextFitStrategy implements BinPackingStrategy {

    @Override
    public void pack(Context context, BinFactory binFactory) {
        Bin current = binFactory.createBin(context.binSize);
        for (int object : context.objects) {
            if (current.fits(object)) {
                current.add(object);
            } else {
                current = binFactory.createBin(context.binSize);
                current.add(object);
            }
        }
    }

    @Override
    public String toString() {
        return "Next fit";
    }

}
