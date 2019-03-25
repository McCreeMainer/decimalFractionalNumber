package dfn;

import static org.junit.jupiter.api.Assertions.*;

class DFNTest {

    @org.junit.jupiter.api.Test
    void plus() {
        assertEquals(new decimalFractionalNumber(3.353, 3),
                new decimalFractionalNumber(2.353, 3).plus(new decimalFractionalNumber(1.0, 1)));
        assertEquals(new decimalFractionalNumber(3123.23456, 5),
                new decimalFractionalNumber(1.2f, 1).plus(new decimalFractionalNumber(3122.03456, 5)));
        assertEquals(new decimalFractionalNumber(228.1488, 4),
                new decimalFractionalNumber(228, 1).plus(new decimalFractionalNumber(0.1488, 4)));
        assertEquals(new decimalFractionalNumber(123456799.01, 3),
                new decimalFractionalNumber(123456789l, 3).plus(new decimalFractionalNumber(10.01, 2)));
        assertEquals(new decimalFractionalNumber(1.353, 3),
                new decimalFractionalNumber(2.353, 3).plus(new decimalFractionalNumber(-1.0, 1)));
    }

    @org.junit.jupiter.api.Test
    void minus() {
        assertEquals(new decimalFractionalNumber(1.353, 3),
                new decimalFractionalNumber(2.353, 3).minus(new decimalFractionalNumber(1.0, 1)));
        assertEquals(new decimalFractionalNumber(3.21, 2),
                new decimalFractionalNumber(6.14f, 2).minus(new decimalFractionalNumber(2.93, 2)));
        assertEquals(new decimalFractionalNumber(224.877, 3),
                new decimalFractionalNumber(228, 1).minus(new decimalFractionalNumber(3.123, 3)));
        assertEquals(new decimalFractionalNumber(123.123, 4),
                new decimalFractionalNumber(227l, 2).minus(new decimalFractionalNumber(103.877, 4)));
        assertEquals(new decimalFractionalNumber(2.353, 3),
                new decimalFractionalNumber(1.353, 3).minus(new decimalFractionalNumber(-1.0, 1)));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        assertEquals(new decimalFractionalNumber(2.353, 3),
                new decimalFractionalNumber(2.353, 3).multiplication(new decimalFractionalNumber(1.0, 1)));
        assertEquals(new decimalFractionalNumber(14.77, 2),
                new decimalFractionalNumber(0.123f, 2).multiplication(new decimalFractionalNumber(123.1, 1)));
        assertEquals(new decimalFractionalNumber(3392.64, 3),
                new decimalFractionalNumber(228, 3).multiplication(new decimalFractionalNumber(14.88, 2)));
        assertEquals(new decimalFractionalNumber(123456.7, 1),
                new decimalFractionalNumber(1234567l, 1).multiplication(new decimalFractionalNumber(0.1, 1)));
        assertEquals(new decimalFractionalNumber(-2.353, 3),
                new decimalFractionalNumber(2.353, 3).multiplication(new decimalFractionalNumber(-1.0, 1)));
    }

    @org.junit.jupiter.api.Test
    void roundOff() {
        assertEquals(new decimalFractionalNumber(2.35, 2), new decimalFractionalNumber(2.353, 3).roundOff(2));
        assertEquals(new decimalFractionalNumber(228.12, 2), new decimalFractionalNumber(228.129f, 4).roundOff(2));
        assertEquals(new decimalFractionalNumber(123.0, 123), new decimalFractionalNumber(123, 1).roundOff(123));
        assertEquals(new decimalFractionalNumber(123.0, 1), new decimalFractionalNumber(123l, 123).roundOff(1));
        assertEquals(new decimalFractionalNumber(-1488.12, 34), new decimalFractionalNumber(-1488.12, 3).roundOff(34));
    }

    @org.junit.jupiter.api.Test
    void toInt() {
        assertEquals(2, new decimalFractionalNumber(2.353, 3).toInt());
        assertEquals(228, new decimalFractionalNumber(228.1488f, 3).toInt());
        assertEquals(2, new decimalFractionalNumber(2, 3).toInt());
        assertEquals(123456789, new decimalFractionalNumber(123456789l, 3).toInt());
        assertEquals(-2, new decimalFractionalNumber(-2.353, 3).toInt());
    }

    @org.junit.jupiter.api.Test
    void toLong() {
        assertEquals(2l, new decimalFractionalNumber(2.353, 3).toLong());
        assertEquals(228l, new decimalFractionalNumber(228.1488f, 3).toLong());
        assertEquals(2l, new decimalFractionalNumber(2, 3).toLong());
        assertEquals(123456789l, new decimalFractionalNumber(123456789l, 3).toLong());
        assertEquals(-2l, new decimalFractionalNumber(-2.353, 3).toLong());
    }

    @org.junit.jupiter.api.Test
    void toFloat() {
        assertEquals(1.353f, new decimalFractionalNumber(1.353, 3).toFloat());
        assertEquals(13.88f, new decimalFractionalNumber(13.88f, 3).toFloat());
        assertEquals(1.0f, new decimalFractionalNumber(1, 3).toFloat());
        assertEquals(300.0f, new decimalFractionalNumber(300l, 3).toFloat());
    }

    @org.junit.jupiter.api.Test
    void toDouble() {
        assertEquals(1.353, new decimalFractionalNumber(1.353, 3).toDouble());
        assertEquals(13.88, new decimalFractionalNumber(13.88f, 3).toDouble());
        assertEquals(1.0, new decimalFractionalNumber(1, 3).toDouble());
        assertEquals(300.0, new decimalFractionalNumber(300l, 3).toDouble());
        assertEquals(-1.353, new decimalFractionalNumber(-1.353, 3).toDouble());

    }

    @org.junit.jupiter.api.Test
    void toStr() {
        assertEquals("1.353", new decimalFractionalNumber(1.353, 3).toString());
        assertEquals("13.88", new decimalFractionalNumber(13.88f, 3).toString());
        assertEquals("1.0", new decimalFractionalNumber(1, 3).toString());
        assertEquals("300.0", new decimalFractionalNumber(300l, 3).toString());
        assertEquals("-1.353", new decimalFractionalNumber(-1.353, 3).toString());

    }
}