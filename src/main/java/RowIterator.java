import java.util.Iterator;

public class RowIterator implements Iterator<Row> {

  private Theater theater;
  private int cur;

  private int calculate = 1;

  public RowIterator(Theater theater) {
    this.theater = theater;
    this.cur = (1 + theater.getNumberOfRows()) / 2;
  }


  @Override
  public boolean hasNext() {
    return (this.cur >= 1 && this.cur <= this.theater.getNumberOfRows());
  }


  @Override
  public Row next() {
    Row res = this.theater.getNthRow(this.cur);
    this.cur = this.cur + this.calculate;
    if (this.calculate > 0) this.calculate = -this.calculate - 1;
    else this.calculate = -this.calculate + 1;
    return res;
  }
}
