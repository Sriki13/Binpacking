package base;

import java.util.List;

public abstract class ContextLoader {

    protected int binSize;
    protected List<Integer> binObjects;

    public int getBinSize() {
        return binSize;
    }

    public List<Integer> getObjects() {
        return binObjects;
    }

}
