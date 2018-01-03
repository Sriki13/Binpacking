package algo;

import bin.Bin;

/**
 * On place successivement les objets dans la bin ayant le plus de place restante.
 * Si aucune bin n'a assez de place pour l'objet, une nouvelle est crée.
 */
public class WorstFitStrategy extends TreeStrategy {

    /**
     * Récupère la bin suivante devant accepter l'objet selon l'algorithme.
     * Retourne null si aucune bin ne convient.
     *
     * @param size la taille de l'objet à rentrer
     * @return la bin devant l'accepter, ou null si aucune ne convient
     */
    @Override
    public Bin getNextBin(int size) {
        return tree.searchWorstBin(size);
    }

    @Override
    public String toString() {
        return "Worst fit";
    }

}
