package ex03;

public class TransactionsLinkedList implements TransactionsList {
    private Node head = null;
    private Node tail = null;
    private Integer length = 0;

    @Override
    public void addTransaction(Transaction transaction) {
        if (head == null) {
            head = new Node(null, transaction, null);
        } else if (tail == null) {
            tail = new Node(head, transaction, null);
            head.next = tail;
        } else {
            tail.next = new Node(tail, transaction, null);
            tail = tail.next;
        }
        length++;
    }

    @Override
    public void removeTransactionById(String id) {
        if (head.item.getIdentifier() == id) {
            head = head.next;
            head.prev = null;
            length--;
        } else if (tail.item.getIdentifier() == id) {
            tail = tail.prev;
            tail.next = null;
            length--;
        } else {
            Node tmp = head;

            while (tmp != tail) {
                if (tmp.item.getIdentifier() == id) {
                    Node prev = tmp.prev;
                    Node next = tmp.next;
                    prev.next = next;
                    next.prev = prev;
                   break;
                }
                tmp = tmp.next;
            }
            if (tmp == tail) {
                throw new TransactionNotFoundException("Transaction with id " + id + " not found");
            }
            length--;
        }
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[length];
        Node tmp = head;

        for (int i = 0; i < length; i++) {
            array[i] = tmp.item;
            tmp = tmp.next;
        }
        return  array;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        return "TransactionsLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", length=" + length +
                '}';
    }
}
