package bin;

import java.util.ArrayList;
import java.util.List;

/**
 * Une interface de factory chargée de centraliser la création des bins.
 * Permet de gérér simplement la gestion d'index des bins à leur création.
 * Garde en mémoire l'ensemble des bins crées.
 */
public abstract class BinFactory {

    protected int index = 0;
    protected List<Bin> created = new ArrayList<>();

    /**
     * Crée une nouvelle bin.
     *
     * @param binSize la taille de la bin à créer
     * @return la bin crée
     */
    public abstract Bin createBin(int binSize);

    public List<Bin> getCreatedBins() {
        return created;
    }

    /**
     * Remet à zéro les index de bins ainsi que la liste de bins crée avant
     * l'exécution d'un autre algorithme.
     */
    public void reset() {
        index = 0;
        created = new ArrayList<>();
    }

}
