package algo;

import base.Bin;
import base.BinFactory;
import base.Context;
import tree.AVLTree;
import tree.ComparatorNotFirstFit;

public class AlmostWorstFitStrategy implements BinPackingStrategy {

    @Override
    public void pack(Context context, BinFactory binFactory) {
        AVLTree tree = new AVLTree(new ComparatorNotFirstFit());
        for (int object : context.objects) {
            Bin bin = tree.searchAlmostWorstBin(object);
            if (bin == null) {
                bin = binFactory.createBin(context.binSize);
            } else {
                tree.delete(bin);
            }
            bin.add(object);
            tree.insert(bin);
        }
    }

    @Override
    public String toString() {
        return "Almost worst fit";
    }

}
