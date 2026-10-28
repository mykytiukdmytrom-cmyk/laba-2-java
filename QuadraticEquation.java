import java.util.Locale;

/** Квадратне рівняння ax^2 + bx + c = 0. */
public class QuadraticEquation {

    // статичні поля
    public static final double EPS = 1e-9;   // похибка при порівнянні з нулем
    private static int count;                // скільки рівнянь створено

    // поля об'єкта
    private final double a;
    private final double b;
    private final double c;

    // ініціалізатор: виконується перед кожним конструктором
    {
        count++;
    }

    //конструктори

    /** Рівняння x^2 - 1 = 0 (за замовчуванням). */
    public QuadraticEquation() {
        this(1, 0, -1);
    }

    public QuadraticEquation(double a, double b, double c) {
        if (Math.abs(a) < EPS) {
            throw new IllegalArgumentException("Коефіцієнт a не може дорівнювати 0");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /** Рівняння з рядка виду "1 -3 2". */
    public QuadraticEquation(String text) {
        this(parse(text, 0), parse(text, 1), parse(text, 2));
    }

    /** Конструктор копіювання. */
    public QuadraticEquation(QuadraticEquation other) {
        this(other.a, other.b, other.c);
    }

    private static double parse(String text, int index) {
        return Double.parseDouble(text.trim().split("\\s+")[index].replace(',', '.'));
    }

    //статичні методи

    public static int getCount() {
        return count;
    }

    /** Рівняння з заданими коренями x1 і x2: (x - x1)(x - x2) = 0. */
    public static QuadraticEquation fromRoots(double x1, double x2) {
        return new QuadraticEquation(1, -(x1 + x2), x1 * x2);
    }

    //основні методи

    public double discriminant() {
        return b * b - 4 * a * c;
    }

    /** Кількість коренів: 0, 1 або 2. */
    public int rootsCount() {
        double d = discriminant();
        if (Math.abs(d) < EPS) return 1;
        return d > 0 ? 2 : 0;
    }

    /** Масив з одного або двох коренів, або null, якщо коренів немає. */
    public double[] roots() {
        double d = discriminant();
        switch (rootsCount()) {
            case 1:
                return new double[]{-b / (2 * a)};
            case 2:
                double sqrt = Math.sqrt(d);
                return new double[]{(-b - sqrt) / (2 * a), (-b + sqrt) / (2 * a)};
            default:
                return null;
        }
    }

    //toString / equals

    @Override
    public String toString() {
        return String.format(Locale.US, "%sx^2 %s %sx %s %s = 0",
                num(a), sign(b), num(Math.abs(b)), sign(c), num(Math.abs(c)));
    }

    /** Перевантажений toString: рівняння разом з коренями. */
    public String toString(boolean withRoots) {
        if (!withRoots) return toString();
        double[] r = roots();
        String result;
        if (r == null) {
            result = "коренів немає";
        } else if (r.length == 1) {
            result = String.format(Locale.US, "x = %.4f", r[0]);
        } else {
            result = String.format(Locale.US, "x1 = %.4f, x2 = %.4f", r[0], r[1]);
        }
        return toString() + "  ->  " + result;
    }

    private static String sign(double v) {
        return v < 0 ? "-" : "+";
    }

    private static String num(double v) {
        return v == Math.rint(v) ? String.valueOf((long) v) : String.valueOf(v);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof QuadraticEquation && equals((QuadraticEquation) obj);
    }

    /** Перевантажений equals для QuadraticEquation. */
    public boolean equals(QuadraticEquation other) {
        return other != null
                && Math.abs(a - other.a) < EPS
                && Math.abs(b - other.b) < EPS
                && Math.abs(c - other.c) < EPS;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Math.round(a * 1e6), Math.round(b * 1e6), Math.round(c * 1e6));
    }
}
