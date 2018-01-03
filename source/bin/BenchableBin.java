package bin;

/**
 * Une bin gardant en mémoire le nombre d'écritures et de lectures effectuées.
 */
public class BenchableBin implements Bin {

    private Bin bin;
    private int nbWrite;
    private int nbRead;

    /**
     * Constructeur.
     *
     * @param bin la bin à observer
     */
    protected BenchableBin(Bin bin) {
        this.bin = bin;
        nbWrite = 1;
        nbRead = 0;
    }

    /**
     * Vérifie si la bin dispose d'assez de place pour l'objet demandé.
     *
     * @param object la taille de l'objet
     * @return vrai si la taille de l'objet est inférieure à la capacité de la bin restante, faux sinon
     */
    @Override
    public boolean fits(int object) {
        nbRead++;
        return bin.fits(object);
    }

    /**
     * Ajoute un objet à la bin. Ne vérifie pas si l'ajout est valide.
     *
     * @param object l'objet à ajouter à la bin
     */
    @Override
    public void add(int object) {
        nbWrite++;
        bin.add(object);
    }

    /**
     * Vérifie si la bin est pleine ou non.
     *
     * @return vrai si pleine, faux sinon
     */
    @Override
    public boolean isFull() {
        nbRead++;
        return bin.isFull();
    }

    @Override
    public int getCapacityLeft() {
        return bin.getCapacityLeft();
    }

    @Override
    public int getIndex() {
        return bin.getIndex();
    }

    public int getNbRead() {return nbRead;}

    public int getNbWrite() {return nbWrite;}

    @Override
    public int compareTo(Bin o) {
        if (o instanceof ConcreteBin) {
            return bin.compareTo(o);
        } else {
            return bin.compareTo(((BenchableBin) o).bin);
        }
    }

}
