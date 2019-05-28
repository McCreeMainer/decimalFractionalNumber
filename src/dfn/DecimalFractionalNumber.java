package dfn;

import java.util.Locale;

public final class DecimalFractionalNumber {

    private String number;
    private int count;
    private int pointPosition;

    public DecimalFractionalNumber(int input, int c) {
        count = c;
        number = "" + input;
        pointPosition = number.length();
    }

    public DecimalFractionalNumber(long input, int c) {
        count = c;
        number = "" + input;
        pointPosition = number.length();
    }

    public DecimalFractionalNumber(float input, int c) {
        count = c;
        number = String.format("%." + c + "f", input, Locale.ROOT);
        if (Float.parseFloat(number) != input) throw new IllegalArgumentException();
        roundNulls(number);
    }

    public DecimalFractionalNumber(double input, int c) {
        count = c;
        number = String.format("%." + c + "f", input, Locale.ROOT);
        if (Double.parseDouble(number) != input) throw new IllegalArgumentException();
        roundNulls(number);
    }

    public DecimalFractionalNumber(String input, int c) {
        count = c;
        if (input.contains(".")) {
            pointPosition = input.indexOf(".");
            number = input.substring(0, pointPosition) + input.substring(pointPosition + 1);
        }
        else {
            number = input;
            pointPosition = number.length();
        }
    }

    private void roundNulls(String a) {
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) == '.') {
            pointPosition = i;
            number = a.substring(0, pointPosition) + a.substring(pointPosition + 1);
            break;
        }
        while (number.length() - 1 >= pointPosition && number.charAt(number.length() - 1) == '0')
            number = number.substring(0, number.length() - 1);
        if (number.length() > count) throw new IllegalArgumentException();
    }

    private DecimalFractionalNumber plusminus(DecimalFractionalNumber oth, boolean sign) {
        String aBefore = number.substring(0, pointPosition);
        String bBefore = oth.number.substring(0, oth.pointPosition);
        String aAfter = number.substring(pointPosition);
        String bAfter = oth.number.substring(oth.pointPosition);
        int point = Math.max(aBefore.length(), bBefore.length());
        String [] arr = new String[Math.max(aBefore.length(), bBefore.length())
                + Math.max(aAfter.length(), bAfter.length()) + 1];
        int nxt = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int a = 0;
            int b = 0;
            int result = 0;
            if (i == point) {
                arr[i] = ".";
                continue;
            }
            else if (i > point) {
                if (point + aAfter.length() < i) a = 0;
                else a = charToInt(aAfter.charAt(i - point - 1));
                if (point + bAfter.length() < i) b = 0;
                else b = charToInt(bAfter.charAt(i - point - 1));
            }
            else {
                if (point - aBefore.length() > i) a = 0;
                else a = charToInt(aBefore.charAt(i - point + aBefore.length()));
                if (point - bBefore.length() > i) b = 0;
                else b = charToInt(bBefore.charAt(i - point + bBefore.length()));
            }
            if (sign) {
                result = (a + 10 - b) % 10;
                arr[i] = String.valueOf((result + 10 - nxt) % 10);
                if (a < b || result < nxt) nxt = 1;
                else nxt = 0;
            }
            else {
                result = a + b;
                arr[i] = String.valueOf((result + nxt) % 10);
                nxt = (result + nxt) / 10;
            }
        }
        StringBuilder num = new StringBuilder();
        for (String s : arr) num.append(s);
        return new DecimalFractionalNumber(num.toString(), Math.max(count, oth.count));
    }

    public DecimalFractionalNumber plus(DecimalFractionalNumber other) {
        return plusminus(other, false);
    }

    public DecimalFractionalNumber minus(DecimalFractionalNumber other) {
        return plusminus(other, true);
    }

    public DecimalFractionalNumber multiplication(DecimalFractionalNumber other) {
        String[] arr = new String[number.length() + other.number.length()];
        for (int i = 0; i < number.length(); i++) {
            int nxt = 0;
            for (int j = 0; j < other.number.length(); j++) {
                int ar = 0;
                if (arr[arr.length - i - j - 1] != null) ar = Integer.parseInt(arr[arr.length - i - j - 1]);
                int result = (number.charAt(number.length() - i - 1) - 48)
                                * (other.number.charAt(other.number.length() - j - 1) - 48) + nxt + ar;
                arr[arr.length - i - j - 1] = String.valueOf(result % 10);
                nxt = result / 10;
            }
            int j = other.number.length();
            while (nxt != 0) {
                int ar = 0;
                if (arr[arr.length - i - j - 1] != null) ar = Integer.parseInt(arr[arr.length - i - j - 1]);
                arr[arr.length - i - j - 1] = String.valueOf(nxt % 10 + ar);
                nxt /= 10;
                j++;
            }
        }
        int j = 0;
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                j++;
                continue;
            }
            if (i == arr.length - (number.length() - pointPosition) - (other.number.length() - other.pointPosition))
                num.append(".");
            if (i - j >= Math.min(other.count, count) - 1) {
                int nxt = 0;
                for (int l = arr.length - 1; l > i; l--) {
                    if (Integer.parseInt(arr[l]) + nxt >= 5) nxt = 1;
                    else nxt = 0;
                }
                num.append(Integer.parseInt(arr[i]) + nxt);
                break;
            }
            num.append(arr[i]);
        }
        return new DecimalFractionalNumber(num.toString(), Math.min(count, other.count));
    }

    public DecimalFractionalNumber roundOff(int scale) {
        if (pointPosition > scale) throw new IllegalArgumentException();
        int a = number.length();
        if (scale < number.length()) a = scale;
        return new DecimalFractionalNumber(number.substring(0, pointPosition) + "."
                + number.substring(pointPosition, a), scale);
    }

    private int charToInt(char a) {
        return (int) a - 48;
    }

    public int toInt() {
        int f = Integer.parseInt(number.substring(0, pointPosition));
        return f;
    }

    public long toLong() {
        long f = Long.parseLong(number.substring(0, pointPosition));
        return f;
    }

    public float toFloat() {
        float f = Float.parseFloat(number.substring(0, pointPosition) + "." + number.substring(pointPosition));
        return f;
    }

    public double toDouble() {
        double f = Double.parseDouble(number.substring(0, pointPosition) + "." + number.substring(pointPosition));
        return f;
    }

    @Override
    public String toString() {
        String result = number.substring(0, pointPosition);
        if (pointPosition != number.length()) result += "." + number.substring(pointPosition);
        return result;
    }

    @Override
      public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof DecimalFractionalNumber) {
            DecimalFractionalNumber other = (DecimalFractionalNumber) obj;
            return number.equals(other.number) && count == other.count && pointPosition == other.pointPosition;
        }
        return false;
    }
}