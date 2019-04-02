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
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) == '.') {
            pointPosition = i;
            break;
        }
        String result = a.substring(0, pointPosition) + a.substring(pointPosition + 1);
        if (result.length() > count) throw new IllegalArgumentException();
        number = result;
    }

    public decimalFractionalNumber plus(decimalFractionalNumber other) {
        String aBefore = number.substring(0, pointPosition);
        String bBefore = other.number.substring(0, pointPosition);
        String aAfter = number.substring(pointPosition);
        String bAfter = other.number.substring(pointPosition);
        String [] arr =
                new String[Math.max(aBefore.length(), bBefore.length())
                        + Math.max(aAfter.length(), bAfter.length()) + 1];
        int nxt = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int a = 0;
            int b = 0;
            int result = 0;
            if (i == pointPosition) {
                arr[pointPosition] = ".";
                continue;
            }
            else if (i > pointPosition) {
                if (pointPosition + aAfter.length() < i) a = 0;
                else a = (int) aAfter.charAt(i - pointPosition - 1) - 48;
                if (pointPosition + bAfter.length() < i) b = 0;
                else b = (int) bAfter.charAt(i - pointPosition - 1) - 48;
            }
            else {
                if (pointPosition - aAfter.length() > i) a = 0;
                else a = (int) aAfter.charAt(i - pointPosition + aAfter.length()) - 48;
                if (pointPosition - bAfter.length() < i) b = 0;
                else b = (int) bAfter.charAt(i - pointPosition + bAfter.length()) - 48;
            }
            result = a + b;
            arr[i] = String.valueOf(result % 10 + nxt);
            nxt = result / 10;
        }
        String num = "";
        for (int i = 0; i < arr.length; i++) num += arr[i];
        return new decimalFractionalNumber(num, arr.length - 1);
    }

    public decimalFractionalNumber minus(decimalFractionalNumber other) {
        String aBefore = number.substring(0, pointPosition);
        String bBefore = other.number.substring(0, pointPosition);
        String aAfter = number.substring(pointPosition);
        String bAfter = other.number.substring(pointPosition);
        String[] arr =
                new String[Math.max(aBefore.length(), bBefore.length())
                        + Math.max(aAfter.length(), bAfter.length()) + 1];
        int nxt = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int a = 0;
            int b = 0;
            int result = 0;
            if (i == pointPosition) {
                arr[pointPosition] = ".";
                continue;
            }
            else if (i > pointPosition) {
                if (pointPosition + aAfter.length() < i) a = 0;
                else a = (int) aAfter.charAt(i - pointPosition - 1) - 48;
                if (pointPosition + bAfter.length() < i) b = 0;
                else b = (int) bAfter.charAt(i - pointPosition - 1) - 48;
            }
            else {
                if (pointPosition - aBefore.length() > i) a = 0;
                else a = (int) aBefore.charAt(i - pointPosition + aBefore.length()) - 48;
                if (pointPosition - bBefore.length() < i) b = 0;
                else b = (int) bBefore.charAt(i - pointPosition + bBefore.length()) - 48;
            }
            result = a - b;
            arr[i] = String.valueOf(Math.abs(result) % 10 - nxt);
            if (result < 0) nxt = 1;
            else nxt = 0;
        }
        String num = "";
        for (int i = 0; i < arr.length; i++) num += arr[i];
        return new decimalFractionalNumber(num, arr.length - 1);
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
        for (int i = arr.length - 1; i > s + Math.max(count, other.count); i--) {
            if (Integer.parseInt(arr[i]) - 30 + nxt >= 5) nxt = 1;
            else nxt = 0;
        }
        arr[s + Math.max(count, other.count)] =
                String.valueOf(Integer.parseInt(arr[s + Math.max(count, other.count)]) + nxt);
        for (int i = s + 1; i <= s + count; i++) {
            if (i == arr.length - (number.length() - pointPosition) - (other.number.length() - other.pointPosition))
                num += ".";
            num += arr[i];
        }
        return new decimalFractionalNumber(num, Math.max(count, other.count));
    }

    public decimalFractionalNumber roundOff(int scale) {
        if (number.length() - pointPosition < scale) throw new IllegalArgumentException();
        return new decimalFractionalNumber(number.substring(0, pointPosition) + "."
                + number.substring(pointPosition, scale), pointPosition - 1 + scale);
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
        return number.substring(0, pointPosition) + "." + number.substring(pointPosition);
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