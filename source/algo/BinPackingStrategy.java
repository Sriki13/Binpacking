package algo;

import base.Bin;
import base.Context;

import java.util.List;

/**
 * @author Guillaume Andre
 */
public interface BinPackingStrategy {

    List<Bin> pack(Context context);

}
