package algo;

import base.Bin;

public class FirstFitStrategy extends TreeStrategy {

    /**
     * On utilise un BST pour les bins pour avoir une complexité
     * de recherche du premier bin pouvant stocker l'objet en log(n) (n = taille de l'arbre)
     * Pour le worst case, on aura N objets dans M bins d'où N = M,
     * d'où la complexité de cet algorithme en n * log(n)
     */
    @Override
    public Bin getNextBin(int size) {
        return tree.searchFirstBin(size);
    }

    @Override
    public String toString() {
        return "First fit";
    }
}
