package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FunnyJsonExplorer {
    private String style;
    private String icon;
    private Component root;

    public FunnyJsonExplorer(String style, String icon) {
        this.style = style;
        this.icon = icon;
        this.root = null;
    }

    private Component buildComponent(JSONObject data) throws JSONException {
        String name = data.optString("name", "Null");
        int level = data.optInt("level", 0);
        Component.Builder builder;
        if (data.has("children")) {
            builder = new Container.Builder(name, level, this.style, this.icon);
            JSONArray children = data.getJSONArray("children");
            for (int i = 0; i < children.length(); i++) {
                JSONObject childData = children.getJSONObject(i);
                ((Container.Builder) builder).addChild(buildComponent(childData));
            }
        } else {
            builder = new Leaf.Builder(name, this.style, this.icon);
        }
        return builder.build();
    }

    public void load(String jsonData) throws JSONException {
        JSONObject parsedData = new JSONObject(jsonData);
        this.root = buildComponent(parsedData);
    }

    public void show() {
        if (this.root != null) {
            System.out.println("Visualizing JSON with style " + this.style + " and icon " + this.icon);
            this.root.draw("", true); // Passing true for isLast
        } else {
            System.out.println("No data loaded.");
        }
    }
}

