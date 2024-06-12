package org.example;

import java.util.ArrayList;
import java.util.List;

interface ComponentFactory {
    Leaf createLeaf(String name);
    Container createContainer(String name, int level);
}

class TreeandpokerComponentFactory implements ComponentFactory {
    @Override
    public Leaf createLeaf(String name) {
        return new Leaf(name, "tree", "poker-face");
    }

    @Override
    public Container createContainer(String name, int level) {
        return new Container(name, level, "tree", "poker-face");
    }
}
class TreeandstarComponentFactory implements ComponentFactory {
    @Override
    public Leaf createLeaf(String name) {
        return new Leaf(name, "tree", "star");
    }

    @Override
    public Container createContainer(String name, int level) {
        return new Container(name, level, "tree", "star");
    }
}
class RectangleandstarComponentFactory implements ComponentFactory {
    @Override
    public Leaf createLeaf(String name) {
        return new Leaf(name, "rectangle", "star");
    }

    @Override
    public Container createContainer(String name, int level) {
        return new Container(name, level, "rectangle", "star");
    }
}
class RectangleandpokerComponentFactory implements ComponentFactory {
    @Override
    public Leaf createLeaf(String name) {
        return new Leaf(name, "rectangle", "poker-face");
    }

    @Override
    public Container createContainer(String name, int level) {
        return new Container(name, level, "Rectangle", "poker-face");
    }
}
abstract class Component {
    protected String style;
    protected String icon;

    public Component(String style, String icon) {
        this.style = style;
        this.icon = icon;
    }

    public abstract void draw(String indent, boolean isLast);
}

class Leaf extends Component {
    private String name;

    public Leaf(String name, String style, String icon) {
        super(style, icon);
        this.name = name;
    }

    @Override
    public void draw(String indent, boolean isLast) {
        String iconChar = "";
        if (this.icon.equals("poker-face")) {
            iconChar = "♣";
        } else if (this.icon.equals("star")) {
            iconChar = "+";
        }

        if (this.style.equals("tree")) {
            System.out.println(indent + (isLast ? "└─ " : "├─ ") + iconChar + " " + this.name);
        } else if (this.style.equals("rectangle")) {
            String connector = isLast ? "└─ " : "├─ ";
            System.out.println(indent + connector + iconChar + " " + this.name + " " + "─".repeat(30 - indent.length() - this.name.length() - connector.length() - 2) + "┤");
        }
    }
}

class Container extends Component {
    private String name;
    private int level;
    private List<Component> children;
    private ComponentFactory factory;

    public Container(String name, int level, String style, String icon) {
        super(style, icon);
        this.name = name;
        this.level = level;
        this.children = new ArrayList<>();
    }

    public Container(String name, int level, String style, String icon, ComponentFactory factory) {
        super(style, icon);
        this.name = name;
        this.level = level;
        this.children = new ArrayList<>();
        this.factory = factory;
    }

    public void setFactory(ComponentFactory factory) {
        this.factory = factory;
    }

    public void add(Component component) {
        this.children.add(component);
    }

    public void remove(Component component) {
        this.children.remove(component);
    }

    public List<Component> getChildren() {
        return children;
    }

    @Override
    public void draw(String indent, boolean isLast) {
        String iconChar = " ";
        if (this.icon.equals("poker-face")) {
            iconChar = "♠";
        } else if (this.icon.equals("star")) {
            iconChar = "*";
        }

        if (this.style.equals("tree")) {
            System.out.println(indent + (isLast ? "└─ " : "├─ ") + iconChar + " " + this.name);
            indent += isLast ? "    " : "|   ";
        } else if (this.style.equals("rectangle")) {
            String connector = isLast ? "└──" : "├─ ";
            System.out.println(indent + connector + iconChar + " " + this.name + " " + "─".repeat(30 - indent.length() - this.name.length() - connector.length() - 2) + "┤");
            indent += "────"; // Assuming firstCall is always true here
        }

        for (int i = 0; i < this.children.size(); i++) {
            boolean isLastChild = i == this.children.size() - 1;
            this.children.get(i).draw(indent, isLastChild);
        }
    }
}
