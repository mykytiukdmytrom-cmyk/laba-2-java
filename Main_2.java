import java.util.Arrays;

public class Main_2 {
    public static void main(String[] args) {
        // різні способи створення
        QuadraticEquation q1 = new QuadraticEquation();                 // x^2 - 1 = 0
        QuadraticEquation q2 = new QuadraticEquation(1, -3, 2);         // два корені
        QuadraticEquation q3 = new QuadraticEquation("1 -2 1");         // один корінь
        QuadraticEquation q4 = new QuadraticEquation(1, 2, 5);          // коренів немає
        QuadraticEquation q5 = new QuadraticEquation(q2);               // копія q2
        QuadraticEquation q6 = QuadraticEquation.fromRoots(4, -1);      // за коренями

        QuadraticEquation[] all = {q1, q2, q3, q4, q6};
        for (QuadraticEquation q : all) {
            System.out.println(q);
            System.out.println("  дискримінант = " + q.discriminant());
            System.out.println("  кількість коренів = " + q.rootsCount());
            System.out.println("  корені = " + Arrays.toString(q.roots()));   // null, якщо коренів немає
        }

        // перевантажений toString
        System.out.println("\n" + q2.toString(true));
        System.out.println(q4.toString(true));

        // equals
        System.out.println("\nq2.equals(q5) = " + q2.equals(q5));
        System.out.println("q2.equals(q3) = " + q2.equals((Object) q3));
        System.out.println("q2.equals(null) = " + q2.equals((Object) null));

        // статичний метод
        System.out.println("\nСтворено рівнянь: " + QuadraticEquation.getCount());

        // обробка помилки
        try {
            new QuadraticEquation(0, 2, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
