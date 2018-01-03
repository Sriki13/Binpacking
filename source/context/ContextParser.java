package context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Charge un contexte à partir d'un fichier.
 */
public class ContextParser extends ContextLoader {

    /**
     * Contructeur. Charge le contexte du fichier.
     *
     * @param input le fichier contenat le contexte
     * @throws IOException si la lecture du fichier est impossible
     */
    public ContextParser(File input) throws IOException {
        name = input.getName();
        binObjects = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(input));

        // Saut de la ligne "Taille bin"
        br.readLine();

        // Lecture de la taille des bins
        binSize = Integer.parseInt(br.readLine());

        // Saut de la ligne "Objets"
        br.readLine();

        // Lecture des objets
        String line;
        Matcher matcher;
        Pattern number = Pattern.compile("[0-9]+");
        while ((line = br.readLine()) != null) {
            matcher = number.matcher(line);
            while (matcher.find()) {
                binObjects.add(Integer.parseInt(matcher.group()));
            }
        }
    }

}
