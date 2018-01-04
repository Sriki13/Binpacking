package tree;

import bin.Bin;

/**
 * Une node pour l'arbre AVL.
 */
public class Node {

    public Node left, right;
    public int height = 1;
    public Bin bin;

    /**
     * Constructeur.
     *
     * @param val la bin stockée dans cette node
     */
    public Node(Bin val) {
        this.bin = val;
    }

}
