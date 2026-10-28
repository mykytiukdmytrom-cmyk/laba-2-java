import java.util.NoSuchElementException;

public class Main_3 {
    public static void main(String[] args) {
        // різні способи створення
        IntStack s1 = new IntStack();                // порожній
        IntStack s2 = new IntStack(10, 20, 30);      // вершина — 30
        IntStack s3 = new IntStack(s2);              // копія s2
        IntStack s4 = IntStack.range(1, 5);          // 1..5, вершина — 5
        System.out.println("s1 = " + s1 + ", порожній? " + s1.isEmpty());
        System.out.println("s2 = " + s2);
        System.out.println("s3 = " + s3 + " (копія s2)");
        System.out.println("s4 = " + s4);

        // додавання
        s1.push(7);
        s1.push(42);
        s1.push(-3);
        System.out.println("\nПісля push(7), push(42), push(-3): s1 = " + s1 + ", розмір = " + s1.size());
        System.out.println("peek() = " + s1.peek());

        // пошук
        System.out.println("\nПошук (позиція від вершини, 1 — вершина):");
        System.out.println("search(-3) = " + s1.search(-3));
        System.out.println("search(7) = " + s1.search(7));
        System.out.println("search(100) = " + s1.search(100));
        System.out.println("contains(42) = " + s1.contains(42));

        // вилучення
        System.out.println("\nВилучення:");
        System.out.println("pop() = " + s1.pop() + ", стек: " + s1);
        System.out.println("pop() = " + s1.pop() + ", стек: " + s1);
        System.out.println("pop() = " + s1.pop() + ", стек: " + s1);
        try {
            s1.pop();
        } catch (NoSuchElementException e) {
            System.out.println("pop() з порожнього стека: " + e.getMessage());
        }

        // перевантажений toString
        System.out.println("\ns4.toString(\" | \") = " + s4.toString(" | "));

        // equals
        System.out.println("\ns2.equals(s3) = " + s2.equals(s3));
        s3.pop();
        System.out.println("після s3.pop(): s2.equals(s3) = " + s2.equals((Object) s3));
        System.out.println("s2 не змінився: " + s2);

        // clear
        s4.clear();
        System.out.println("\nПісля clear(): s4 = " + s4 + ", порожній? " + s4.isEmpty());

        // статичний метод
        System.out.println("Створено стеків: " + IntStack.getCount());
    }
}
