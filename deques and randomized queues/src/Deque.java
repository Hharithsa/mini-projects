import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Point first;
    private Point last;
    private int size;

    public Deque() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Point point = new Point(item, null, this.first);
        if (size() == 0) {
            this.last = point;
        } else {
            this.first.left = point;
        }
        this.first = point;
        size++;

    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Point point = new Point(item, this.last, null);
        if (size() == 0) {
            this.first = point;
        } else {
            this.last.right = point;
        }
        this.last = point;
        size++;

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Point temp = this.first;
        this.first = this.first.right;
        this.first.left = null;
        size--;
        return temp.getItem();
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Point temp = this.last;
        this.last = this.last.left;
        this.last.right = null;
        size--;
        return temp.getItem();
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Point current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item temp = current.item;
            current = current.right;
            return temp;
        }
    }

    private class Point {
        private Point right;
        private Point left;
        private Item item;

        public Point(Item item, Point left, Point right) {
            this.right = right;
            this.left = left;
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }

    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        StdOut.println(dq.isEmpty());
        dq.addLast(7);
        dq.addFirst(1);
        dq.addFirst(3);
        dq.addLast(8);
        for (int i : dq) {
            StdOut.println(i);
        }

        dq.removeFirst();
        dq.removeLast();
        dq.removeLast();
        StdOut.println();
        for (int i : dq) {
            StdOut.println(i);
        }
    }
}


