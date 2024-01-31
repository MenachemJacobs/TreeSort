import java.util.Collection;
import java.util.Iterator;

public class OldTreeSort<E extends Comparable<E>> {

    /**
     * A private inner class representing a node in the linked list.
     * Each node contains data and a reference to the next node in the list.
     */
    private class Node{
        //The information contained in a given node
        private E data;
        //A reference to the Node to the conceptual left, which is to say, lesser values.
        private Node left;
        //A reference to the Node to the conceptual right, which is to say, greater values
        private Node right;

        Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node head = null;
    private int size = 0;
    boolean overflowFlag = false;

    public int size(){
        return !overflowFlag ? size : Integer.MAX_VALUE;
    }

    public void clear(){
        size = 0;
        overflowFlag = false;
        head = null;
    }

    public boolean add(E toAdd) {
        Node newNode = new Node(toAdd);

        if (head == null) {
            head = newNode;
            incrementSize();
            return true;
        } else
            return add(head, newNode);
    }

    private boolean add(Node Current, Node toAdd) {
        int comparisonResult = Current.data.compareTo(toAdd.data);

        if (comparisonResult > 0) {
            if (Current.left != null)
                return add(Current.left, toAdd);
            else {
                Current.left = toAdd;
                incrementSize();
                return true;
            }
        }
        else if (comparisonResult < 0) {
            if (Current.right != null)
                return add(Current.right, toAdd);
            else {
                Current.right = toAdd;
                incrementSize();
                return true;
            }
        }

        return false;
    }

    public void addAll(Collection<E> o){
        for (E el: o) {
            add(el);
        }
    }

    private class TreeSortIterator implements Iterator<E>{
        private Node cursor = head;
        int depth = 0;
        boolean hasNext = true;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return cursor != null && hasNext;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            return null;
        }
    }

    public Iterator<E> iterator(){
        return new TreeSortIterator();
    }

    private void incrementSize(){
        if(size + 1 == Integer.MAX_VALUE)
            overflowFlag = true;
        size++;
    }
}
