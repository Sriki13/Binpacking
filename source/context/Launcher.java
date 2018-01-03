package context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Classe gérant les arguments et le lancement du programme en général.
 */
public class Launcher {

    private final static String EXAMPLES_PATH = "exemples";

    private boolean randomFlag;
    private boolean benchFlag;
    private String path;
    private PrintStream out;
    private InputStream in;

    /**
     * Constructeur.
     *
     * @param out le stream de sortie des résultats
     * @param in  le stream d'entrée de communication avec l'utilisateur
     */
    public Launcher(PrintStream out, InputStream in) {
        this.out = out;
        this.in = in;
        randomFlag = false;
        benchFlag = false;
    }


    /**
     * Traite les arguments du programme.
     *
     * @param args les arguments utilisés
     */
    public void manageArgs(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                // Pas une option: chargement d'un contexte unique
                path = arg;
            } else if (arg.equals("-random")) {
                // Génération de contextes
                randomFlag = true;
            } else if (arg.equals("-bench")) {
                // Montrer le nombre de lectures et d'écritures sur les bins
                benchFlag = true;
            } else {
                throw new RuntimeException("Argument invalide: " + arg);
            }
        }
    }

    /**
     * Lance la lecture des contextes et l'exécution des startégies sur celles-ci.
     *
     * @throws FileNotFoundException si l'un des fichiers de contexte est introuvable
     */
    public void launch() throws FileNotFoundException {
        Invoker invoker;
        if (path != null) {
            // Exécution sur un seul fichier
            invoker = new Invoker(out, Collections.singletonList(new File(path)));
        } else if (randomFlag) {
            // Génération de contextes
            out.println("Entrez le nombre de contextes à créer");
            Scanner sc = new Scanner(in);
            invoker = new Invoker(out, System.in, sc.nextInt());
        } else {
            // Exécution sur tous les fichiers du dossier exemple
            File dir = new File(EXAMPLES_PATH);
            if (!dir.exists()) {
                out.println("Dossier spécifié: " + dir.getAbsolutePath());
                throw new FileNotFoundException("Impossible de trouver le dossier d'exemples");
            }
            if (dir.listFiles() == null) {
                throw new FileNotFoundException("Aucun exemple trouvé dans le dossier");
            }
            @SuppressWarnings("ConstantConditions")
            List<File> examples = Arrays.asList(dir.listFiles());
            invoker = new Invoker(out, examples);
        }
        invoker.applyStrategies(benchFlag);
    }

}
