public class Main {
    public static void main(String[] args) {
        // різні способи створення
        Fraction a = new Fraction();                    // 0.0000
        Fraction b = new Fraction(5, (short) 2500);     // 5.2500
        Fraction c = new Fraction(3.14159);             // 3.1416
        Fraction d = new Fraction("-2,5");              // -2.5000
        Fraction e = new Fraction(b);                   // копія b
        System.out.println("");
        System.out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + ", e = " + e);

        // арифметика
        System.out.println(b + " + " + c + " = " + b.add(c));
        System.out.println(b + " - " + c + " = " + b.subtract(c));
        System.out.println(d + " + " + b + " = " + d.add(b));
        System.out.println(b + " + 0.75 = " + b.add(0.75));
        System.out.println(b + " - 10 = " + b.subtract(10));

        // порівняння
        System.out.println("b.compareTo(c) = " + b.compareTo(c));
        System.out.println("b > c ? " + b.greaterThan(c));
        System.out.println("d < a ? " + d.lessThan(a));
        System.out.println("max(b, c) = " + Fraction.max(b, c));
        System.out.println("min(b, c) = " + Fraction.min(b, c));

        // equals і toString
        System.out.println("b.equals(e) = " + b.equals(e));
        System.out.println("b.equals(c) = " + b.equals((Object) c));
        System.out.println("c.toString(2) = " + c.toString(2));

        // статичний метод
        System.out.println("Створено об'єктів: " + Fraction.getCount());
    }
}