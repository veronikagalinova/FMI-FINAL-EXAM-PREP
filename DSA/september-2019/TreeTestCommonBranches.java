import java.util.ArrayList;
import java.util.List;

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
        System.out.println(node);
        node.getChildren().forEach(child -> printTreeDfs(child));
    }

    public static <T> List<String> commonBranches(Node<T> labeledTree, Node<T> u, Node<T> v, int k) {

        List<String> wordsWithSumHalfKNodeU = new ArrayList<>();
        commonBranchesUtil(u, 0, "", k, wordsWithSumHalfKNodeU);

        List<String> wordsWithSumHalfKNodeV = new ArrayList<>();
        commonBranchesUtil(v, 0, "", k, wordsWithSumHalfKNodeV);

        // TO DO - verify that word can exists more than once
        // when it exists in the to branches
        List<String> commonBranches = TreeTestCommonBranches.findCommonElements(wordsWithSumHalfKNodeU,
                wordsWithSumHalfKNodeV);
        return commonBranches;
    }

    static <T> void commonBranchesUtil(Node<T> node, int val, String word, int k, List<String> wordsWithSumHalfK) {
        // start dfs from the given node
        System.out.println(node);
        for (Node<T> child : node.getChildren()) {
            // weight value - add to sum
            // weight label - concat to word
            // !!! in this representation weight of X-Y edge is in node Y
            commonBranchesUtil(child, val + child.value(), word + child.label(), k, wordsWithSumHalfK);
        }

        if (val == k / 2) {
            wordsWithSumHalfK.add(word);
        }
        // if is leaf - reset word and val
        val = 0;
        word = "";
    }

    private static List<String> findCommonElements(List<String> a, List<String> b) {
        if (a.size() > b.size()) {
            a.retainAll(b);
        } else {
            b.retainAll(a);
        }
        return a;
    }
}
