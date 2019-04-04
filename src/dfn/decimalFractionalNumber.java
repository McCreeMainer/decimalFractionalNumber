package dfn;

public final class decimalFractionalNumber {

    private String number;
    private int count;
    private int pointPosition;

    public decimalFractionalNumber(int input, int c) {
        count = c;
        whereIsPoint("" + input);
    }

    public decimalFractionalNumber(long input, int c) {
        count = c;
        whereIsPoint("" + input);
    }

    public decimalFractionalNumber(float input, int c) {
        count = c;
        whereIsPoint("" + input);
    }

    public decimalFractionalNumber(double input, int c) {
        count = c;
        whereIsPoint("" + input);
    }

    public decimalFractionalNumber(String input, int c) {
        count = c;
        whereIsPoint(input);
    }

    private void whereIsPoint(String a) {
        pointPosition = a.length();
        String result = a.substring(0, pointPosition);
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) == '.') {
            pointPosition = i;
            result = a.substring(0, pointPosition) + a.substring(pointPosition + 1);
            break;
        }
        if (result.length() > count) throw new IllegalArgumentException();
        number = result;
    }

    public decimalFractionalNumber plus(decimalFractionalNumber other) {
        String aBefore = number.substring(0, pointPosition);
        String bBefore = other.number.substring(0, other.pointPosition);
        String aAfter = number.substring(pointPosition);
        String bAfter = other.number.substring(other.pointPosition);
        int point = Math.max(aBefore.length(), bBefore.length());
        String [] arr =
                new String[Math.max(aBefore.length(), bBefore.length())
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
                else a = (int) aAfter.charAt(i - point - 1) - 48;
                if (point + bAfter.length() < i) b = 0;
                else b = (int) bAfter.charAt(i - point - 1) - 48;
            }
            else {
                if (point - aBefore.length() > i) a = 0;
                else a = (int) aBefore.charAt(i - point + aBefore.length()) - 48;
                if (point - bBefore.length() > i) b = 0;
                else b = (int) bBefore.charAt(i - point + bBefore.length()) - 48;
            }
            result = a + b;
            arr[i] = String.valueOf((result + nxt) % 10);
            nxt = (result + nxt) / 10;
        }
        String num = "";
        for (int i = 0; i < arr.length; i++) num += arr[i];
        return new decimalFractionalNumber(num, Math.max(count, other.count));
    }

    public decimalFractionalNumber minus(decimalFractionalNumber other) {
        String aBefore = number.substring(0, pointPosition);
        String bBefore = other.number.substring(0, other.pointPosition);
        String aAfter = number.substring(pointPosition);
        String bAfter = other.number.substring(other.pointPosition);
        int point = Math.max(aBefore.length(), bBefore.length());
        String[] arr = new String[point + Math.max(aAfter.length(), bAfter.length()) + 1];
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
                else a = (int) aAfter.charAt(i - point - 1) - 48;
                if (pointPosition + bAfter.length() < i) b = 0;
                else b = (int) bAfter.charAt(i - point - 1) - 48;
            }
            else {
                if (point - aBefore.length() > i) a = 0;
                else a = (int) aBefore.charAt(i - point + aBefore.length()) - 48;
                if (point - bBefore.length() > i) b = 0;
                else b = (int) bBefore.charAt(i - point + bBefore.length()) - 48;
            }
            result = (a + 10 - b) % 10;
            arr[i] = String.valueOf((result + 10 - nxt) % 10);
            if (a < b || result < nxt) nxt = 1;
            else nxt = 0;
        }
        String num = "";
        for (int i = 0; i < arr.length; i++) num += arr[i];
        return new decimalFractionalNumber(num, Math.max(count, other.count));
    }

    public decimalFractionalNumber multiplication(decimalFractionalNumber other) {
        String[] arr = new String[number.length() + other.number.length()];
        for (int i = 0; i < arr.length; i++) arr[i] = "";
        for (int i = 0; i < number.length(); i++) {
            int nxt = 0;
            for (int j = 0; j < other.number.length(); j++) {
                int ar = 0;
                if (arr[arr.length - i - j - 1] != "") ar = Integer.parseInt(arr[arr.length - i - j - 1]);
                int result = (number.charAt(number.length() - i - 1) - 48)
                                * (other.number.charAt(other.number.length() - j - 1) - 48) + nxt + ar;
                arr[arr.length - i - j - 1] = String.valueOf(result % 10);
                nxt = result / 10;
            }
            int j = other.number.length();
            while (nxt != 0) {
                int ar = 0;
                if (arr[arr.length - i - j - 1] != "") ar = Integer.parseInt(arr[arr.length - i - j - 1]);
                arr[arr.length - i - j - 1] = String.valueOf(nxt % 10);
                nxt /= 10;
                j++;
            }
        }
        String num = "";
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == "") s = i;
            else break;
        }
        int nxt = 0;
        int round = arr.length - 1;
        while (round > s + Math.max(count, other.count)) {
            if (Integer.parseInt(arr[round]) - 30 + nxt >= 5) nxt = 1;
            else nxt = 0;
            round--;
        }
        arr[round] = String.valueOf(Integer.parseInt(arr[round]) + nxt);
        for (int i = s + 1; i <= round; i++) {
            if (i == arr.length - (number.length() - pointPosition) - (other.number.length() - other.pointPosition))
                num += ".";
            num += arr[i];
        }
        return new decimalFractionalNumber(num, Math.max(count, other.count));
    }

    public decimalFractionalNumber roundOff(int scale) {
        if (pointPosition > scale) throw new IllegalArgumentException();
        int a = number.length();
        if (scale < number.length()) a = scale;
        return new decimalFractionalNumber(number.substring(0, pointPosition) + "."
                + number.substring(pointPosition, a), scale);
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
        if (decimalFractionalNumber.class.isInstance(obj)) {
            decimalFractionalNumber other = (decimalFractionalNumber) obj;
            return number.equals(other.number) && count == other.count && pointPosition == other.pointPosition;
        }
        return false;
    }
}