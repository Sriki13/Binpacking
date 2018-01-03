package bin;

/**
 * Une factory créant des bins standards.
 */
public class ConcreteBinFactory extends BinFactory {

    /**
     * Crée une nouvelle bin.
     *
     * @param binSize la taille de la bin à créer
     * @return la bin crée
     */
    @Override
    public Bin createBin(int binSize) {
        Bin bin = new ConcreteBin(binSize, index++);
        created.add(bin);
        return bin;
    }

}
