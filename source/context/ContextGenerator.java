package context;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Un générateur de contexte selon des paramètres indiqués par l'utilisateur.
 *
 * @author JA
 */
public class ContextGenerator extends ContextLoader {

    private int size;
    private int minSize;
    private int maxSize;
    private int method;
    private int mean;
    private int deviation;

    /**
     * Constructeur.
     *
     * @param output le stream de sortie de dialogue utilisateur
     * @param input  le stream d'entrée de dialogue utilisateur
     */
    public ContextGenerator(PrintStream output, InputStream input) {
        name = "Contexte généré par l'utilisateur";
        Scanner sc = new Scanner(input);
        try {
            output.println("Nouveau contexte");
            output.println("Entrez la taille des bins");
            binSize = sc.nextInt();
            output.println("Entrez le nombre d'objets");
            size = sc.nextInt();
            output.println("Entrez la méthode de génération, 1 pour uniforme, 2 pour répartition normale");
            method = sc.nextInt();
            switch (method) {
                case 1:
                    output.println("Entrez la taille minimale des paquets (incluse)");
                    minSize = sc.nextInt();
                    output.println("Entrez la taille maximale des paquets (excluse)");
                    maxSize = sc.nextInt();
                    buildUniform();
                    break;
                case 2:
                    output.println("Entrez la moyenne");
                    mean = sc.nextInt();
                    output.println("Entrez l'écart-type");
                    deviation = sc.nextInt();
                    buildGaussian();
                    break;
                default:
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException ie) {
            output.println("Mauvais input");
            sc.close();
            System.exit(1);
        }
    }

    /**
     * Construit une liste d'objets de taille suivant une distribution uniforme.
     */
    private void buildUniform() {
        binObjects = new Random().ints(size, minSize, maxSize).boxed().collect(Collectors.toList());
    }

    /**
     * Construit une liste d'objets de taille suivant une distribution gaussienne.
     */
    private void buildGaussian() {
        Random r = new Random();
        binObjects = IntStream.range(0, size)
                .map(i -> (int) Math.round(r.nextGaussian() * deviation + mean))
                .boxed()
                .collect(Collectors.toList());
        for (int i = 0; i < binObjects.size(); i++) {
            if (binObjects.get(i) > binSize) {
                binObjects.set(i, binSize);
            } else if (binObjects.get(i) < 1) {
                binObjects.set(i, 1);
            }
        }
    }

    /**
     * Regénère des données aléatoires selon ce qui a été indiqué au préalable.
     *
     * @return un générateur fonctionnat avec une même distribution que précédamment
     */
    public ContextGenerator cloneMethod() {
        if (method == 1) {
            buildUniform();
        } else {
            buildGaussian();
        }
        return this;
    }

}
