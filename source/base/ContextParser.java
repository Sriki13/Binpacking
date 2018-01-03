package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextParser extends ContextLoader {

    public ContextParser(File input) throws IOException {
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
