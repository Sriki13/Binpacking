package context;

import java.util.Collections;
import java.util.List;

public class Context {

    public int binSize;
    public List<Integer> objects;
    public String name;

    public Context(ContextLoader creator) {
        binSize = creator.getBinSize();
        objects = creator.getObjects();
        name = creator.getName();
    }

    @Override
    public String toString() {
        return "Bins de taille " + binSize + " avec " + objects.size() + " objets " +
                "de taille " + Collections.min(objects) + " à " + Collections.max(objects);
    }

}
