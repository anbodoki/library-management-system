package com.lms.integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DataParser {

    private static final Path rootLocation = Paths.get("upload-dir");

    public static Map<DataLine, List<String>> read() throws IOException {
        try {
            Map<DataLine, List<String>> map = new HashMap<>();
            Scanner scanner = new Scanner(rootLocation.resolve("data.tsv"), "UTF-8");
            while (scanner.hasNextLine()) {
                String curr = scanner.nextLine();
                String[] split = curr.split("\t");
                DataLine line = new DataLine();
                if (split.length != 4)  {
                    continue;
                }
                try {
                    line.setEdition((split[2].isEmpty() ? 0 : Integer.parseInt(split[2])));
                } catch (Exception e) {
                    line.setEdition(0);
                }
                line.setYear(split[3]);
                String authAndName = split[1];
                String name = authAndName.substring(0, authAndName.lastIndexOf('('));
                if (name.isEmpty()) {
                    continue;
                }
                String auth = authAndName.substring(authAndName.lastIndexOf('(') + 1, authAndName.lastIndexOf(')'));
                if (auth.isEmpty() || auth.length() < 2) {
                    continue;
                }
                line.setName(name);
                line.setAuthor(auth);
                if (!map.containsKey(line)) {
                    map.put(line, new ArrayList<>());
                }
                map.get(line).add(split[0]);
            }
            scanner.close();
            return map;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
