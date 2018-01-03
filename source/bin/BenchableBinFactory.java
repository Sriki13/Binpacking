package bin;

/**
 * Une factory de bins créant des bins comptant le nombre de lectures/écritures.
 */
public class BenchableBinFactory extends BinFactory {

    /**
     * Crée une nouvelle bin.
     *
     * @param binSize la taille de la bin à créer
     * @return la bin crée
     */
    @Override
    public Bin createBin(int binSize) {
        Bin bin = new BenchableBin(new ConcreteBin(binSize, index++));
        created.add(bin);
        return bin;
    }

}
