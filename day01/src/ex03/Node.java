package ex03;

public class Node {
    Transaction item;
    Node next;
    Node prev;

    Node(Node prev, Transaction element, Node next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }

    public Node(Node prev, Transaction element) {
        this.item = element;
        this.prev = prev;
        this.next = null;
    }

    public Node(Transaction item) {
        this.item = item;
        this.prev = null;
        this.next = null;
    }

}