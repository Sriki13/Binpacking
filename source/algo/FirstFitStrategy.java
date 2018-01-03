package algo;

import bin.Bin;

/**
 * On remplit les bins en placant les objets successivement dans les bins d'indice de
 * plus bas où ils peuvent rentrer. Une nouvelle bin est crée si acune ne convient.
 */
public class FirstFitStrategy extends TreeStrategy {

    /**
     * Récupère la bin suivante devant accepter l'objet selon l'algorithme.
     * Retourne null si aucune bin ne convient.
     *
     * @param size la taille de l'objet à rentrer
     * @return la bin devant l'accepter, ou null si aucune ne convient
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
