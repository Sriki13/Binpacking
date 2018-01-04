package algo;

import bin.BenchableBin;
import bin.Bin;
import bin.BinFactory;
import bin.ExecutionData;
import context.Context;

import java.util.List;

/**
 * Classe abstraite pour toutes les stratégies de binpacking.
 */
public abstract class BinPackingStrategy {

    protected ExecutionData data = new ExecutionData();

    /**
     * Crée et remplit les bins d'objets.
     *
     * @param context    le contexte détaillant les bins et objets disponibles
     * @param binFactory la factory utilisée pour créer les bins
     */
    public abstract void pack(Context context, BinFactory binFactory);

    public void apply (Context context, BinFactory binFactory, boolean bench) {
        long start = System.nanoTime();
        pack(context,binFactory);
        data.addExecTime(System.nanoTime() - start);
        List<Bin> bins = binFactory.getCreatedBins();
        if (bench) {
            data.addnbRead(bins.stream().mapToLong(bin -> ((BenchableBin) bin).getNbRead()).sum());
            data.addnbWrite(bins.stream().mapToLong(bin -> ((BenchableBin) bin).getNbWrite()).sum());
        }
        data.addFilledBins(bins.size());
    }
    /**
     * Remet à zéro la stratégie.
     *
     */
    public abstract void reset();

    public ExecutionData getData() {
        return data;
    }

}
