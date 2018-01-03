package bin;

/**
 * Une bin standard instanciable.
 */
public class ConcreteBin implements Bin {

    private int capacityLeft;
    private int index;

    /**
     * Constructeur.
     *
     * @param capacity la capacité maximale de la bin
     * @param index    l'index de création de cette bin
     */
    protected ConcreteBin(int capacity, int index) {
        capacityLeft = capacity;
        this.index = index;
    }

    /**
     * Vérifie si la bin dispose d'assez de place pour l'objet demandé.
     *
     * @param object la taille de l'objet
     * @return vrai si la taille de l'objet est inférieure à la capacité de la bin restante, faux sinon
     */
    @Override
    public boolean fits(int object) {
        return capacityLeft - object >= 0;
    }

    /**
     * Ajoute un objet à la bin. Ne vérifie pas si l'ajout est valide.
     *
     * @param object l'objet à ajouter à la bin
     */
    @Override
    public void add(int object) {
        capacityLeft -= object;
    }

    /**
     * Vérifie si la bin est pleine ou non.
     *
     * @return vrai si pleine, faux sinon
     */
    @Override
    public boolean isFull() {
        return capacityLeft == 0;
    }

    @Override
    public int getCapacityLeft() {
        return capacityLeft;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Bin o) {
        int val = this.capacityLeft - o.getCapacityLeft();
        if (val == 0) {
            return this.index - o.getIndex();
        }
        return val;
    }

    @Override
    public String toString() {
        return "ConcreteBin{" +
                "capacityLeft=" + capacityLeft +
                ", index=" + index +
                '}';
    }
}