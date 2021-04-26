public class binarysearchtree<T extends Comparable<T>> {
    private int nodecount= 0;
    private node root;

    class node {
        node left, right;
        T data;

        public node(node left, node right, T elem) {
            data = elem;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodecount;
    }

    public void add(T elem) {
        if (contains(elem))
            return; // element already exists
        root = add(root, elem);
        nodecount++;
    }

    private node add(node n, T elem) {
        if (n == null)
            return new node(null, null, elem);
        if (elem.compareTo(n.data) < 0)
            n.left = add(n.left, elem);
        else if (elem.compareTo(n.data) > 0)
            n.right = add(n.right, elem);
        return n;
    }

    public void remove(T elem) {
        if (contains(elem)) {
            root = remove(root, elem);
            nodecount--;
        }
    }

    private node remove(node n, T elem) {
        if (n == null)
            return n;
        int cmp = elem.compareTo(n.data);
        if (cmp < 0)
            remove(n.left, elem);
        else if (cmp > 0)
            remove(n.right, elem);
        else {
            if (n.left == null) {
                node r = n.right;
                n.data = null;
                n = null;
                return r;
            } else if (n.right == null) {
                node l = n.left;
                n.data = null;
                n = null;
                return l;
            } else {
                node tmp = digleft(n.right);
                n.data = tmp.data;
                n.right = remove(n.right, tmp.data);
            }
        }
        return n;
    }

    private node digleft(node n) {
        node curr = n;
        while (curr.left != null)
            curr = curr.left;
        return curr;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(node n, T elem) {
        if (n == null)
            return false;
        int c = elem.compareTo(n.data);
        if (c < 0)
            return contains(n.left, elem);
        else if (c > 0)
            return contains(n.right, elem);
        else
            return true;
    }

    public int height(node root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public void levelOrder(node root) { // Level order = BFS
        java.util.LinkedList<node> q = new java.util.LinkedList<node>();
        q.add(root);
        while (!q.isEmpty()) {
            node temp = q.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) q.add(temp.left);
            if (temp.right!= null) q.add(temp.right);
        }
    }

    public void preOrder(node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(node root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public void postOrder(node root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    public static void main(String[] args) {
        binarysearchtree<Character> b=new binarysearchtree<>();
    }
}