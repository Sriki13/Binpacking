package algo;

import base.BinFactory;
import base.Context;

public interface BinPackingStrategy {

    void pack(Context context, BinFactory binFactory);

}
