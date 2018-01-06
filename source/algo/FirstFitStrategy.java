package algo;

import bin.Bin;
import bin.BinFactory;
import context.Context;
import tree.AVLTreeFirstFit;

/**
 * On remplit les bins en placant les objets successivement dans les bins d'indice de
 * plus bas où ils peuvent rentrer. Une nouvelle bin est crée si acune ne convient.
 */
public class FirstFitStrategy extends BinPackingStrategy {

    /**
     * Crée et remplit les bins d'objets.
     *
     * @param context    le contexte détaillant les bins et objets disponibles
     * @param binFactory la factory utilisée pour créer les bins
     */
    @Override
    public void pack(Context context, BinFactory binFactory) {
        AVLTreeFirstFit tree = new AVLTreeFirstFit();
        for (int object : context.objects) {
            Bin bin = tree.searchFirstBin(object);
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
        // Rien à faire
    }


    public String toString() {
        return "First fit";
    }
}
