package algo;

import bin.Bin;
import bin.BinFactory;
import context.Context;

/**
 * On remplit les bins d'objets en net raitant qu'une bin à la fois.
 * Si celle ci ne peut pas accueillir d'objets, une nouvelle bin est crée
 * et l'ancienne bin ne sera pas considérée pour les objets suivants.
 */
public class NextFitStrategy extends BinPackingStrategy {

    /**
     * Crée et remplit les bins d'objets.
     *
     * @param context    le contexte détaillant les bins et objets disponibles
     * @param binFactory la factory utilisée pour créer les bins
     */
    @Override
    public void pack(Context context, BinFactory binFactory) {
        Bin current = binFactory.createBin(context.binSize);
        for (int object : context.objects) {
            if (current.fits(object)) {
                current.add(object);
            } else {
                current = binFactory.createBin(context.binSize);
                current.add(object);
            }
        }
    }

    @Override
    public void reset() {
        //Ne fait rien
    }

    @Override
    public String toString() {
        return "Next fit";
    }

}
