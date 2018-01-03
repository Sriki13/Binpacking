package algo;

import base.Bin;
import base.BinFactory;
import base.Context;
import tree.BinarySearchTree;

import java.util.List;

public class FirstFitStrategy implements BinPackingStrategy {

    /**
     * On utilise un BST pour les bins pour avoir une complexité
     * de recherche du premier bin pouvant stocker l'objet en log(n) (n = taille de l'arbre)
     * Pour le worst case, on aura N objets dans M bins d'où N = M,
     * d'où la complexité de cet algorithme en n * log(n)
     */
    @Override
    public List<Bin> pack(Context context, BinFactory binFactory) {
        BinarySearchTree<Bin> bins = new BinarySearchTree<>();
        boolean inserted = true;
        bins.insert(binFactory.createBin(context.binSize));
        for (int object : context.objects) {
            // todo vérifier si l'itérator de la classe BST n'est pas en N (et bien en log(n) ...)
            for (Bin bin : bins) {
                if (bin.fits(object)) {
                    bin.add(object);
                }
            }
            if (!inserted) {
                // une fois sorti de la boucle,
                // nouveau bin où l'on va ajouter l'objet
                Bin tmp = binFactory.createBin(context.binSize);
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
