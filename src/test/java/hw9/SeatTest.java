package hw9;

import static org.junit.jupiter.api.Assertions.*;

import hw9.Seat;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeatTest {
  private Seat seatTest1;
  private Seat seatTest2;

  @BeforeEach
  void setUp() {
    seatTest1 = new Seat("A");
  }

  @Test
  void getName() {
    String ex = "A";
    assertEquals(ex, seatTest1.getName());
  }

  @Test
  void getReservedFor() {
    assertNull(seatTest1.getReservedFor());
  }

  @Test
  void setReservedFor() {
    seatTest1.setReservedFor("Alex");
    assertEquals("Alex", seatTest1.getReservedFor());
  }

  @Test
  void isReserved() {
    assertFalse(seatTest1.isReserved());

    seatTest1.setReservedFor("Alex");
    assertTrue(seatTest1.isReserved());

    assertThrows(IllegalCallerException.class, ()->seatTest1.setReservedFor("Adam"));
  }

  @Test
  void setWheelChairAccessible() {
    assertFalse(seatTest1.isWheelChairAccessible());

    seatTest1.setWheelChairAccessible(true);
    assertTrue(seatTest1.isWheelChairAccessible());
  }

  @Test
  void testToString() {
    assertEquals("_", seatTest1.toString());

    seatTest1.setWheelChairAccessible(true);
    assertEquals("=", seatTest1.toString());

    seatTest1.setReservedFor("Alex");
    assertEquals("X", seatTest1.toString());
  }

  @Test
  void testEquals() {
    assertTrue(seatTest1.equals(seatTest1));

    assertFalse(seatTest1.equals(null));
    assertFalse(seatTest1.equals(seatTest1.toString()));

    seatTest2 = new Seat("A");
    assertTrue(seatTest1.equals(seatTest2));

    seatTest1.setReservedFor("Amy");
    assertFalse(seatTest1.equals(seatTest2));

    seatTest2.setReservedFor("Amy");
    seatTest1.setWheelChairAccessible(true);
    assertFalse(seatTest1.equals(seatTest2));
  }

  @Test
  void testHashCode() {
    int ex = Objects.hash(seatTest1.getName(), seatTest1.getReservedFor(), seatTest1.isWheelChairAccessible());
    assertEquals(ex, seatTest1.hashCode());
  }
}