import java.util.Iterator;

public class RowIterator implements Iterator<Row> {

  private Theater theater;
  private int cur;

  private int calculate = 1;

  public RowIterator(Theater theater) {
    this.cur = (1 + theater.getNumberOfRows()) / 2;
  }

  private Row getNthRow(int n) {
    for (int i = 0; i < theater.getNumberOfRows(); i++) {
      Row r = theater.get(i);
      if (r.getRowNum() == n) return r;
    }
    throw new IllegalCallerException("Cannot find row n!");
  }

  @Override
  public boolean hasNext() {
    return (this.cur >= 1 && this.cur <= theater.getNumberOfRows());
  }


  @Override
  public Row next() {
    Row res = this.getNthRow(this.cur);
    this.cur = this.cur + this.calculate;
    if (this.calculate > 0) this.calculate = -this.calculate - 1;
    else this.calculate = -this.calculate + 1;
    return res;
  }
}
