package dfn;

public final class decimalFractionalNumber<E> {

    private final double number;
    private final int count;

    public decimalFractionalNumber(E input, int c) {
        count = c;
        double i = Math.pow(10.0, count);
        if (input instanceof String) number = Double.parseDouble((String)input);
        else if (Integer.class.isInstance(input)) {
            int a = (Integer) input;
            number = a;
        }
        else if (Long.class.isInstance(input)) {
            long a = (Long) input;
            number = a;
        }
        else if (Float.class.isInstance(input)) {
            float a = (Float) input;
            number = Math.round((double)a * i) / i;
        }
        else if (Double.class.isInstance(input)){
            double a = (Double)input;
            number = Math.round(a * i) / i;
        }
        else throw new IllegalArgumentException("wrong input type");
    }

    public decimalFractionalNumber plus(decimalFractionalNumber other) {
        return new decimalFractionalNumber(number + other.number, Math.max(count, other.count));
    }

    public decimalFractionalNumber minus(decimalFractionalNumber other) {
        return new decimalFractionalNumber(number - other.number, Math.max(count, other.count));
    }

    public decimalFractionalNumber multiplication(decimalFractionalNumber other) {
        long result = 0;
        long a = Math.round(number * Math.pow(10.0, count));
        long b = Math.round(other.number * Math.pow(10, other.count));
        while (b != 0) {
            result += b % 10 * a;
            b /= 10;
            a *= 10;
        }
        return new decimalFractionalNumber(result * Math.pow(0.1, count + other.count), Math.max(count, other.count));
    }

    public decimalFractionalNumber roundOff(int scale) {
        double i = Math.pow(10.0, scale);
        return new decimalFractionalNumber(Math.floor(number * i) / i, scale);
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
        return (float) number;
    }

    public double toDouble() {
        return number;
    }

    @Override
    public String toString() {
        return "" + number;
    }

    @Override
      public boolean equals(Object obj) {
        if (this == obj) return true;
        if (decimalFractionalNumber.class.isInstance(obj)) {
            decimalFractionalNumber other = (decimalFractionalNumber) obj;
            return (number == other.number && count == other.count);
        }
        return false;
    }
}