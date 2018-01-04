package context;

import algo.*;
import bin.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe chargée d'invoquer les différents algorithmes sur l'ensemble des contextes.
 */
public class Invoker {

    private List<Context> contexts;
    private List<BinPackingStrategy> strategies;
    private PrintStream output;

    /**
     * Constructeur utilisé pour charger des contextes dans des fichiers.
     *
     * @param output   le stream d'output des résultats
     * @param examples une liste des fichiers de contexte à charger
     * @throws FileNotFoundException si l'un des fichiers est introuvable
     */
    public Invoker(PrintStream output, List<File> examples) throws FileNotFoundException {
        this.output = output;
        loadDefaultStrategies();
        contexts = new ArrayList<>();
        for (File file : examples) {
            try {
                contexts.add(new Context(new ContextParser(file)));
            } catch (IOException e) {
                output.println(e.getMessage());
                output.println("Echec de lecture du fichier " + file.getName());
            }
        }
    }

    /**
     * Charge l'ensemble des stratégies disponibles.
     */
    private void loadDefaultStrategies() {
        strategies = new ArrayList<>();
        strategies.add(new NextFitStrategy());
        strategies.add(new FirstFitStrategy());
        strategies.add(new BestFitStrategy());
        strategies.add(new WorstFitStrategy());
        strategies.add(new AlmostWorstFitStrategy());
    }

    /**
     * Constructeur utilisé lors de la génération aléatoire de contexte.
     *
     * @param output le stream d'output des résultats
     * @param input  le stream d'input des données
     * @param amount le nombre de contextes à générer
     */
    public Invoker(PrintStream output, InputStream input, int amount) {
        this.output = output;
        loadDefaultStrategies();
        contexts = new ArrayList<>();
        ContextGenerator cg = new ContextGenerator(output, input);
        contexts.add(new Context(cg));
        for (int i = 1; i < amount; i++) {
            contexts.add(new Context(cg.cloneMethod()));
        }
    }

    /**
     * Applique l'ensemble des stratégies aux contextes disponibles.
     *
     * @param random vrai si génération de contexte, faux sinon
     * @param bench  vrai pour montrer le nombre d'écriture/lecture des bench, faux sinon
     */
    public void applyStrategies(boolean bench, boolean random) {
        BinFactory binFactory;
        if (bench) {
            binFactory = new BenchableBinFactory();
        } else {
            binFactory = new ConcreteBinFactory();
        }
        if (random && contexts.size() > 1) {
            output.println("Chargement du contexte " + contexts.get(0).name);
            output.println("Contenu: " + contexts.get(0).toString() + "\n");
            int i = 0;
            output.println("Chargement...");
            for (int j = 0; j < 100; j++) {
                output.print('.');
            }
            output.print('\n');
            for (Context context : contexts) {
                ++i;
                if (i * 100 / contexts.size() > 0) {
                    i = 0;
                    output.print('.');
                }
                for (BinPackingStrategy strategy : strategies) {
                    strategy.apply(context, binFactory, bench);
                    binFactory.reset();
                    strategy.reset();
                }
            }
            output.println();
            output.println();
            for (BinPackingStrategy strategy : strategies) {
                output.println("\tRésultats de la stratégie " + strategy.toString());
                output.println(strategy.getData());
                output.println("----------------------------------------------------------------------");
                output.println();
            }
        } else {
            for (Context context : contexts) {
                output.println("Chargement du contexte " + context.name);
                output.println("Contenu: " + context.toString() + "\n");
                for (BinPackingStrategy strategy : strategies) {
                    output.println("\tApplication de la stratégie " + strategy.toString());
                    long start = System.nanoTime();
                    strategy.pack(context, binFactory);
                    output.println("\tExécution en " + (System.nanoTime() - start) + " nanosecondes");
                    List<Bin> bins = binFactory.getCreatedBins();
                    output.println("\tBins remplies: " + bins.size());
                    if (bench) {
                        long read = bins.stream().mapToLong(bin -> ((BenchableBin) bin).getNbRead()).sum();
                        long write = bins.stream().mapToLong(bin -> ((BenchableBin) bin).getNbWrite()).sum();
                        output.println("\t\t Nombres de lectures " + read);
                        output.println("\t\t Nombres d'écritures " + write);
                    }
                    output.println();
                    binFactory.reset();
                    strategy.reset();
                }
                output.println("----------------------------------------------------------------------");
            }

        }
    }

}
