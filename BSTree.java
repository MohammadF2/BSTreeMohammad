public class BSTree<T extends Comparable<T>> {

    private TNode<T> root;

    // @author @m7md.png AKA Mohammad Faraj

    public BSTree() {}

    public TNode find(T data) { return find(data, root); }
    public TNode find(T data, TNode node) {
        if (node!= null) {
            int comp = node.data.compareTo(data);
            if (comp == 0)
                return node;
            else if (comp > 0 && node.hasLeft()) return find(data, node.left);
            else if (comp < 0 && node.hasRight()) return find(data, node.right);
        }
        return null;
    }
    public TNode largest() { return largest(root); }
    public TNode<T> largest(TNode node) {
        if(node!= null){
            if(!node.hasRight())
                return (node);
            return largest(node.right);
        }
        return null;
    }
    public TNode smallest() { return smallest(root); }
    public TNode<T> smallest(TNode node) {
        if(node!= null){
            if(!node.hasLeft())
                return (node);
            return smallest(node.left);
        }
        return null;
    }
    public int height() { return height(root); }
    public int height(TNode node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        int left = 0;
        int right = 0;
        if (node.hasLeft())
            left = height(node.left);
        if (node.hasRight())
            right = height(node.right);
        return (left > right) ? (left + 1) : (right + 1);
    }
    public void insert(T data) {
        if (isEmpty())
            root = new TNode(data);
        else
            insert(data, root);
    }
    private void insert(T data, TNode node) {
        if (data.compareTo((T) node.data) >= 0) { // insert into right subtree
            if (!node.hasRight())
                node.right = new TNode(data);
            else
                insert(data, node.right);
        } else { // insert into left subtree
            if (!node.hasLeft())
                node.left = new TNode(data);
            else
                insert(data, node.left);
        }
    }
    public boolean isEmpty(){
        if(root != null)
            return false;
        else
            return true;
    }
    public TNode delete(T data) {
        TNode current = root;
        TNode parent = root;
        boolean isLeftChild = false;
        if (isEmpty()) return null; // tree is empty
        while (current != null && !current.data.equals(data)) {
            parent = current;
            if (data.compareTo((T)current.data) < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }
        if (current == null) return null; // node to be deleted not found
        // case 1: node is a leaf
        if (!current.hasLeft() && !current.hasRight()) {
            if (current == root) // tree has one node
                root = null;
            else {
                if (isLeftChild) parent.left = null;
                else parent.right = null;
            }
        }
        else if (current.hasLeft()) { // current has left child only
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.hasRight()) { // current has right child only
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        else {
            TNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return current;
    }
    private TNode getSuccessor(TNode node) {
        TNode parentOfSuccessor = node;
        TNode successor = node;
        TNode current = node.right;
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right) { // fix successor connections
            parentOfSuccessor.left = successor.right;
            successor.right = node.right;
        }
        return successor;
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


    // coded by mohammad faraj

}
