package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Guillaume Andre
 */
public class ContextParser implements ContextLoader {

    private int parsedSize;
    private List<Integer> parsedObjects;

    public ContextParser(File input) throws IOException {
        parsedObjects = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(input));

        // Saut de la ligne "Taille bin"
        br.readLine();

        // Lecture de la taille des bins
        parsedSize = Integer.parseInt(br.readLine());

        // Saut de la ligne "Objets"
        br.readLine();

        // Lecture des objets
        String line;
        Matcher matcher;
        Pattern number = Pattern.compile("[0-9]+");
        while ((line = br.readLine()) != null) {
            matcher = number.matcher(line);
            while (matcher.find()) {
                parsedObjects.add(Integer.parseInt(matcher.group()));
            }
        }
    }

    @Override
    public int getBinSize() {
        return parsedSize;
    }

    @Override
    public List<Integer> getObjects() {
        return parsedObjects;
    }

}
