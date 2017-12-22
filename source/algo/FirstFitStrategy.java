package algo;

import base.Bin;
import base.Context;
import tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class FirstFitStrategy implements BinPackingStrategy {

    /**
     * On utilise un BST pour les bins pour avoir une complexité
     * de recherche du premier bin pouvant stocker l'objet en log(n) (n = taille de l'arbre)
     * Pour le worst case, on aura N objets dans M bins d'où N = M,
     * d'où la complexité de cet algorithme en n * log(n)
     */
    @Override
    public List<Bin> pack(Context context) {
        BinarySearchTree<Bin> bins = new BinarySearchTree<>();
        boolean inserted = true;
        bins.insert(new Bin(context.binSize));
        for (int object : context.objects) {
            for (Bin bin : bins) {
                if (bin.fits(object)) {
                    bin.add(object);
                }
            }
            if (!inserted) {
                // une fois sorti de la boucle,
                // nouveau bin où l'on va ajouter l'objet
                Bin tmp = new Bin(context.binSize);
                tmp.add(object);
                bins.insert(tmp);
            }
            inserted = false;
        }

        return bins.toSortedList();
    }

    @Override
    public String toString() {
        return "First fit";
    }

}
