import java.util.LinkedList;

public class CustomStack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T element) {
        list.addFirst(element);
    }

    public T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    public T peek() {
        if (list.isEmpty()) {
            return null;
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
