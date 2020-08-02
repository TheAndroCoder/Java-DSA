package LinearDS;

import java.util.Iterator;

/**
 * @author theandrocoder
 */

public class QueueMain {
    public static void main(String[] args) {
        Queue<Integer> q=new Queue<>();
        q.enqueue(12);
        q.enqueue(13);
        q.enqueue(14);
        q.dequeue();
        Iterator<Integer> it=q.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println("\nSize of queue="+q.size());
    }
}
class Queue<T> implements Iterable<T>{

    private T arr[];
    private int capacity;
    private int front,rear;

    public Queue(){
        this.capacity=16;
        this.front=this.rear=-1;
        arr=(T[])new Object[this.capacity];
    }
    public Queue(int capacity){
        if(capacity<=0)throw new IllegalArgumentException("capacity should be non-zero and non-negative");
        this.front=this.rear=-1;
        arr=(T[])new Object[this.capacity];
    }

    /**
     * Number of elements in queue
     * @return size of queue at any point of time
     */
    public int size(){
        return rear-front;
    }

    public void enqueue(T element){
        if(rear>=capacity){
            System.out.println("Queue capacity exceeded");
            return;
        }
        arr[++rear]=element;
    }
    
    public boolean isEmpty(){
        return size()==0;
    }

    /**
     * Remove front most element from queue
     * @return front most element
     */
    public T dequeue(){
        if(front+1<0 || rear<=front)throw new NullPointerException();
        return arr[++front];
    }

    /**
     * View the topmost element in queue without removing it
     * @return element T
     */
    public T peek(){
        if(front<0 || front<rear)throw new NullPointerException();
        return arr[front+1];
    }

    /**
     * Iterate over the queue
     * @return iterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index=front+1;
            @Override
            public boolean hasNext() {
                return index>front && index<=rear;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }


}
