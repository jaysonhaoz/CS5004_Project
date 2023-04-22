package hw9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationsServiceTest {
  private Theater theater1;
  private ReservationsService rs1;

  @BeforeEach
  void setUp() {
    theater1 = new Theater("AMC", 5, 5, new ArrayList<>(List.of(2, 4)));
    rs1 = new ReservationsService();
  }

  @Test
  void reserveSeat() {
    assertThrows(IllegalArgumentException.class, ()-> rs1.reserveSeat(theater1, 0, "Amy", true));

    assertTrue(rs1.reserveSeat(theater1, 5, "Amy", true));
    assertTrue(rs1.reserveSeat(theater1, 5, "Adam", false));
    assertFalse(rs1.reserveSeat(theater1, 6, "Emily", false));
    assertTrue(rs1.reserveSeat(theater1, 5, "Adam", true));
    assertFalse(rs1.reserveSeat(theater1, 1, "Adam", true));
  }

  @Test
  void getRowReserved() {
    assertTrue(rs1.reserveSeat(theater1, 5, "Amy", true));
    assertEquals(4, rs1.getRowReserved());

    assertTrue(rs1.reserveSeat(theater1, 5, "Adam", false));
    assertEquals(3, rs1.getRowReserved());
  }
}