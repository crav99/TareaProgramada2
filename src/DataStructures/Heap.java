/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author allanvz
 * @param <T>
 */
public class Heap<T> {
    
    public class Node<T> {
        
        private int priority;
        private T element;

        public Node() {
            this.priority = -1;
            this.element = null;
        }

        public Node(int priority, T element) {
            this.priority = priority;
            this.element = element;
        }

        /**
         * @return the priority
         */
        public int getPriority() {
            return priority;
        }

        /**
         * @param priority the priority to set
         */
        public void setPriority(int priority) {
            this.priority = priority;
        }

        /**
         * @return the element
         */
        public T getElement() {
            return element;
        }

        /**
         * @param element the element to set
         */
        public void setElement(T element) {
            this.element = element;
        }
        
    }
    
    private Node<T>[] heapArray;
    
    public Heap() {
        this.heapArray = null;
    }
    
    public Heap(Heap<T> elemento) {
        this.heapArray = elemento.heapArray;
    }
    
    public boolean isLeaf(int pos) {
        return ((2 * pos) + 1) < heapArray.length;
    }
    
    public int getLeftChild(int pos) {
        return (2 * pos) + 1;
    }
    
    public int getRightChild(int pos) {
        return (2 * pos) + 2;
    }
    
    public int parent(int pos) {
        return (pos - 1) / 2;
    }
    
    public void insert(T element, int priority) {
        Node<T> newNode = new Node(priority, element);
        Node<T>[] heapTemp = this.heapArray;
        this.heapArray = new Node[this.heapArray.length + 1];
        int x = 0;
        for(; x < heapTemp.length; x++) {
            this.heapArray[x] = heapTemp[x];
        }
        this.heapArray[x] = newNode;
        siftUp(this.heapArray.length - 1);
    }
    
    public void siftDown(int pos) {
        int leftChild = 2*pos + 1;
        int rightChild = 2*pos + 2;
        int largerChild;
        if(rightChild < this.heapArray.length && this.heapArray[rightChild].getPriority() > this.heapArray[leftChild].getPriority()) {
                largerChild = rightChild;
        } else {
                largerChild = leftChild;
        }
        if(largerChild < this.heapArray.length && this.heapArray[largerChild].getPriority() > this.heapArray[pos].getPriority()) {
            Node<T> temp = this.heapArray[pos];
            this.heapArray[pos] = this.heapArray[largerChild];
            this.heapArray[largerChild] = temp;
            siftDown(largerChild);
        }
    }
    
    public void siftUp(int pos) {
        int parent = (int)Math.floor((pos-1)/2);
        if(pos > 0 && this.heapArray[parent].getPriority() < this.heapArray[pos].getPriority()) {
            Node<T> temp = this.heapArray[pos];
            this.heapArray[pos] = this.heapArray[parent];
            this.heapArray[parent] = temp;
            siftUp(parent);
        }
    }
    
    public Node<T> removeRoot() {
        Node<T>[] arrayTemp = this.heapArray;
        arrayTemp[0] = arrayTemp[arrayTemp.length - 1];
        this.heapArray = new Node[this.heapArray.length - 1];
        int x = 0;
        for(; x < this.heapArray.length; x ++) {
            this.heapArray[x] = arrayTemp[x];
        }
        siftDown(0);
        return arrayTemp[x];
    }
    
    public Node<T>[] heapSort() {
        Node<T>[] heap = this.heapArray;
        for(int i = this.heapArray.length - 1; i >= 1; i--) {
            Node<T> max = removeRoot();
            heap[i] = max;
        }
        return heap;
    }
    
}
