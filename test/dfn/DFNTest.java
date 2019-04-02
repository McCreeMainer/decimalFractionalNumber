package dfn;

import static org.junit.jupiter.api.Assertions.*;

class DFNTest {

    @org.junit.jupiter.api.Test
    void plus() {
        assertEquals(new decimalFractionalNumber(3.353, 4),
                new decimalFractionalNumber(2.353, 4).plus(new decimalFractionalNumber(1.0, 2)));
    }

    @org.junit.jupiter.api.Test
    void minus() {
        assertEquals(new decimalFractionalNumber(1.353, 4),
                new decimalFractionalNumber(2.353, 4).minus(new decimalFractionalNumber(1.0, 2)));
    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        assertEquals(new decimalFractionalNumber(4.706, 4),
                new decimalFractionalNumber(2.353, 4).multiplication(new decimalFractionalNumber(2.0, 2)));
    }

    @org.junit.jupiter.api.Test
    void roundOff() {
        assertEquals(new decimalFractionalNumber(2.35, 3), new decimalFractionalNumber(2.353, 4).roundOff(3));
    }

    @org.junit.jupiter.api.Test
    void toInt() {
        assertEquals(2, new decimalFractionalNumber(2.353, 4).toInt());
    }

    @org.junit.jupiter.api.Test
    void toLong() {
        assertEquals(2l, new decimalFractionalNumber(2.353, 4).toLong());
    }

    @org.junit.jupiter.api.Test
    void toFloat() {
        assertEquals(1.353f, new decimalFractionalNumber(1.353, 4).toFloat());
    }

    @org.junit.jupiter.api.Test
    void toDouble() {
        assertEquals(1.353, new decimalFractionalNumber(1.353, 4).toDouble());
    }

    @org.junit.jupiter.api.Test
    void toStr() {
        assertEquals("1.353", new decimalFractionalNumber(1.353, 4).toString());
    }
}