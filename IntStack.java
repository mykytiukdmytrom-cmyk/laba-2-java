import java.util.NoSuchElementException;

/** Динамічний стек цілих чисел на основі зв'язного списку. */
public class IntStack {

    /** Вузол списку. */
    private static class Node {
        final int value;
        final Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    // статичне поле
    private static int count;      // скільки стеків створено

    // поля об'єкта
    private Node top;              // вершина стека
    private int size;              // кількість елементів

    // ініціалізатор: виконується перед кожним конструктором
    {
        count++;
    }

    //конструктори

    /** Порожній стек. */
    public IntStack() {
    }

    /** Стек із початкових значень (останнє значення стає вершиною). */
    public IntStack(int... items) {
        for (int x : items) {
            push(x);
        }
    }

    /** Конструктор копіювання. */
    public IntStack(IntStack other) {
        int[] items = other.toArray();           // від вершини до дна
        for (int i = items.length - 1; i >= 0; i--) {
            push(items[i]);
        }
    }

    //статичні методи

    public static int getCount() {
        return count;
    }

    /** Створює стек зі значень від from до to включно (to буде на вершині). */
    public static IntStack range(int from, int to) {
        IntStack s = new IntStack();
        for (int i = from; i <= to; i++) {
            s.push(i);
        }
        return s;
    }

    //основні операції

    /** Додати елемент на вершину. */
    public void push(int value) {
        top = new Node(value, top);
        size++;
    }

    /** Видалити елемент з вершини й повернути його. */
    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Стек порожній");
        }
        int value = top.value;
        top = top.next;
        size--;
        return value;
    }

    /** Переглянути вершину без видалення. */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Стек порожній");
        }
        return top.value;
    }

    /** Пошук: позиція елемента від вершини (1 — вершина) або -1, якщо не знайдено. */
    public int search(int value) {
        int position = 1;
        for (Node n = top; n != null; n = n.next, position++) {
            if (n.value == value) {
                return position;
            }
        }
        return -1;
    }

    public boolean contains(int value) {
        return search(value) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    /** Елементи від вершини до дна. */
    public int[] toArray() {
        int[] result = new int[size];
        int i = 0;
        for (Node n = top; n != null; n = n.next) {
            result[i++] = n.value;
        }
        return result;
    }

    //toString / equals

    /** Вершина — ліворуч: [3, 2, 1]. */
    @Override
    public String toString() {
        return toString(", ");
    }

    /** Перевантажений toString із власним роздільником. */
    public String toString(String separator) {
        StringBuilder sb = new StringBuilder("[");
        for (Node n = top; n != null; n = n.next) {
            sb.append(n.value);
            if (n.next != null) {
                sb.append(separator);
            }
        }
        return sb.append("]").toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntStack && equals((IntStack) obj);
    }

    /** Перевантажений equals: однакові елементи в однаковому порядку. */
    public boolean equals(IntStack other) {
        if (other == null || size != other.size) {
            return false;
        }
        Node a = top;
        Node b = other.top;
        while (a != null) {
            if (a.value != b.value) {
                return false;
            }
            a = a.next;
            b = b.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(toArray());
    }
}
