package base;

import algo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume Andre
 */
public class Invoker {

    private List<Context> contexts;
    private List<BinPackingStrategy> strategies;
    private PrintStream output;
    private BinFactory binFactory;

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

    public Invoker(PrintStream output, int amount) {
        this.output = output;
        loadDefaultStrategies();
        contexts = new ArrayList<>();
        ContextGenerator cg = new ContextGenerator();
        contexts.add(new Context(cg));
        for (int i = 1; i < amount; i++) {
            contexts.add(new Context(cg.cloneMethod()));
        }
    }

    private void loadDefaultStrategies() {
        strategies = new ArrayList<>();
        strategies.add(new NextFitStrategy());
        strategies.add(new FirstFitStrategy());
        strategies.add(new BestFitStrategy());
        strategies.add(new WorstFitStrategy());
        strategies.add(new AlmostWorstFitStrategy());
    }

    public void applyStrategies() {
        binFactory = new ConcreteBinFactory();
        for (Context context : contexts) {
            output.println("Chargement du contexte " + context.toString() + "\n");
            for (BinPackingStrategy strategy : strategies) {
                output.println("\tApplication de la stratégie " + strategy.toString());
                long start = System.nanoTime();
                List<Bin> bins = strategy.pack(context,binFactory);
                output.println("\tExécution en " + (System.nanoTime()  - start) + " nanosecondes");
                output.println("\tBins remplies: " + bins.size());
                output.println();
            }
            output.println("----------------------------------------------------------------------");
        }
    }

}
