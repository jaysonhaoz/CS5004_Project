package hw9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RowIteratorTest {
  private Theater theater1;
  private Theater theater2;
  private RowIterator rowIterator1;
  private RowIterator rowIterator2;

  @BeforeEach
  void setUp() {
    theater1 = new Theater("AMC", 5, 5, new ArrayList<>(List.of(2, 4)));
    rowIterator1 = new RowIterator(theater1);
  }

  @Test
  void hasNext() {
    int cur = (1+5)/2;
    assertEquals(cur, rowIterator1.getCur());
    assertTrue(rowIterator1.hasNext());

    theater2 = new Theater("AMC", 1, 5, new ArrayList<>(List.of(1)));
    rowIterator2 = new RowIterator(theater2);
    Row row = rowIterator2.next();
    assertFalse(rowIterator2.hasNext());
  }

  @Test
  void next() {
    Row row = rowIterator1.next();
    assertEquals(3, row.getRowNum());

    row = rowIterator1.next();
    assertEquals(4, row.getRowNum());

    row = rowIterator1.next();
    assertEquals(2, row.getRowNum());
  }

  @Test
  void testEquals() {
    assertTrue(rowIterator1.equals(rowIterator1));

    assertFalse(rowIterator1.equals(null));

    rowIterator2 = new RowIterator(theater1);
    assertTrue(rowIterator1.equals(rowIterator2));

    Row row = rowIterator2.next();
    assertFalse(rowIterator1.equals(rowIterator2));
  }

  @Test
  void testHashCode() {
    rowIterator2 = new RowIterator(theater1);
    assertEquals(rowIterator1.hashCode(), rowIterator2.hashCode());
  }
}