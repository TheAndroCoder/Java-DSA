package singlylinklist;

import java.util.Iterator;

/**
 * Excercises on Singly Linked list implemented in java
 * @author theandrocoder
 */
public class SinglyLinkedListExcercises {
    public static void main(String[] args) {
        LinkedList<Integer> llist=new LinkedList<>();
        // Add Elements
        llist.add(1);
        llist.add(2);
        llist.add(3);
        llist.add(4);
        llist.add(3);
        llist.add(2);
        llist.add(1);


        //Remove an element
        llist.remove(3);


        //length of loop (if exists) in a linkedlist
        System.out.println("Length of loop = "+llist.loopLength());


        //check if a linked list is palindrome ( eg. 1->2->3->4->3->2->1 )
        // the below line is commented out since it changes the linkedlist and iteration becomes impossible.
        //System.out.println("Is Palindrome : "+llist.isPalindrome());


        // Find Intersection point of two linked list
        // findIntersection() defined in LinkedList class


        //Iterate through all elements in llist
        Iterator<Integer> it = llist.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
    }
}

class LinkedList<T> implements Iterable<T>{
    /************* Node Class ***************/
    private class Node<T>{
        T data;
        Node<T> next;
        public Node(T data){
            this.data=data;
            this.next=null;
        }
        public Node(){
            this.next=null;
            this.data=null;
        }
    }
    /************ Node class ***************/

    private Node<T> head;
    private int size;

    public LinkedList(){
        this.size=0;
        this.head=null;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return this.size==0;
    }
    public void add(T data){
        Node<T> node=new Node<>(data);
        if(head==null){
            head=node;
        }else{
            Node temp=head;
            while(temp.next!=null)temp=temp.next;
            temp.next=node;
        }
        size++;
    }
    public T get(int index){
        if(index<0 || index>=size)throw new IndexOutOfBoundsException();
        Node<T> temp=head;
        while(index--!=0)temp=temp.next;
        return temp.data;
    }
    public void remove(int index){
        if(index<0 || index>=size)throw new IndexOutOfBoundsException();
        if(index==0){
            head=head.next;
        }else{
            Node<T> temp=head;
            while(index--!=1)temp=temp.next;
            temp.next=temp.next.next;
        }
        size--;
    }

    /**
     * Based on Flyod's Cycle detection algorithm
     * @return length of loop (if exists), else 0
     */
    public int loopLength(){
        Node slow=head,fast=head;
        while(slow!=null && fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)return countLoopLength(slow);
        }
        return 0;
    }

    /**
     * Helper function of the above function
     * @param p : the node from where the cycle begins
     * @return the loop length
     */
    private int countLoopLength(Node p){
        int res=0;
        Node temp=p;
        while(temp.next!=p){
            res++;
            temp=temp.next;
        }
        return res;
    }

    public boolean isPalindrome(){
        Node head1=head;
        Node head2 = head;
        int half = size/2;
        while(half--!=0)head2=head2.next;
        if(size%2!=0)head2=head2.next;

        //reverse the 2nd list
        head2=reverse(head2);
        while(head1!=null && head2!=null){
            if(head1.data.equals(head2.data)){
                head1=head1.next;
                head2=head2.next;
            }else return false;
        }

        return true;
    }

    /**
     * Reverse the link list whose head is given
     * @param head : head of linkedlist to be reversed
     * @return the new head of linkedlist
     */
    private Node reverse(Node head){
        Node prev=null;
        Node curr=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr = next;
        }
        return prev;
    }

    /**
     * find intersection using difference of node counts
     * @param head1 : head of first linked list
     * @param head2 : head of second linked list
     * @return intersection node
     */
    public Node<T> findIntersection(Node<T> head1, Node<T> head2){
        int length1=0,length2=0;
        Node temp=head1;
        while(temp!=null){
            length1++;
            temp=temp.next;
        }
        temp=head2;
        while(temp!=null){
            length2++;
            temp=temp.next;
        }
        int diff = Math.abs(length1-length2);
        if(length1>length2){
            // list1 is longer
            while(diff--!=0)head1=head1.next;
            while(head1!=null || head2!=null){
                if(head1==head2)return head1;
            }
            return null;
        }else if(length2>length1){
            //list2 is longer
            while(diff--!=0) head2=head2.next;
            while(head1!=null || head2!=null){
                if(head1==head2)return head1;
            }
            return null;
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index=0;
            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public T next() {
                return get(index++);
            }
        };
    }
}
