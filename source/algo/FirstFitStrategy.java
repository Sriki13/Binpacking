package algo;

import base.Bin;
import base.BinFactory;
import base.Context;
import tree.AVLTree;
import tree.ComparatorNotFirstFit;

public class FirstFitStrategy implements BinPackingStrategy {

    /**
     * On utilise un BST pour les bins pour avoir une complexité
     * de recherche du premier bin pouvant stocker l'objet en log(n) (n = taille de l'arbre)
     * Pour le worst case, on aura N objets dans M bins d'où N = M,
     * d'où la complexité de cet algorithme en n * log(n)
     */
    @Override
    public void pack(Context context, BinFactory binFactory) {
        // todo change the comparator to use ComparatorFirstFit<Bin>
        AVLTree tree = new AVLTree(new ComparatorNotFirstFit());
        for (int object : context.objects) {
            Bin bin = tree.searchFirstBin(object);
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
        return "First fit";
    }
}
