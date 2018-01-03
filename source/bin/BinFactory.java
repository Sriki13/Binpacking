package bin;

import java.util.ArrayList;
import java.util.List;

public abstract class BinFactory {

    protected int index = 0;
    protected List<Bin> created = new ArrayList<>();

    public abstract Bin createBin(int binSize);

    public List<Bin> getCreatedBins() {
        return created;
    }

    public void reset() {
        index = 0;
        created = new ArrayList<>();
    }

}
