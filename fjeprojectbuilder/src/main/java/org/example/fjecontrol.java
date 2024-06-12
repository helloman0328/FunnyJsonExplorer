package org.example;

import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Main {

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser(args);
        String jsonFile = parser.getFile();
        String style = parser.getStyle();
        String icon = parser.getIcon();

        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(jsonFile)));
            FunnyJsonExplorer explorer = new FunnyJsonExplorer(style, icon);
            explorer.load(jsonData);
            explorer.show();
        } catch (IOException | JSONException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class ArgumentParser {
    private String jsonFile;
    private String style;
    private String icon;

    public ArgumentParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f") || args[i].equals("--file")) {
                this.jsonFile = args[i + 1];
            } else if (args[i].equals("-s") || args[i].equals("--style")) {
                this.style = args[i + 1];
            } else if (args[i].equals("-i") || args[i].equals("--icon")) {
                this.icon = args[i + 1];
            }
        }
    }

    public String getFile() {
        return jsonFile;
    }

    public String getStyle() {
        return style;
    }

    public String getIcon() {
        return icon;
    }
}
