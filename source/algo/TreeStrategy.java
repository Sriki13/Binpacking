package algo;


import base.Bin;
import base.BinFactory;
import base.Context;
import tree.AVLTree;

public abstract class TreeStrategy implements BinPackingStrategy {

    protected AVLTree tree;

    public TreeStrategy() {
        tree = new AVLTree();
    }

    @Override
    public void pack(Context context, BinFactory binFactory) {
        for (int object : context.objects) {
            Bin bin = getNextBin(object);
            if (bin == null) {
                bin = binFactory.createBin(context.binSize);
            } else {
                tree.delete(bin);
            }
            bin.add(object);
            if (!bin.isFull()) {
                tree.insert(bin);
            }
        }
    }

    public abstract Bin getNextBin(int size);

}
