// 10.09.2019
public class TreeTest {

    public static void main(String[] args) {
        Node<Integer> root = new Node<Integer>(1); // node0

        Node<Integer> node01 = root.addChild(new Node<>(2, 100, 'a'));
        Node<Integer> node02 = root.addChild(new Node<>(9, 50, 'b'));
        Node<Integer> node03 = root.addChild(new Node<>(10, 120, 'c'));

        Node<Integer> node11 = node01.addChild(new Node<>(3, 230, 'k'));
        Node<Integer> node12 = node01.addChild(new Node<>(4, 90, 'd'));
        Node<Integer> node13 = node01.addChild(new Node<>(7, 60, 'm'));
        Node<Integer> node14 = node01.addChild(new Node<>(8, 140, 'p'));

        Node<Integer> node21 = node12.addChild(new Node<>(5, 75, 'e'));
        Node<Integer> node22 = node12.addChild(new Node<>(6, 85, 'l'));

        TreeTest.printTreeDfs(root);

    }


    public static <T> void printTreeDfs(Node<T> node) {
        System.out.println(node);
        node.getChildren().forEach(child -> printTreeDfs(child));
    }
}
