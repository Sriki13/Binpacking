package bin;

/**
 * Interface pour les bins utilisées par les algorithmes.
 */
public interface Bin extends Comparable<Bin> {

    /**
     * Vérifie si la bin dispose d'assez de place pour l'objet demandé.
     *
     * @param object la taille de l'objet
     * @return vrai si la taille de l'objet est inférieure à la capacité de la bin restante, faux sinon
     */
    boolean fits(int object);

    /**
     * Ajoute un objet à la bin. Ne vérifie pas si l'ajout est valide.
     *
     * @param object l'objet à ajouter à la bin
     */
    void add(int object);

    /**
     * Vérifie si la bin est pleine ou non.
     *
     * @return vrai si pleine, faux sinon
     */
    boolean isFull();

    int getCapacityLeft();

    int getIndex();

}
