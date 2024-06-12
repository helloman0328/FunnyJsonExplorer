package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FunnyJsonExplorer {
    private ComponentFactory factory;
    private Component root;

    public FunnyJsonExplorer(ComponentFactory factory) {
        this.factory = factory;
        this.root = null;
    }

    private Component buildComponent(JSONObject data) throws JSONException {
        String name = data.optString("name", "Null");
        int level = data.optInt("level", 0);
        if (data.has("children")) {
            Container container = factory.createContainer(name, level);
            JSONArray children = data.getJSONArray("children");
            for (int i = 0; i < children.length(); i++) {
                JSONObject childData = children.getJSONObject(i);
                container.add(buildComponent(childData));
            }
            return container;
        } else {
            return factory.createLeaf(name);
        }
    }

    public void load(String jsonData) throws JSONException {
        JSONObject parsedData = new JSONObject(jsonData);
        this.root = buildComponent(parsedData);
    }

    public void show() {
        if (this.root != null) {
            System.out.println("Visualizing JSON with style " + factory.getClass().getSimpleName() + " and icon " + factory.getClass().getSimpleName());
            this.root.draw("", true); // Passing true for isLast
        } else {
            System.out.println("No data loaded.");
        }
    }
}
