package context;

import java.util.List;

public abstract class ContextLoader {

    protected int binSize;
    protected List<Integer> binObjects;
    protected String name;

    public int getBinSize() {
        return binSize;
    }

    public List<Integer> getObjects() {
        return binObjects;
    }

    public String getName() { return name;}

}
