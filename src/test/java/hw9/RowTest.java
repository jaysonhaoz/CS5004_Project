package hw9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RowTest {
  private Row rowTest1;
  private Row rowTest2;

  @BeforeEach
  void setUp() {
    rowTest1 = new Row(3, 1, false);
  }

  @Test
  void constructorFail() {
    assertThrows(IllegalArgumentException.class, ()->new Row(0, 1, true));
    assertThrows(IllegalArgumentException.class, ()->new Row(27, 1, true));
    assertThrows(IllegalArgumentException.class, ()->new Row(13, 0, true));

  }

  @Test
  void getRowNum() {
    assertEquals(1, rowTest1.getRowNum());
  }

  @Test
  void isWheelchairAccessible() {
    assertFalse(rowTest1.isWheelchairAccessible());
  }

  @Test
  void reserveRow() {
    assertTrue(rowTest1.reserveRow(2, "Amy"));
    assertFalse(rowTest1.reserveRow(2, "Amy"));
    assertThrows(IllegalArgumentException.class, ()->rowTest1.reserveRow(1, ""));
  }

  @Test
  void testToString() {
    String ex = "1  _ _ _ ";
    assertEquals(ex, rowTest1.toString());

    rowTest2 = new Row(12, 5, false);
    String ex2= "5  _ _ _ _ _ _ _ _ _ _ _ _ ";
    assertEquals(ex2, rowTest2.toString());

  }

  @Test
  void testEquals() {
    assertTrue(rowTest1.equals(rowTest1));

    assertFalse(rowTest1.equals(null));
    assertFalse(rowTest1.equals(rowTest1.toString()));

    rowTest2 = new Row(3, 1, false);
    assertTrue(rowTest1.equals(rowTest2));

    rowTest2 = new Row(1, 1, false);
    assertFalse(rowTest1.equals(rowTest2));

    rowTest2 = new Row(3, 2, false);
    assertFalse(rowTest1.equals(rowTest2));

    rowTest2 = new Row(3, 1, true);
    assertFalse(rowTest1.equals(rowTest2));
  }

  @Test
  void testHashCode() {
    rowTest2 = new Row(3, 1, false);
    int ex = rowTest2.hashCode();
    assertEquals(ex, rowTest1.hashCode());
  }
}