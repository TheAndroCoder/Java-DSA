package TreeDS;

import java.util.*;

/**
 * Binary Search Tree formation and traversals
 * @author theandrocoder 
 */
public class BSTMain {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(10);
        bst.add(9);
        bst.add(12);
        bst.add(3);
        bst.add(4);
        bst.add(1);
        Iterator<Integer> it = bst.traverse(BST.TreeTraversalOrder.IN_ORDER);
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println("\nPreorder Traversal");
        Iterator<Integer> preorder_it = bst.traverse(BST.TreeTraversalOrder.PRE_ORDER);
        while(preorder_it.hasNext()){
            System.out.print(preorder_it.next()+" ");
        }
        System.out.println("\nPostOrder Traversal");
        Iterator<Integer> postorder_it = bst.traverse(BST.TreeTraversalOrder.POST_ORDER);
        while(postorder_it.hasNext()){
            System.out.print(postorder_it.next()+" ");
        }
        System.out.println("\nLevelOrder Traversal");
        Iterator<Integer> levelorder_it = bst.traverse(BST.TreeTraversalOrder.LEVEL_ORDER);
        while(levelorder_it.hasNext()){
            System.out.print(levelorder_it.next()+" ");
        }
    }
}
class BST<T extends Comparable<T>>{
    private class Node<T>{
        T data;
        Node left,right;
        public Node(){
            this(null);
        }
        public Node(T data){
            this.data=data;
        }
    }

    private Node<T> root;
    private int size;

    public BST(){
        this.root=null;
        this.size=0;
    }

    /**
     * returns number of elements in BST at any point of time
     * @return number of elements
     */
    public int size(){
        return this.size;
    }

    /**
     * returns if BST is empty or not
     * @return true if BST is empty, otherwise returns false
     */
    public boolean isEmpty(){
        return this.size==0;
    }

    /**
     * checks if element already exists, if not then adds to appropriate location
     * @param data : the element to be added
     * @return true if element is successfully added , otherwise returns false
     */
    public boolean add(T data){
        Node<T> new_node = new Node<>(data);
        if(contains(data))return false;
        if(root==null){
            root=new_node;
            return true;
        }
        else{
            add(root,data);
        }
        size++;
        return true;
    }

    /**
     * Recursive version of add method
     * @param root
     * @param data
     * @return
     */
    private Node<T> add(Node<T> root,T data){
        if(root==null){
            Node<T> node = new Node<>(data);
            return node;
        }
        else{
            if(data.compareTo(root.data)<0){
                root.left = add(root.left,data);
            }else{
                root.right = add(root.right,data);
            }
        }
        return root;
    }

    /**
     * removes a node from BST
     * @param data : the value of the node which is to be removed
     * @return true if node is found and removed, otherwise returns false
     */
    public boolean remove(T data){
        if(!contains(data))return false;
        root=remove(root,data);
        size--;
        return true;
    }

    /**
     * Recursive version of remove method
     * @param root
     * @param data
     * @return
     */
    private Node<T> remove(Node<T> root,T data){
        if(root==null)return null;
        int cmp=data.compareTo(root.data);
        if(cmp<0){
            root.left=remove(root.left,data);
        }else if(cmp>0){
            root.right=remove(root.right,data);
        }else{
            //we found the node to be removed
            if(root.left==null){
                Node rightChild = root.right;
                //root.data=null;
                root=null;
                return rightChild;
            }else if(root.right==null){
                Node leftChild = root.left;
                //root.left=null;
                root=null;
                return leftChild;
            }else{
                // both are non - null
                // find leftmost node in right-subtree
                Node<T> temp=root.right;
                while(temp.left!=null)temp=temp.left;
                root.data=temp.data;
                root.right = remove(root.right,temp.data);
            }
        }
        return root;
    }

    /**
     * checks if the data is present in BST or not
     * @param data : the data whose presence is to be checked
     * @return true if the data is found, otherwise returns false
     */
    private boolean contains(T data) {
        return contains(root,data);
    }

    /**
     * Recursive version of contains method
     * @param node
     * @param data
     * @return
     */
    private boolean contains(Node<T> node,T data){
        if(node ==null)return false;
        int cmp=data.compareTo(node.data);
        if(cmp<0){
            return contains(node.left,data);
        }else if(cmp>0){
            return contains(node.right, data);
        }
        else return true;

    }

    /**
     * Returns height o0f BST at any point of time
     * @return : integer : height of tree
     */
    public int height(){
        return height(root);
    }

    /**
     * Recursive version of the above method
     * @param node
     * @return
     */
    public int height(Node node){
        if(node==null)return 0;
        return Math.max(height(node.left),height(node.right))+1;
    }
    
    //**************** Iterators for tree traversals ********************//
    public enum TreeTraversalOrder{
        PRE_ORDER,IN_ORDER,POST_ORDER,LEVEL_ORDER
    }
    public Iterator<T> traverse(TreeTraversalOrder order){
        switch (order){
            case PRE_ORDER: return preOrder();
            case IN_ORDER: return inOrder();
            case POST_ORDER: return postOrder();
            case LEVEL_ORDER: return levelOrder();
            default: return null;
        }
    }

    /**
     * Preorder Traversal of BST
     * @return Iterator object
     */
    private Iterator<T> preOrder(){
        int expectedNodeCount=size;
        Stack<Node<T>> st = new Stack<>();
        st.push(root);
        return new Iterator<T>() {
            Node<T> node=root;
            int i=0;
            @Override
            public boolean hasNext() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                return !st.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                node=st.pop();
                T data = node.data;
                if(node.right!=null){
                    st.push(node.right);
                }
                if(node.left!=null){
                    st.push(node.left);
                }
                return data;

            }
        };
    }

    /**
     * InOrder Traversal of BST
     * @return : Iterator object
     */
    private Iterator<T> inOrder(){
        int expectedNodeCount=size;
        Stack<Node<T>> st = new Stack<>();
        return new Iterator<T>() {
            Node<T> node=root;
            @Override
            public boolean hasNext() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                return node!=null || !st.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                while(node!=null){
                    st.push(node);
                    node=node.left;
                }
                node=st.pop();
                T data = node.data;
                node=node.right;
                return data;

            }
        };
    }

    /**
     * PostOrder Traversal of BST
     * @return : Iterator object
     */
    private Iterator<T> postOrder(){
        int expectedNodeCount=size;
        Stack<Node<T>> st = new Stack<>();
        st.push(root);
        Stack<Node<T>> out = new Stack<>();
        while(!st.empty()){
            Node<T> curr=st.pop();
            out.push(curr);
            if(curr.left!=null)st.push(curr.left);
            if(curr.right!=null)st.push(curr.right);
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                return !out.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                return out.pop().data;

            }
        };
    }

    /**
     * Level Order Traversal of BST
     * @return : Iterator Object
     */
    private Iterator<T> levelOrder(){
        Queue<Node<T>> q=new LinkedList<>();
        q.add(root);
        return new Iterator<T>() {
            Node<T> node=root;
            @Override
            public boolean hasNext() {
                return !q.isEmpty();
            }

            @Override
            public T next() {
                node=q.poll();
                T data=node.data;
                if(node.left!=null)q.add(node.left);
                if(node.right!=null)q.add(node.right);
                return data;
            }
        };
    }
}
