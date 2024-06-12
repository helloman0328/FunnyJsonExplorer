package org.example;

import java.util.ArrayList;
import java.util.List;

abstract class Component {
    protected String style;
    protected String icon;

    public Component(String style, String icon) {
        this.style = style;
        this.icon = icon;
    }

    public abstract void draw(String indent, boolean isLast);

    public static abstract class Builder<T extends Builder<T>> {
        protected String style;
        protected String icon;

        public Builder(String style, String icon) {
            this.style = style;
            this.icon = icon;
        }

        public abstract Component build();
        protected abstract T self();
    }
}

class Leaf extends Component {
    private String name;

    public Leaf(Leaf.Builder builder) {
        super(builder.style, builder.icon);
        this.name = builder.name;
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

    public static class Builder extends Component.Builder<Leaf.Builder> {
        private String name;

        public Builder(String name, String style, String icon) {
            super(style, icon);
            this.name = name;
        }

        @Override
        public Leaf build() {
            return new Leaf(this);
        }

        @Override
        protected Leaf.Builder self() {
            return this;
        }
    }
}

class Container extends Component {
    private String name;
    private int level;
    private List<Component> children;

    public Container(Container.Builder builder) {
        super(builder.style, builder.icon);
        this.name = builder.name;
        this.level = builder.level;
        this.children = builder.children;
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

    public static class Builder extends Component.Builder<Container.Builder> {
        private String name;
        private int level;
        private List<Component> children = new ArrayList<>();

        public Builder(String name, int level, String style, String icon) {
            super(style, icon);
            this.name = name;
            this.level = level;
        }

        public Builder addChild(Component component) {
            this.children.add(component);
            return this;
        }

        @Override
        public Container build() {
            return new Container(this);
        }

        @Override
        protected Container.Builder self() {
            return this;
        }
    }
}
