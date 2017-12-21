package base;

import java.util.Collections;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class Context {

    public int binSize;
    public List<Integer> objects;

    public Context(ContextLoader creator) {
        binSize = creator.getBinSize();
        objects = creator.getObjects();
    }

    @Override
    public String toString() {
        return "Bins de taille " + binSize + " avec " + objects.size() + " objets " +
                "de taille " + Collections.min(objects) + " à " + Collections.max(objects);
    }


}
