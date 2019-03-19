package dfn;

public final class decimalFractionalNumber<E> {

    private static double number;
    private static int count;

    public decimalFractionalNumber(E input, int c) {
        count = c;
        int i = (int) Math.pow(10.0, count);
        if (input instanceof String) number = Double.parseDouble((String)input);
        else if (Integer.class.isInstance(input) || Long.class.isInstance(input) || Float.class.isInstance(input) ||
                Double.class.isInstance(input)) number = ((double) Math.floor((double) ((Number)input) * i)) / i;
        else throw new IllegalArgumentException("wrong input type");
    }

    public decimalFractionalNumber plus(decimalFractionalNumber other) {
        return new decimalFractionalNumber(number + other.number, Math.max(count, other.count));
    }

    public decimalFractionalNumber minus(decimalFractionalNumber other) {
        return new decimalFractionalNumber(number - other.number, Math.max(count, other.count));
    }

    public decimalFractionalNumber multiplication(decimalFractionalNumber other) {
        int bfr;
        int result = 0;
        int i = 1;
        if (number * other.number < 0) bfr = -1;
        else bfr = 1;
        int a = (int) Math.floor(number * (int) Math.pow(10, count));
        while (a % 10 != 0) {
            int b = (int) Math.floor(other.number * (int) Math.pow(10, other.count));
            int j = 1;
            int dec = a % 10;
            while (b % 10 != 0) {
                result += dec * (b % 10) * i * j;
                b /= 10;
                j *= 10;
            }
            a /= 10;
            i *= 10;
        }
        return new decimalFractionalNumber(bfr * result * Math.pow(0.1, count + other.count), count + other.count);
    }

    public decimalFractionalNumber roundOff(int scale) {
        return new decimalFractionalNumber(number, scale);
    }

    public int toInt() {
        if (number > Integer.MAX_VALUE || number < Integer.MIN_VALUE)
            throw new IllegalArgumentException("number is not in range of int");
        return (int) number;
    }

    public long toLong() {
        if (number > Long.MAX_VALUE || number < Long.MIN_VALUE)
            throw new IllegalArgumentException("number is not in range of long");
        return (long) number;
    }

    public float toFloat() {
        if (number > Float.MAX_VALUE || number < Float.MIN_VALUE)
            throw new IllegalArgumentException("number is not in range of float");
        int i = (int) Math.pow(10.0, count);
        return ((float) number * i);
    }

    public double toDouble() {
        return number;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}