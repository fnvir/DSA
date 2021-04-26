public class bSearchtree {
	node root;
	
    static class node{
        private int data;
        node left,right;
        
        public node(int data) {
            this.data=data;
        }
    }
    
    public node search(node root, int key) {
        if(root==null || root.data==key) return root;
        if(root.data<key) return search(root.right, key);
        return search(root.left, key);
    }
    
    public void insert(int key) {
    	root=recurinsert(root,key);
    }
    
    private node recurinsert(node root, int key) {
    	if(root==null) return new node(key);
    	if(key<root.data) root.left=recurinsert(root.left, key);
    	else if(key>root.data) root.right=recurinsert(root.right, key);
    	return root;
    }
    
    public void remove(int key) {
    	root=recurRemove(root,key);
    }
    
    private node recurRemove(node root,int key) {
    	if(root==null) return root;
    	if(key<root.data) root.left=recurRemove(root.left,key);
    	else if(key>root.data) root.right=recurRemove(root.right,key);
    	else {
    		if(root.left==null) return root.right;
    		else if(root.right==null) return root.left;
    		root.data=digLeft(root.right).data;
    		root.right=recurRemove(root.right,root.data);
    	}
    	return root;
    }
    
    private node digLeft(node r) {
    	node curr=r;
    	while(curr.left!=null) curr=curr.left;
    	return curr;
    }
    
    public static int getHeight(node root) {
        if(root==null) return 0;
        int left=getHeight(root.left)+1;
        int right=getHeight(root.right)+1;
        return Math.max(left, right);
    }
    
    public static void main(String[] args) {
		bSearchtree bst=new bSearchtree();
		bst.insert(5);
		bst.insert(9);
		bst.insert(4);
		bst.insert(2);
		bst.insert(1);
		bst.insert(18);
		bst.insert(56);
		bst.insert(0);
		bst.insert(7);
		bst.remove(9);
//		for(int i=0;i<9;i++) bst.insert(i);
		System.out.println(getHeight(bst.root));
//		System.out.println(bst.search(bst.root, 9)!=null?"YES":"NO");
	}
}