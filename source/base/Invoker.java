package base;

import algo.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Invoker {

    private List<Context> contexts;
    private List<BinPackingStrategy> strategies;
    private PrintStream output;

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

    public void applyStrategies(boolean bench) {
        BinFactory binFactory;
        if (bench) {
            binFactory = new BenchableBinFactory();
        } else {
            binFactory = new ConcreteBinFactory();
        }
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
            }
            output.println("----------------------------------------------------------------------");
        }
    }

}
