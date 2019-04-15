package dfn;

import static org.junit.jupiter.api.Assertions.*;

class DFNTest {

    @org.junit.jupiter.api.Test
    void plus() {
        assertEquals(new DecimalFractionalNumber(3.353, 4),
                new DecimalFractionalNumber(2.353, 4).plus(new DecimalFractionalNumber(1.0, 2)));
        assertEquals(new DecimalFractionalNumber(3.353, 6),
                new DecimalFractionalNumber(2.353f, 6).plus(new DecimalFractionalNumber(1, 1)));
        assertEquals(new DecimalFractionalNumber(3.0, 4),
                new DecimalFractionalNumber(2, 4).plus(new DecimalFractionalNumber(1.0, 2)));
        assertEquals(new DecimalFractionalNumber(200.0, 4),
                new DecimalFractionalNumber(199l, 4).plus(new DecimalFractionalNumber(1.0, 2)));
        assertEquals(new DecimalFractionalNumber(3.353, 4),
                new DecimalFractionalNumber("2.353", 4).plus(new DecimalFractionalNumber(1.0, 2)));
    }

    @org.junit.jupiter.api.Test
    void minus() {
        assertEquals(new DecimalFractionalNumber(1.353, 4),
                new DecimalFractionalNumber(2.353, 4).minus(new DecimalFractionalNumber(1.0, 2)));
        assertEquals(new DecimalFractionalNumber(1.353, 6),
                new DecimalFractionalNumber(2.353f, 6).minus(new DecimalFractionalNumber(1, 1)));
        assertEquals(new DecimalFractionalNumber(1.0, 4),
                new DecimalFractionalNumber(2, 4).minus(new DecimalFractionalNumber(1.0, 2)));
        assertEquals(new DecimalFractionalNumber(199.0, 4),
                new DecimalFractionalNumber(200l, 4).minus(new DecimalFractionalNumber(1.0, 2)));
        assertEquals(new DecimalFractionalNumber(1.353, 4),
                new DecimalFractionalNumber("2.353", 4).minus(new DecimalFractionalNumber(1.0, 2)));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        assertEquals(new DecimalFractionalNumber(4.7, 2),
                new DecimalFractionalNumber(2.353, 4).multiplication(new DecimalFractionalNumber(2.0, 2)));
        assertEquals(new DecimalFractionalNumber(0.1, 2),
                new DecimalFractionalNumber(0.2353f, 5).multiplication(new DecimalFractionalNumber(0.2, 2)));
        assertEquals(new DecimalFractionalNumber(56, 2),
                new DecimalFractionalNumber(28, 4).multiplication(new DecimalFractionalNumber(2, 2)));
        assertEquals(new DecimalFractionalNumber(109, 3),
                new DecimalFractionalNumber(0.470, 4).multiplication(new DecimalFractionalNumber(232l, 3)));
        assertEquals(new DecimalFractionalNumber(0.5, 2),
                new DecimalFractionalNumber("2.353", 4).multiplication(new DecimalFractionalNumber("0.2", 2)));

    }

    @org.junit.jupiter.api.Test
    void roundOff() {
        assertEquals(new DecimalFractionalNumber(2.35, 3), new DecimalFractionalNumber(2.353, 4).roundOff(3));
        assertEquals(new DecimalFractionalNumber(2.353f, 6), new DecimalFractionalNumber(2.353f, 4).roundOff(6));
        assertEquals(new DecimalFractionalNumber(1, 1), new DecimalFractionalNumber(1, 4).roundOff(1));
        assertEquals(new DecimalFractionalNumber(200l, 3), new DecimalFractionalNumber(200l, 73).roundOff(3));
        assertEquals(new DecimalFractionalNumber(2.353, 4), new DecimalFractionalNumber("2.353234", 7).roundOff(4));
    }

    @org.junit.jupiter.api.Test
    void toInt() {
        assertEquals(2, new DecimalFractionalNumber(2.353, 4).toInt());
        assertEquals(1l, new DecimalFractionalNumber(1.3f, 4).toInt());
        assertEquals(1l, new DecimalFractionalNumber(1, 4).toInt());
        assertEquals(100l, new DecimalFractionalNumber(100l, 4).toInt());
        assertEquals(2l, new DecimalFractionalNumber("2.353", 4).toInt());
    }

    @org.junit.jupiter.api.Test
    void toLong() {
        assertEquals(2l, new DecimalFractionalNumber(2.353, 4).toLong());
        assertEquals(1l, new DecimalFractionalNumber(1.3f, 4).toLong());
        assertEquals(1l, new DecimalFractionalNumber(1, 4).toLong());
        assertEquals(100l, new DecimalFractionalNumber(100l, 4).toLong());
        assertEquals(2l, new DecimalFractionalNumber("2.353", 4).toLong());
    }

    @org.junit.jupiter.api.Test
    void toFloat() {
        assertEquals(1.353f, new DecimalFractionalNumber(1.353, 4).toFloat());
        assertEquals(1.3f, new DecimalFractionalNumber(1.3f, 4).toFloat());
        assertEquals(1.0f, new DecimalFractionalNumber(1, 4).toFloat());
        assertEquals(100.0f, new DecimalFractionalNumber(100l, 4).toFloat());
        assertEquals(1.353f, new DecimalFractionalNumber("1.353", 4).toFloat());
    }

    @org.junit.jupiter.api.Test
    void toDouble() {
        assertEquals(1.353, new DecimalFractionalNumber(1.353, 4).toDouble());
        assertEquals(99999999999999.0, new DecimalFractionalNumber(99999999999999.0, 25).toDouble());
        assertEquals(1.3, new DecimalFractionalNumber(1.3f, 4).toDouble());
        assertEquals(1.0, new DecimalFractionalNumber(1, 4).toDouble());
        assertEquals(100.0, new DecimalFractionalNumber(100l, 4).toDouble());
        assertEquals(1.353, new DecimalFractionalNumber("1.353", 4).toDouble());
    }

    @org.junit.jupiter.api.Test
    void toStr() {
        assertEquals("99999999999999", new DecimalFractionalNumber(99999999999999.0, 25).toString());
        assertEquals("1.3", new DecimalFractionalNumber(1.3f, 4).toString());
        assertEquals("1", new DecimalFractionalNumber(1, 4).toString());
        assertEquals("100", new DecimalFractionalNumber(100l, 4).toString());
        assertEquals("1.353", new DecimalFractionalNumber("1.353", 4).toString());
    }
}