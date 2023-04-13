import java.util.ArrayList;
import java.util.Random;

public class Theater {
    private String name;
    private int numberOfRows;
    private ArrayList<Row> rows;
    private ArrayList<Integer> accessibleRows;

    public Theater(String name, ArrayList<Row> rows) {
        if (name.length() == 0) throw new IllegalArgumentException("Theater name cannot be empty!");
        if (rows.isEmpty()) throw new IllegalArgumentException("No seat row for the theater!");
        this.name = name;
        this.rows = rows;
        this.numberOfRows = this.rows.size();
        for (Row row : rows) {
            if (row.isWheelchairAccessible())
                this.accessibleRows.add(row.getRowNum());
        }
    }

    public String getName() {
        return name;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public ArrayList<Integer> getAccessibleRows() {
        return accessibleRows;
    }
}
