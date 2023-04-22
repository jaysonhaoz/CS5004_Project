package hw9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TheaterTest {
  private Theater theater1;
  private Theater theater2;

  @BeforeEach
  void setUp() {
    theater1 = new Theater("AMC", 5, 5, new ArrayList<>(List.of(2, 4)));
  }

  @Test
  void testConstructorFailure() {
    assertThrows(IllegalArgumentException.class, ()->new Theater("", 5, 5, new ArrayList<>(Arrays.asList(2, 4))));
    assertThrows(IllegalArgumentException.class, ()->new Theater("AMC", 0, 5, new ArrayList<>(Arrays.asList(2, 4))));
    assertThrows(IllegalArgumentException.class, ()->new Theater("AMC", 5, 0, new ArrayList<>(Arrays.asList(2, 4))));
    assertThrows(IllegalArgumentException.class, ()->new Theater("AMC", 5, 5, new ArrayList<>(Arrays.asList(2, 7))));
    assertThrows(IllegalArgumentException.class, ()->new Theater("AMC", 5, 5, new ArrayList<>(Arrays.asList(0, 4))));
    assertThrows(IllegalArgumentException.class, ()->new Theater("AMC", 5, 5, new ArrayList<>()));

  }

  @Test
  void getNthRow() {
    assertTrue(theater1.get(0).equals(theater1.getNthRow(1)));

    assertThrows(IllegalArgumentException.class, ()->theater1.getNthRow(10));
  }

  @Test
  void iterator() {
    Iterator<Row> ex = new RowIterator(theater1);
    assertEquals(theater1.iterator(), ex);
  }

  @Test
  void getTheaterName() {
    assertEquals("AMC", theater1.getTheaterName());
  }

  @Test
  void getNumberOfRows() {
    assertEquals(5, theater1.getNumberOfRows());
  }

  @Test
  void getAccessibleRows() {
    ArrayList<Integer> list = new ArrayList<>(List.of(2, 4));
    assertEquals(list, theater1.getAccessibleRows());
  }

  @Test
  void testToString() {
    String ex = """
        1  _ _ _ _ _\s
        2  = = = = =\s
        3  _ _ _ _ _\s
        4  = = = = =\s
        5  _ _ _ _ _\s
        """;
    assertEquals(ex, theater1.toString());
  }

  @Test
  void testEquals() {
    ArrayList<Integer> list1 = new ArrayList<>(List.of(1, 2));
    ArrayList<Integer> list2 = new ArrayList<>(List.of(1, 2));
    assertTrue(list1.equals(list2));
    assertTrue(theater1.equals(theater1));

    assertFalse(theater1.equals(null));
    assertFalse(theater1.equals(theater1.toString()));

    theater2 = new Theater("AMC", 4, 5, new ArrayList<>(List.of(2, 4)));
    assertFalse(theater1.equals(theater2));

    theater2 = new Theater("AMC", 5, 1, new ArrayList<>(List.of(2, 4)));
    assertFalse(theater1.equals(theater2));

    theater2 = new Theater("AMCX", 5, 5, new ArrayList<>(List.of(2, 4)));
    assertFalse(theater1.equals(theater2));

    theater2 = new Theater("AMC", 5, 5, new ArrayList<>(List.of(4)));
    assertFalse(theater1.equals(theater2));
  }

  @Test
  void testHashCode() {
    theater2 = new Theater("AMC", 5, 5, new ArrayList<>(List.of(2, 4)));
    int hash = theater2.hashCode();
    assertEquals(hash, theater1.hashCode());
  }
}