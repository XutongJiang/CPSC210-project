package persistence;

import model.Necessities;
import model.Necessity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read necessities list from a file
// Imitating the class with same name in TellerApp.
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of necessities parsed from the file
    //          throws IOException if an exception is raised when opening the file
    public static Necessities readNecessities(File file) throws IOException {
        List<String> fileList = readFile(file);
        return parseList(fileList);
    }

    // EFFECTS: return content of the list file as a list of strings,
    //          each string containing features of a necessity with the same name
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of necessities with the given string name
    private static Necessities parseList(List<String> fileList) {
        Necessities necessities = new Necessities();

        for (String line : fileList) {
            ArrayList<String> lineContent = getString(line);
            necessities.addNecessity(parseNecessity(lineContent));
        }
        return necessities;
    }

    // EFFECTS: return a list of strings by splitting lines on DELIMITER
    private static ArrayList<String> getString(String line) {
        String[] gets = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(gets));
    }

    // REQUIRES: components has size 5 where element 0 represents the
    // id of the next necessity to be constructed, element 1 represents
    // the id of the necessity, elements 2 represents the name of the necessity,
    // element 3 represents the daily usage of the necessity and
    // element 4 represents the current amount of the necessity to be constructed
    // EFFECTS: returns an necessity constructed from components
    private static Necessity parseNecessity(List<String> components) {
        int nextId = Integer.parseInt(components.get(0));
        int id = Integer.parseInt(components.get(1));
        String nam = components.get(2);
        double usage = Double.parseDouble(components.get(3));
        double amt = Double.parseDouble(components.get(4));
        return new Necessity(nextId, id, nam, usage, amt);
    }
}
