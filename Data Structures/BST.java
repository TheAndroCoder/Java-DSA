package TreeDS;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

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
            //it.next();
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

    public int size(){
        return this.size;
    }

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

    public boolean remove(T data){
        if(!contains(data))return false;
        root=remove(root,data);
        size--;
        return true;
    }
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
    private boolean contains(T data) {
        return contains(root,data);
    }
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

    public int height(){
        return height(root);
    }
    public int height(Node node){
        if(node==null)return 0;
        return Math.max(height(node.left),height(node.right))+1;
    }
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

    private Iterator<T> preOrder(){
        int expectedNodeCount=size;
        Stack<Node<T>> st = new Stack<>();
        st.push(root);
        return new Iterator<T>() {
            Node<T> node=root.left;
            @Override
            public boolean hasNext() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                return root!=null && !st.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                T data = node.data;
                while(node !=null){
                    st.push(node);
                    node=node.left;
                }
                node=st.pop();
                node=node.right;
                return data;

            }
        };
    }
    private Iterator<T> inOrder(){
        int expectedNodeCount=size;
        Stack<Node<T>> st = new Stack<>();
        st.push(root);
        return new Iterator<T>() {
            Node<T> node=root.left;
            boolean bool=true;
            @Override
            public boolean hasNext() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                return root!=null && !st.isEmpty();
            }

            @Override
            public T next() {
                if(expectedNodeCount!=size)throw new ConcurrentModificationException();
                while(node !=null){
                    if(bool)
                    st.push(node);
                    bool=true;
                    node=node.left;
                }
                node=st.pop();
                T data = node.data;
                node=node.right;
                if(node!=null)
                {
                    st.push(node);
                    bool=false;
                }
                return data;

            }
        };
    }
    private Iterator<T> postOrder(){
        return null;
    }
    private Iterator<T> levelOrder(){
        return null;
    }
}
