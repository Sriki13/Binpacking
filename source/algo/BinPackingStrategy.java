package algo;

import bin.BinFactory;
import context.Context;

/**
 * Interface pour toutes les stratégies de binpacking.
 */
public interface BinPackingStrategy {

    /**
     * Crée et remplit les bins d'objets.
     *
     * @param context    le contexte détaillant les bins et objets disponibles
     * @param binFactory la factory utilisée pour créer les bins
     */
    void pack(Context context, BinFactory binFactory);

}
