package algo;

import bin.Bin;

/**
 * On remplit les bins en placant successivement les objets dans les bins les
 * plus remplies possibles. Une nouvelle bin est crée si aucune ne convient.
 */
public class BestFitStrategy extends TreeStrategy {

    /**
     * Récupère la bin suivante devant accepter l'objet selon l'algorithme.
     * Retourne null si aucune bin ne convient.
     *
     * @param size la taille de l'objet à rentrer
     * @return la bin devant l'accepter, ou null si aucune ne convient
     */
    @Override
    public Bin getNextBin(int size) {
        return tree.searchBestBin(size);
    }

    @Override
    public String toString() {
        return "Best fit";
    }

}
