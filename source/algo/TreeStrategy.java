package algo;


import bin.Bin;
import bin.BinFactory;
import context.Context;
import tree.AVLTree;

public abstract class TreeStrategy extends BinPackingStrategy {

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

    @Override
    public void reset() {
        tree = new AVLTree();
    }

    /**
     * Récupère la bin suivante devant accepter l'objet selon l'algorithme.
     * Retourne null si aucune bin ne convient.
     *
     * @param size la taille de l'objet à rentrer
     * @return la bin devant l'accepter, ou null si aucune ne convient
     */
    public abstract Bin getNextBin(int size);

}
