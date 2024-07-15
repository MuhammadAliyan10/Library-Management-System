public class CustomLinkedList<T> {
    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public T search(String title) {
        Node<T> current = head;
        while (current != null) {
            if (((LibraryItem) current.data).getTitle().equalsIgnoreCase(title)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void sort() {
        if (head == null || head.next == null) {
            return;
        }
        head = mergeSort(head);
    }

    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;

        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    private Node<T> sortedMerge(Node<T> a, Node<T> b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (((LibraryItem) a.data).getTitle().compareToIgnoreCase(((LibraryItem) b.data).getTitle()) <= 0) {
            a.next = sortedMerge(a.next, b);
            return a;
        } else {
            b.next = sortedMerge(a, b.next);
            return b;
        }
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }
        Node<T> slow = head, fast = head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
    }
}
