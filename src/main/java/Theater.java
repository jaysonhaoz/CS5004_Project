import java.util.ArrayList;
import java.util.Iterator;

public class Theater extends ArrayList<Row> {
    private String theaterName;
    private int numberOfRows;
    private ArrayList<Integer> accessibleRows;

    public Theater(String theaterName, int numberOfRows, int seatsInARow, ArrayList<Integer> accessibleRows) {
        super(numberOfRows);
        if (theaterName.length() == 0) throw new IllegalArgumentException("Theater name cannot be empty!");
        if (numberOfRows == 0) throw new IllegalArgumentException("number of rows cannot be 0");
        if (seatsInARow == 0) throw new IllegalArgumentException("seats in a row cannot be empty!");
        if (accessibleRows.isEmpty()) throw new IllegalArgumentException("Cannot create theater without accessible row!");
        for (int ithRow : accessibleRows) {
            if (ithRow <= 0 || ithRow > numberOfRows) throw new IllegalArgumentException("Cannot create such accessible row!");}
        for (int i = 1; i <= numberOfRows; i++) {
            Row newRow = new Row(seatsInARow, i, accessibleRows.contains(i));
            this.add(newRow);
        }
        this.theaterName = theaterName;
        this.numberOfRows = numberOfRows;
        this.accessibleRows = accessibleRows;
    }

    public Theater(ArrayList<Row> rows, String theaterName) {
        super(rows);
        if (rows.isEmpty()) throw new IllegalArgumentException("Rows is theater cannot be empty!");
        if (theaterName.length() == 0) throw new IllegalArgumentException("Theater name cannot be empty!");
        this.theaterName = theaterName;
        this.numberOfRows = rows.size();
        this.accessibleRows = new ArrayList<>();
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= this.numberOfRows; i++) {
            stringBuilder.append(this.getNthRow(i).toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
