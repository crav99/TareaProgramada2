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
public class PriorityQueue<T> {
    
    private class Node<T> {
        
        private Object priority;
        private LQueue<T> queue;
        private Node<T> next;
        
        public Node() {
            this.priority = null;
            this.queue = new LQueue();
            this.next = null;
        }
        
        public Node(Object priority) {
            this.priority = priority;
            this.queue = new LQueue();
            this.next = null;
        }
        
        public Node(Object priority, Node next) {
            this.priority = priority;
            this.queue = new LQueue();
            this.next = next;
        }

        /**
         * @return the priority
         */
        public Object getPriority() {
            return priority;
        }

        /**
         * @param priority the priority to set
         */
        public void setPriority(Object priority) {
            this.priority = priority;
        }

        /**
         * @return the queue
         */
        public LQueue<T> getQueue() {
            return queue;
        }

        /**
         * @param queue the queue to set
         */
        public void setQueue(LQueue<T> queue) {
            this.queue = queue;
        }

        /**
         * @return the next
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }
        
    }
    
    private Node<T> head;
    private Node<T> current;
    private Node<T> tail;
    private int position;
    private int size;
    
    public PriorityQueue() {
        this.head = null;
        this.current = this.head;
        this.tail = this.head;
        this.size = 0;
        this.position = -1;
    }
    
    public PriorityQueue (PriorityQueue<T> elemento) {
        this.head = elemento.head;
        this.current = elemento.head;
        this.tail = elemento.head;
        this.size = elemento.size;
        this.position = elemento.position;
    }
    
    public void insert(Object priority) {
        Node<T> newNode = new Node(priority);
        if(this.head == null) {
            this.head = this.current = this.tail = newNode;
            this.position = 0;
        }else {
            this.current.setNext(head);
            if(this.current == this.tail) {
                this.tail = this.tail.getNext();
            }
        }
        this.size ++;
    }
    
    public void append(Object priority) {
        Node <T> newNode = new Node(priority);
        if(this.head == null) {
            this.head = this.current = this.tail = newNode;
            this.position = 0;
        }else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size ++;
    }
    
    public void remove() {
        if (this.head == null){
            System.out.println("Cola vacía, no se puede remover ningún elemento");
            return;
        }else if((this.head == this.current) && (this.head == this.tail)) {
            this.head = this.current = this.tail = null;
            this.position = -1;
        }else {
            Node<T> temp = head;
            while (temp.getNext() != this.current) {
                temp = temp.getNext();
            }

            temp.setNext(this.current.getNext());

            if (this.current == this.tail) {
                this.current = this.tail = temp;
                this.position--;
            }else {
                this.current = this.current.getNext();
            }
        }
        this.size--;
    }
    
    public void clear() {
        this.head = this.tail = this.current = null;
        this.size = 0;
        this.position = -1;
    }
    
    public Object getElement(){
        return this.current.getPriority();
    }
    
    public int getSize() {
        return this.size;
    }
    
    public boolean next() {
        if (this.current == this.tail) {
            return false;
        }
        this.current = this.current.getNext();
        this.position++;
        return true;
    }
    
    public boolean previous() {
        if (this.current == this.head) {
            return false;
        }
        Node temp = head;
        this.position = 0;
        while (temp.getNext() != this.current) {
            temp = temp.getNext();
            this.position++;
        }
        this.current = temp;
        return true;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public void goToStart(){
        this.current = this.head;
        this.position = 0;
    }
    
    public void goToEnd(){
        this.current = this.tail;
        this.position = this.size - 1;
    }
    
    public void goToPos(int pos) {
        if (pos < 0 || pos >= this.size) {
            System.out.println("Posición inválida");
            return;
        }
        if (pos > this.position) {
            while (pos > this.position) {
                this.next();
            }
        } else if (pos < this.position) {
            while (pos < this.position) {
                this.previous();
            }
        }
    }
    
    @Override
    public String toString() {
        Node currentNode = this.head.getNext();

        StringBuffer result = new StringBuffer();

        for (int i = 0; currentNode != null; i++)
        {
            if (i > 0)
            {
                result.append(",");
            }
            Object element = currentNode.getPriority();

            result.append(element == null ? "" : element);
            currentNode = currentNode.getNext();
        }
        return result.toString();
    }
    
}
