package LinearDS;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
* @author theandrocoder
*/
public class StackMain {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        System.out.println(stack.pop());
        Iterator<Integer> it = stack.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}

class Stack<T> implements Iterable<T>{
    private T arr[];
    private int top;
    private int capacity;
    public Stack(){
        this.top=0;
        this.capacity=16;
        this.arr=(T[])new Object[this.capacity];
    }
    public Stack(int capacity){
        this.top=0;
        this.capacity=capacity;
        this.arr=(T[])new Object[this.capacity];
    }

    public void push(T element){
        if(top>=capacity){
            System.out.println("Stack capacity exceeded");
        }
        arr[top++]=element;
    }
    public T pop(){
        if(top<=0)throw new EmptyStackException();
        return arr[--top];
    }

    public T peek(){
        if(top<0) throw new EmptyStackException();
        return arr[top-1];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index=0;
            @Override
            public boolean hasNext() {
                return index<top;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }
}
