import java.util.Iterator;

public class DynamicArray {
    public static void main(String[] args) {
        Array<Integer> dynamic=new Array<>();
        dynamic.add(25);
        dynamic.add(30);
        System.out.println(dynamic.toString());
        dynamic.add(90);
        dynamic.add(45);
        dynamic.remove(2);
        System.out.println(dynamic.toString());
        dynamic.remove(new Integer(25));
        System.out.println(dynamic.toString());
        System.out.println("Size is "+dynamic.size());
    }
}
class Array <T> implements Iterable<T>{

    private T[] arr;
    private int len=0; // length user thinks array is
    private int capacity=0; // actual capacity of dynamic array

    public Array(){
        this(16);
    }

    public Array(int capacity){
        if(capacity<0)throw new IllegalArgumentException("Illegal capacity "+capacity);
        this.capacity=capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){
        return this.len;
    }
    public boolean isEmpty(){
        return this.len==0;
    }

    public T get(int index){
        return arr[index];
    }
    public void set(int index,T element){
        arr[index]=element;
    }

    public void clear(){
        for (int i = 0; i <len ; i++) {
            arr[i]=null;
        }
        len=0;
    }

    public void add(T element){
        // check if resizing needed
        if(len+1>=capacity){
            capacity*=2;
            T[] new_arr = (T[]) new Object[capacity];
            for (int i = 0; i <len ; i++) {
                new_arr[i]=arr[i];
            }
            arr=new_arr;
        }
        arr[len++]=element;
    }

    public void remove(int index){
        if(index>=this.len || index<0)throw new IndexOutOfBoundsException();
        for (int i = index+1; i <len ; i++) {
            arr[i-1]=arr[i];
        }
        len--;
    }

    public boolean remove(T element){
        for (int i = 0; i <len ; i++) {
            if(arr[i].equals(element)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element){
        for (int i = 0; i <len ; i++) {
            if(arr[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T element){
        for (int i = 0; i <len ; i++) {
            if(arr[i].equals(element)){
                return true;
            }
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
                return arr[index];
            }
        };
    }

    @Override
    public String toString() {
        String str="";
        for (int i = 0; i <len ; i++) {
            str+=arr[i]+" ";
        }
        return str;
    }
}
