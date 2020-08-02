import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TreeTestCommonBranches {

    public static void main(String[] args) {
        Node<String> root = new Node<>("A"); // node0

        Node<String> node01 = root.addChild(new Node<>("B", 1, 'e'));
        Node<String> node02 = root.addChild(new Node<>("C", 4, 'o'));


        Node<String> node11 = node01.addChild(new Node<>("D", 6, 'd'));
        Node<String> node12 = node01.addChild(new Node<>("E", 10, 'a'));
        Node<String> node13 = node01.addChild(new Node<>("F", 8, 'm'));

        Node<String> node21 = node12.addChild(new Node<>("I", 20, 'b'));
        Node<String> node22 = node12.addChild(new Node<>("J", 4, 'b'));

        Node<String> node14 = node02.addChild(new Node<>("G", 10, 'a'));
        Node<String> node15 = node02.addChild(new Node<>("H", 6, 'd'));

        Node<String> node23 = node14.addChild(new Node<>("K", 20, 'b'));

        // print common branches
        int k = 60;
        List<String> commonBranches = TreeTestCommonBranches.commonBranches(root, node01, node02, k);
        System.out.println(commonBranches.size());
        commonBranches.forEach(System.out::println);

    }

    public static <T> void printTreeDfs(Node<T> node) {
//        System.out.println(node);
        node.getChildren().forEach(child -> printTreeDfs(child));
    }

    public static <T> List<String> commonBranches(Node<T> labeledTree, Node<T> u, Node<T> v, int k) {

        List<Pair<String, Integer>> nodeUWordsVals = new ArrayList<>();
        commonBranchesUtil(u, 0, "", k, nodeUWordsVals);

        List<Pair<String, Integer>> nodeVWordsVals = new ArrayList<>();
        commonBranchesUtil(v, 0, "", k, nodeVWordsVals);

        return TreeTestCommonBranches.findCommonElements(nodeUWordsVals,
                nodeVWordsVals, k);
    }

    static <T> void commonBranchesUtil(Node<T> node, int val, String word, int k,
                                       List<Pair<String, Integer>> nodeWordVal) {
        // start dfs from the given node
        System.out.println(node);
        for (Node<T> child : node.getChildren()) {
            // weight value - add to sum
            // weight label - concat to word
            // !!! in this representation weight of X-Y edge is in node Y
            commonBranchesUtil(child, val + child.value(), word + child.label(), k, nodeWordVal);
        }

        if (node.isLeaf()) {
            nodeWordVal.add(new Pair<>(word, val));
            // if is leaf - reset word and val
            val = 0;
            word = "";
        }

    }

    private static List<String> findCommonElements(List<Pair<String, Integer>> a,
                                                   List<Pair<String, Integer>> b,
                                                   int k) {

        if (a.size() > b.size()) {
            return b.stream().filter(bElem ->
                    a.stream().anyMatch(aElem ->
                            aElem.getKey().equals(bElem.getKey())
                                    && aElem.getValue() + bElem.getValue() == k))
                    .map(Pair::getKey)
                    .collect(Collectors.toList());
        }

        return a.stream().filter(aElem ->
                b.stream().anyMatch(bElem ->
                        aElem.getKey().equals(bElem.getKey())
                                && aElem.getValue() + bElem.getValue() == k))
                .map(Pair::getKey)
                .collect(Collectors.toList());
    }
}
