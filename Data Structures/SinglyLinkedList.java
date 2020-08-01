package LinearDS;

/**
 * @author theandrocoder 
 */
public class SinglyLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(56);
        list.add(45);
        list.set(1,23);
        list.insert(1,44);
        list.insert(2,77);
        list.insert(0,23);
        list.insert(0,23);
        list.remove(3);
        list.remove(new Integer(56));
        System.out.println(list.toString()+", size is "+list.size());
    }
}
class LinkedList<T>{
    //***** Node inner class **********//
    class Node<T>{
        T value;
        Node<T> next=null;
        public Node(T value){
            this.value=value;
            this.next=null;
        }
    }
    //******* Node inner class *******//

    private Node head=null;
    private int len=0;

    public LinkedList(){
        this.len=0;
    }

    /**
     * size of linked list
     * @return number of elements in linkedlist
     */
    public int size(){
        return this.len;
    }

    /**
     * Returns whether linked list is empty or not
     * @return true if list is empty or else false
     */
    public boolean isEmpty(){
        return this.len==0;
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
        while(i++!=index){
            temp=temp.next;
        }
        return (T)temp.value;
    }

    /**
     * append an element to end of linked list
     * @param element the element to be appended
     */
    public void add(T element){
        if(this.head==null){
            this.head=new Node(element);
        }else{
            Node temp=head;
            while(temp.next!=null)temp=temp.next;
            Node new_node = new Node(element);
            temp.next=new_node;
        }
        len++;

    }

    /**
     * replace element at index by another element
     * @param index the index to be replaced
     * @param element the new element
     */
    public void set(int index,T element){
        if(index>=len || index<0)throw new IndexOutOfBoundsException();
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
        if(index==0){
            Node new_node = new Node(element);
            new_node.next=head;
            head=new_node;
            len++;
            return;
        }
        int i=0;
        Node temp=head;
        while(i++!=index-1){
            temp=temp.next;
        }
        Node new_node = new Node(element);
        new_node.next=temp.next;
        temp.next=new_node;
        len++;
    }

    /**
     * Removes element at specified index
     * @param index
     */
    public void remove(int index){
        if(index>=len || index<0)throw new IndexOutOfBoundsException();
        if(index==0){
            head=head.next;
        }else{
            int i=0;
            Node temp=head;
            while(i++!=index-1)temp=temp.next;
            temp.next=temp.next.next;
        }
        len--;
    }

    /**
     * Remove element if exists
     * @param element the element to be removed
     * @return true if element exists and is removed otherwise false
     */
    public boolean remove(T element){
        Node temp=head;
        if(temp.value.equals(element)){
            head=head.next;
            len--;
            return true;
        }
        while(temp.next!=null){
            if(temp.next.value.equals(element)){
                temp.next=temp.next.next;
                len--;
                return true;
            }
            temp=temp.next;
        }
        return false;
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
    public String toString() {
        String str="";
        Node temp=head;
        while(temp!=null){
            str+=temp.value+" ";
            temp=temp.next;
        }
        return str;
    }
}

