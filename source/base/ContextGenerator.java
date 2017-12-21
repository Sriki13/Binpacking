package base;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class ContextGenerator implements ContextLoader {


    public ContextGenerator(PrintStream out) {

    }

    @Override
    public int getBinSize() {
        return 0;
    }

    @Override
    public List<Integer> getObjects() {
        return new ArrayList<>();
    }
}
