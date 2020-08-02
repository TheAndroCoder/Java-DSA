package LinearDS;


/**
 * Priority Queue implementation using singly LinkedList
 * @author theandrocoder 
 */
public class PriorityQueueMain {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.insert(1,11);
        pq.insert(2,23);
        pq.insert(4,22);
        pq.insert(0,99);
        pq.insert(10,3);
        pq.insert(3,4);
        pq.insert(9,98);
        pq.poll();
        pq.printQueue();
    }
}

class PriorityQueue<T extends Comparable<T>>{
    private class Node<T>{
        T data;
        int priority;
        Node next;

        public Node(){
            this.data=null;
            this.priority=0;
            this.next=null;
        }
        public Node(int priority,T data){
            this.priority=priority;
            this.data=data;
            this.next=null;
        }
    }
    private int size=0;
    private Node<T> head=null;

    public PriorityQueue(){
        this.size=0;
        this.head=null;
    }

    public int size(){
        return size;
    }

    /**
     * if priority queue is empty or not
     * @return true if priority queue is empty otherwise false
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * insert an element into priority queue
     * @param priority priority of element to be inserted, more the priority more closer it is to head
     * @param data element to bew inserted
     */
    public void insert(int priority,T data){
        Node<T> new_node = new Node<>(priority,data);
        if(head==null){
            head=new_node;
        }else if(head.priority<priority){
            new_node.next=head;
            head=new_node;
        }else if(head.next==null){
            head.next=new_node;
        }else{
            Node p1=head,p2=head.next;
            while(p2!=null && (priority<p1.priority && priority<p2.priority)){
                p1=p1.next;
                p2=p2.next;
            }
            p1.next=new_node;
            new_node.next=p2;
        }
        this.size++;
    }

    /**
     * remove the highest priority element from list
     * @return element with highest priority
     */
    public T poll(){
        T data = head.data;
        head=head.next;
        this.size--;
        return data;
    }

    public T peek(){
        return head.data;
    }

    public void printQueue(){
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
    }


}
