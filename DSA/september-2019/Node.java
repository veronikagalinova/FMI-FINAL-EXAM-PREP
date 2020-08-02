import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Weight weight;

    public Node(T data) {
        this.data = data;
        this.weight = new Weight(0, Character.MIN_VALUE);
    }

    public Node(T data, Integer value, Character label) {
        this.data = data;
        this.weight = new Weight(value, label);
    }

    public Node<T> addChild(Node<T> child) {
        this.children.add(child);
        return child;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }
    
    public boolean isLeaf() {
        return getChildren().size() == 0;
    }

    public int value() {
        return weight.getValue();
    }

    public char label() {
        return weight.getLabel();
    }


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", value=" + value() +
                ", label=" + label() +
                '}';
    }

    static class Weight {
        private int value;
        private char label;

        public Weight(Integer value, Character label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public char getLabel() {
            return label;
        }
    }
}
