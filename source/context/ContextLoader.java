package context;

import java.util.List;

/**
 * Une abstraction de chargement de contexte.
 */
public abstract class ContextLoader {

    protected int binSize;
    protected List<Integer> binObjects;
    protected String name;

    /**
     * Récupère la taille des bins.
     */
    public int getBinSize() {
        return binSize;
    }

    /**
     * Récupère les tailles des objets à placer dans les bins.
     */
    public List<Integer> getObjects() {
        return binObjects;
    }

    /**
     * Récupère le nom du contexte.
     */
    public String getName() { return name;}

}
