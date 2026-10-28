import java.util.Locale;

/**
 * Дробове число: ціла частина (long) і дробова частина (short, 4 знаки).
 * Приклад: 3.1415 -> whole = 3, frac = 1415.
 * Для від'ємних чисел обидві частини від'ємні: -2.5 -> whole = -2, frac = -5000.
 */
public class Fraction implements Comparable<Fraction> {

    // статичні поля
    public static final int SCALE = 10_000;
    private static int count;           // скільки об'єктів створено

    // поля об'єкта
    private final long whole;
    private final short frac;

    // ініціалізатор: виконується перед кожним конструктором
    {
        count++;
    }

    //конструктори

    public Fraction() {
        this(0, (short) 0);
    }

    public Fraction(long whole, short frac) {
        if (Math.abs(frac) >= SCALE || (whole > 0 && frac < 0) || (whole < 0 && frac > 0)) {
            throw new IllegalArgumentException("Некоректна дробова частина: " + frac);
        }
        this.whole = whole;
        this.frac = frac;
    }

    public Fraction(double value) {
        this(Math.round(value * SCALE));
    }

    public Fraction(String text) {
        this(Double.parseDouble(text.replace(',', '.')));
    }

    public Fraction(Fraction other) {
        this(other.whole, other.frac);
    }

    // число у "сотих тисячних": 3.1415 -> 31415 (використовується всередині)
    private Fraction(long units) {
        this(units / SCALE, (short) (units % SCALE));
    }

    private long units() {
        return whole * SCALE + frac;
    }

    //статичні методи

    public static int getCount() {
        return count;
    }

    public static Fraction max(Fraction a, Fraction b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static Fraction min(Fraction a, Fraction b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    //арифметика

    public Fraction add(Fraction other) {
        return new Fraction(units() + other.units());
    }

    public Fraction add(double value) {
        return add(new Fraction(value));
    }

    public Fraction subtract(Fraction other) {
        return new Fraction(units() - other.units());
    }

    public Fraction subtract(double value) {
        return subtract(new Fraction(value));
    }

    //порівняння

    @Override
    public int compareTo(Fraction other) {
        return Long.compare(units(), other.units());
    }

    public boolean greaterThan(Fraction other) {
        return compareTo(other) > 0;
    }

    public boolean lessThan(Fraction other) {
        return compareTo(other) < 0;
    }

    //toString / equals

    @Override
    public String toString() {
        return toString(4);
    }

    /** Перевантажений toString: задана кількість знаків після коми. */
    public String toString(int digits) {
        return String.format(Locale.US, "%." + digits + "f", units() / (double) SCALE);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Fraction && equals((Fraction) obj);
    }

    /** Перевантажений equals для Fraction. */
    public boolean equals(Fraction other) {
        return other != null && units() == other.units();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(units());
    }
}