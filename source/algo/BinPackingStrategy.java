package algo;

import base.Bin;
import base.BinFactory;
import base.Context;

import java.util.List;

public interface BinPackingStrategy {

    List<Bin> pack(Context context, BinFactory binFactory);

}
