package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FunnyJsonExplorer {
    private ComponentFactory componentFactory;
    private Component root;

    public FunnyJsonExplorer(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        this.root = null;
    }

    private Component buildComponent(JSONObject data,String sytle,String icon) throws JSONException {
        String name = data.optString("name", "Null");
        int level = data.optInt("level", 0);
        if (data.has("children")) {
            Container container = componentFactory.createContainer(name, level, sytle, icon); // Assuming default style and icon
            JSONArray children = data.getJSONArray("children");
            for (int i = 0; i < children.length(); i++) {
                JSONObject childData = children.getJSONObject(i);
                container.addChild(buildComponent(childData,sytle,icon));
            }
            return container;
        } else {
            return componentFactory.createLeaf(name, sytle, icon); // Assuming default style and icon
        }
    }

    public void load(String jsonData,String style,String icon) throws JSONException {
        JSONObject parsedData = new JSONObject(jsonData);
        this.root = buildComponent(parsedData,style,icon);
    }

    public void show() {
        if (this.root != null) {
            System.out.println("Visualizing JSON with style " + this.componentFactory.getStyle() + " and icon " + this.componentFactory.getIcon());
            this.root.draw("", true); // Passing true for isLast
        } else {
            System.out.println("No data loaded.");
        }
    }
}
