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

    /**
     * If user does not provide a capacity then default capacity=16
     */
    public Array(){
        this(16);
    }

    public Array(int capacity){
        if(capacity<0)throw new IllegalArgumentException("Illegal capacity "+capacity);
        this.capacity=capacity;
        arr = (T[]) new Object[capacity];
    }

    /**
     * Returns size of the dynamic array
     * @return size of the dynamic array or the number of elements present
     */
    public int size(){
        return this.len;
    }

    /**
     * Returns true if array is empty - else returns false
     * @return true/false
     */
    public boolean isEmpty(){
        return this.len==0;
    }

    /**
     * Get element at specified index
     * @param index
     * @return element at specified index
     */
    public T get(int index){
        if(arr[index]==null)throw new NullPointerException();
        return arr[index];
    }

    /**
     * replaces the element at specified index by the given element
     * @param index the index at which element needs to be replaced
     * @param element the new element
     */
    public void set(int index,T element){
        if(index>=this.len)throw new IndexOutOfBoundsException();
        arr[index]=element;
    }

    /**
     * removes all the elements from the array
     */
    public void clear(){
        for (int i = 0; i <len ; i++) {
            arr[i]=null;
        }
        len=0;
    }

    /**
     * Append an element to the array
     * @param element the element to be appended
     */
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

    /**
     * remove element at index
     * @param index the index to be removed
     */
    public void remove(int index){
        if(index>=this.len || index<0)throw new IndexOutOfBoundsException();
        for (int i = index+1; i <len ; i++) {
            arr[i-1]=arr[i];
        }
        len--;
    }

    /**
     * Remove element if exists
     * @param element the element to be removed
     * @return true if element is found and removed else returns false if element not found
     */
    public boolean remove(T element){
        for (int i = 0; i <len ; i++) {
            if(arr[i].equals(element)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * returns index of an element if exists
     * @param element element whose index needs to be found
     * @return index of element if found else returns -1
     */
    public int indexOf(T element){
        for (int i = 0; i <len ; i++) {
            if(arr[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    /**
     * returns if element exists or not
     * @param element the element to be searched for
     * @return true if element exists else returns false
     */
    public boolean contains(T element){
        for (int i = 0; i <len ; i++) {
            if(arr[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    /**
     * Iterator for fast iteration of array elements
     * @return iterator object for the dynamic array
     */
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
                return arr[index++];
            }
        };
    }

    /**
     * Represents array as a string of elements seperated by space
     * @return string representation of the array
     */
    @Override
    public String toString() {
        String str="";
        for (int i = 0; i <len ; i++) {
            str+=arr[i]+" ";
        }
        return str;
    }
}
