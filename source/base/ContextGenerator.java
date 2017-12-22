package base;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author JA
 */
public class ContextGenerator extends ContextLoader {

    private int size;
    private int minSize;
    private int maxSize;
    private int method;
    private int mean;
    private int deviation;

    public ContextGenerator() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Nouveau contexte");
            System.out.println("Entrez la taille des bins");
            binSize = sc.nextInt();
            System.out.println("Entrez le nombre de bins");
            size = sc.nextInt();
            System.out.println("Entrez la méthode de génération, 1 pour uniforme, 2 pour répartition normale");
            method = sc.nextInt();
            switch (method) {
                case 1:
                    System.out.println("Entrez la taille minimale des paquets (incluse)");
                    minSize = sc.nextInt();
                    System.out.println("Entrez la taille maximale des paquets (excluse)");
                    maxSize = sc.nextInt();
                    buildUniform();
                    break;
                case 2:
                    System.out.println("Entrez la moyenne");
                    mean = sc.nextInt();
                    System.out.println("Entrez l'écart-type");
                    deviation = sc.nextInt();
                    buildGaussian();
                    break;
                default:
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException ie) {
            System.out.println("Mauvais input");
            sc.close();
            System.exit(1);
        }
    }

    public ContextGenerator cloneMethod() {
        if (method == 1) {
            buildUniform();
        } else {
            buildGaussian();
        }
        return this;
    }

    private void buildUniform() {
        binObjects = new Random().ints(size, minSize, maxSize).boxed().collect(Collectors.toList());
    }

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
                binObjects.set(binObjects.get(i), 0);
            }
        }
    }
}
