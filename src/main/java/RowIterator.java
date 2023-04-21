import java.util.Iterator;

/**
 * The RowIterator class provides an iterator for iterating through the rows of a Theater object.
 * It implements the Iterator interface for Row objects.
 */
public class RowIterator implements Iterator<Row> {

  private Theater theater;
  private int cur;

  private int calculate = 1;

  /**
   * Constructs a RowIterator object for the specified Theater.
   *
   * @param theater the Theater object whose rows will be iterated
   */
  public RowIterator(Theater theater) {
    this.theater = theater;
    this.cur = (1 + theater.getNumberOfRows()) / 2;
  }


  /**
   * Returns true if the iterator has more elements (rows) to iterate.
   *
   * @return true if the iterator has more rows to iterate, false otherwise
   */
  @Override
  public boolean hasNext() {
    return (this.cur >= 1 && this.cur <= this.theater.getNumberOfRows());
  }


  /**
   * Returns the next Row object in the iteration, starting from the middle row and
   * alternating between rows above and below the middle row.
   *
   * @return the next Row object in the iteration
   */
  @Override
  public Row next() {
    Row res = this.theater.getNthRow(this.cur);
    this.cur = this.cur + this.calculate;
    if (this.calculate > 0) this.calculate = -this.calculate - 1;
    else this.calculate = -this.calculate + 1;
    return res;
  }
}
