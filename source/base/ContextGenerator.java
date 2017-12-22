package base;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author JA
 */
public class ContextGenerator extends ContextLoader {

    public ContextGenerator() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Nouveau contexte");
            System.out.println("Entrez la taille des bins");
            binSize = sc.nextInt();
            System.out.println("Entrez le nombre de bins");
            int size = sc.nextInt();
            System.out.println("Entrez la méthode de génération, 1 pour uniforme, 2 pour répartition normale");
            int val = sc.nextInt();
            switch (val) {
                case 1:
                    buildUniform(size, sc);
                    break;
                case 2:
                    buildGaussian(size, sc);
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
        catch (InputMismatchException ie) {
            System.out.println("Mauvais input");
            sc.close();
            System.exit(1);
        }
    }

    private void buildUniform(int size, Scanner sc) {
        System.out.println("Entrez la taille minimale des paquets (incluse)");
        int minSize = sc.nextInt();
        System.out.println("Entrez la taille maximale des paquets (excluse)");
        int maxSize = sc.nextInt();
        binObjects = new Random().ints(size, minSize, maxSize).boxed().collect(Collectors.toList());
    }

    private void buildGaussian(int size, Scanner sc) {
        System.out.println("Entrez la moyenne");
        int mean = sc.nextInt();
        System.out.println("Entrez l'écart-type");
        int deviation = sc.nextInt();
        Random r = new Random();
        binObjects = IntStream.range(0, size)
                .map(i -> (int) Math.round(r.nextGaussian() * deviation + mean))
                .boxed()
                .collect(Collectors.toList());
        for (int i = 0; i < binObjects.size(); i++) {
            if (binObjects.get(i) > binSize) {
                binObjects.set(binObjects.get(i), binSize);
            } else if (binObjects.get(i) < 1) {
                binObjects.set(binObjects.get(i), 0);
            }
        }
        System.out.println(binObjects);

    }
}
