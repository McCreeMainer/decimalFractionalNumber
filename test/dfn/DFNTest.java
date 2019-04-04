package dfn;

import static org.junit.jupiter.api.Assertions.*;

class DFNTest {

    @org.junit.jupiter.api.Test
    void plus() {
        assertEquals(new decimalFractionalNumber(3.353, 4),
                new decimalFractionalNumber(2.353, 4).plus(new decimalFractionalNumber(1.0, 2)));
        assertEquals(new decimalFractionalNumber(3.353, 6),
                new decimalFractionalNumber(2.353f, 6).plus(new decimalFractionalNumber(1, 1)));
        assertEquals(new decimalFractionalNumber(3.0, 4),
                new decimalFractionalNumber(2, 4).plus(new decimalFractionalNumber(1.0, 2)));
        assertEquals(new decimalFractionalNumber(200.0, 4),
                new decimalFractionalNumber(199l, 4).plus(new decimalFractionalNumber(1.0, 2)));
        assertEquals(new decimalFractionalNumber(3.353, 4),
                new decimalFractionalNumber("2.353", 4).plus(new decimalFractionalNumber(1.0, 2)));
    }

    @org.junit.jupiter.api.Test
    void minus() {
        assertEquals(new decimalFractionalNumber(1.353, 4),
                new decimalFractionalNumber(2.353, 4).minus(new decimalFractionalNumber(1.0, 2)));
        assertEquals(new decimalFractionalNumber(1.353, 6),
                new decimalFractionalNumber(2.353f, 6).minus(new decimalFractionalNumber(1, 1)));
        assertEquals(new decimalFractionalNumber(1.0, 4),
                new decimalFractionalNumber(2, 4).minus(new decimalFractionalNumber(1.0, 2)));
        assertEquals(new decimalFractionalNumber(199.0, 4),
                new decimalFractionalNumber(200l, 4).minus(new decimalFractionalNumber(1.0, 2)));
        assertEquals(new decimalFractionalNumber(1.353, 4),
                new decimalFractionalNumber("2.353", 4).minus(new decimalFractionalNumber(1.0, 2)));


    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        assertEquals(new decimalFractionalNumber(4.706, 4),
                new decimalFractionalNumber(2.353, 4).multiplication(new decimalFractionalNumber(2.0, 2)));
        assertEquals(new decimalFractionalNumber("0.0470", 5),
                new decimalFractionalNumber(0.2353f, 5).multiplication(new decimalFractionalNumber(0.2, 2)));
        assertEquals(new decimalFractionalNumber(56, 4),
                new decimalFractionalNumber(28, 4).multiplication(new decimalFractionalNumber(2, 2)));
        assertEquals(new decimalFractionalNumber(470.6, 4),
                new decimalFractionalNumber(2.353, 4).multiplication(new decimalFractionalNumber(200l, 3)));
        assertEquals(new decimalFractionalNumber("0.470", 4),
                new decimalFractionalNumber("2.353", 4).multiplication(new decimalFractionalNumber("0.2", 2)));

    }

    @org.junit.jupiter.api.Test
    void roundOff() {
        assertEquals(new decimalFractionalNumber(2.35, 3), new decimalFractionalNumber(2.353, 4).roundOff(3));
        assertEquals(new decimalFractionalNumber(2.353f, 6), new decimalFractionalNumber(2.353f, 4).roundOff(6));
        assertEquals(new decimalFractionalNumber(1, 1), new decimalFractionalNumber(1, 4).roundOff(1));
        assertEquals(new decimalFractionalNumber(200l, 3), new decimalFractionalNumber(200l, 73).roundOff(3));
        assertEquals(new decimalFractionalNumber(2.353, 4), new decimalFractionalNumber("2.353234", 7).roundOff(4));
    }

    @org.junit.jupiter.api.Test
    void toInt() {
        assertEquals(2, new decimalFractionalNumber(2.353, 4).toInt());
        assertEquals(1l, new decimalFractionalNumber(1.3f, 4).toInt());
        assertEquals(1l, new decimalFractionalNumber(1, 4).toInt());
        assertEquals(100l, new decimalFractionalNumber(100l, 4).toInt());
        assertEquals(2l, new decimalFractionalNumber("2.353", 4).toInt());
    }

    @org.junit.jupiter.api.Test
    void toLong() {
        assertEquals(2l, new decimalFractionalNumber(2.353, 4).toLong());
        assertEquals(1l, new decimalFractionalNumber(1.3f, 4).toLong());
        assertEquals(1l, new decimalFractionalNumber(1, 4).toLong());
        assertEquals(100l, new decimalFractionalNumber(100l, 4).toLong());
        assertEquals(2l, new decimalFractionalNumber("2.353", 4).toLong());
    }

    @org.junit.jupiter.api.Test
    void toFloat() {
        assertEquals(1.353f, new decimalFractionalNumber(1.353, 4).toFloat());
        assertEquals(1.3f, new decimalFractionalNumber(1.3f, 4).toFloat());
        assertEquals(1.0f, new decimalFractionalNumber(1, 4).toFloat());
        assertEquals(100.0f, new decimalFractionalNumber(100l, 4).toFloat());
        assertEquals(1.353f, new decimalFractionalNumber("1.353", 4).toFloat());
    }

    @org.junit.jupiter.api.Test
    void toDouble() {
        assertEquals(1.353, new decimalFractionalNumber(1.353, 4).toDouble());
        assertEquals(1.3, new decimalFractionalNumber(1.3f, 4).toDouble());
        assertEquals(1.0, new decimalFractionalNumber(1, 4).toDouble());
        assertEquals(100.0, new decimalFractionalNumber(100l, 4).toDouble());
        assertEquals(1.353, new decimalFractionalNumber("1.353", 4).toDouble());
    }

    @org.junit.jupiter.api.Test
    void toStr() {
        assertEquals("1.353", new decimalFractionalNumber(1.353, 4).toString());
        assertEquals("1.3", new decimalFractionalNumber(1.3f, 4).toString());
        assertEquals("1", new decimalFractionalNumber(1, 4).toString());
        assertEquals("100", new decimalFractionalNumber(100l, 4).toString());
        assertEquals("1.353", new decimalFractionalNumber("1.353", 4).toString());
    }
}