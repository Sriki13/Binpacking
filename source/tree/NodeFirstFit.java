package tree;

import bin.Bin;

/**
 * Une node pour l'arbre AVL.
 */
public class NodeFirstFit {

    public NodeFirstFit left, right;
    public int height = 1;
    public int minIndex;
    public Bin bin;

    /**
     * Constructeur.
     *
     * @param val la bin stockée dans cette node
     */
    public NodeFirstFit(Bin val) {
        this.bin = val;
        minIndex = bin.getIndex();
    }

}
