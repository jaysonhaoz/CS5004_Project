import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Theater extends ArrayList<Row> {
    private String theaterName;
    private int numberOfRows;
    private ArrayList<Integer> accessibleRows;

    public Theater(ArrayList<Row> rows, String theaterName, ArrayList<Integer> accessibleRows) {
        super(rows);
        if (rows.isEmpty()) throw new IllegalArgumentException("Rows is theater cannot be empty!");
        if (theaterName.length() == 0) throw new IllegalArgumentException("Theater name cannot be empty!");
        this.theaterName = theaterName;
        this.numberOfRows = rows.size();
        this.accessibleRows = accessibleRows;
        for (Row row : rows) {
            if (row.isWheelchairAccessible())
                this.accessibleRows.add(row.getRowNum());
        }
    }

    public Row getNthRow(int n) {
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            Row r = this.get(i);
            if (r.getRowNum() == n) return r;
        }
        throw new IllegalArgumentException("Cannot find row n!");
    }

    @Override
    public Iterator<Row> iterator() {
        return new RowIterator(this);
    }

    public String getTheaterName() {
        return theaterName;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public ArrayList<Integer> getAccessibleRows() {
        return accessibleRows;
    }
}
