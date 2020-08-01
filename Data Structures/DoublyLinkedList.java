package LinearDS;

import java.util.Iterator;

/**
* @author theandrocoder
*/
public class DoublyLinkedList {
    public static void main(String[] args) {
        DLinkedList<Integer> dll = new DLinkedList<>();
        dll.add(35);
        dll.add(22);
        dll.set(1,36);
        dll.insert(0,12);
        dll.remove(2);
        System.out.println(dll.toString()+"\nsize="+dll.size());

        Iterator<Integer> it = dll.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
class DLinkedList<T> implements Iterable<T>{
    //***** Node inner class **********//
    private class Node<T>{
        T value;
        Node<T> next=null;
        Node<T> prev=null;
        public Node(T value){
            this.value=value;
            this.next=null;
            this.prev=null;
        }
    }
    //******* Node inner class *******//

    private int len;
    private Node<T> head=null;

    public int size(){
        return this.len;
    }

    /**
     * Linked list is empty or not
     * @return true if linked list is empty otherwise returns false
     */
    public boolean isEmpty(){
        return this.len==0;
    }

    /**
     * append an element to end of linked list
     * @param element the element to be appended
     */
    public void add(T element){
        if(head==null){
            Node<T> new_node = new Node<>(element);
            head=new_node;
        }else{
            Node temp=head;
            while(temp.next!=null)temp=temp.next;
            Node<T> new_node = new Node<>(element);
            temp.next=new_node;
            new_node.prev = temp;
        }
        len++;
    }

    /**
     * Gets element at position
     * @param index index of element to be returned
     * @return element at specified index
     */
    public T get(int index){
        if(index>=len || index<0)throw new IndexOutOfBoundsException();
        int i=0;
        Node temp=head;
        while(i++!=index)temp=temp.next;
        return (T)temp.value;
    }

    /**
     * replace element at index by another element
     * @param index the index to be replaced
     * @param element the new element
     */
    public void set(int index,T element){
        if(index<0 || index>=len) throw new IndexOutOfBoundsException();
        int i=0;
        Node temp=head;
        while(i++!=index)temp=temp.next;
        temp.value=element;
    }

    /**
     * remove all elements from linked list
     */
    public void clear(){
        this.head=null;
    }

    /**
     * Insert element at index
     * @param index the index at which element needs to be inserted
     * @param element the element to be inserted
     */
    public void insert(int index,T element){
        if(index>=len || index<0)throw new IndexOutOfBoundsException();
        Node<T> new_node = new Node<>(element);
        if(index==0){
            new_node.next=head;
            head.prev=new_node;
            head=new_node;
        }else{
            int i=0;
            Node temp=head;
            while(i++!=index-1){
                temp=temp.next;
            }
            new_node.next=temp.next;
            new_node.prev=temp;
            temp.next.prev=new_node;
            temp.next=new_node;
        }
        len++;
    }

    /**
     * Removes element at specified index
     * @param index
     */
    public void remove(int index){
        if(index<0 || index>=len)throw new IndexOutOfBoundsException();
        if(index==0){
            head.next.prev=null;
            head=head.next;
        }else{
            int i=0;
            Node temp=head;
            while(i++!=index){
                temp=temp.next;
            }
            temp.prev.next=temp.next;
            if(temp.next!=null)
            temp.next.prev=temp.prev;
        }
        len--;
    }

    /**
     * checks if linked list contains the element or not
     * @param element the element whose presence needs to be checked
     * @return true if element exists otherwise false
     */
    public boolean contains(T element){
        Node temp=head;
        while(temp!=null){
            if(temp.value.equals(element))return true;
            temp=temp.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index=0;
            @Override
            public boolean hasNext() {
                return index<len;
            }

            @Override
            public T next() {
                return (T)get(index++);
            }
        };
    }

    @Override
    public String toString() {
        String str="Head to tail Representation\n";
        Node temp=head;
        while(temp!=null){
            str+=temp.value+"->";
            temp=temp.next;
        }
        str=str.substring(0,str.length()-2);
        str+="\nTail to Head Representation\n";
        temp=head;
        while(temp.next!=null)temp=temp.next;
        while(temp!=null){
            str+=temp.value+"->";
            temp=temp.prev;
        }
        str=str.substring(0,str.length()-2);
        return str;
    }
}
