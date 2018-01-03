package algo;

import bin.Bin;

/**
 * Cette stratégie remplit les bins en ajoutant successivement les objets dans l'avant-dernière
 * bin de taille maximale pouvant l'accepter, ou la dernière bin si seule une bin peut l'accepter.
 * Si aucune bin n'est de taille suffisante, une nouvelle est créee.
 */
public class AlmostWorstFitStrategy extends TreeStrategy {

    /**
     * Récupère la bin suivante devant accepter l'objet selon l'algorithme.
     * Retourne null si aucune bin ne convient.
     *
     * @param size la taille de l'objet à rentrer
     * @return la bin devant l'accepter, ou null si aucune ne convient
     */
    @Override
    public Bin getNextBin(int size) {
        return tree.searchAlmostWorstBin(size);
    }

    @Override
    public String toString() {
        return "Almost worst fit";
    }

}
