public class Tree {

    private TNode root;

    // @author @m7md.png AKA Mohammad Faraj

    public Tree() {
        this.root = null;
    }
    public void traverseInOrder() { traverseInOrder(root); }
    private void traverseInOrder(TNode node) {
        if (node != null) {
            if (node.left != null)
                traverseInOrder(node.left);
            System.out.print(node + " ");
            if (node.right != null)
                traverseInOrder(node.right);
        }
    }


}
