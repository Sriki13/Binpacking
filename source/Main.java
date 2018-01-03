import context.Launcher;

public class Main {

    public static void main(String[] args) throws Exception {
        Launcher launcher = new Launcher(System.out, System.in);
        launcher.manageArgs(args);
        launcher.launch();
    }

}
