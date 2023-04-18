import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Theater {
    private String name;
    private int numberOfRows;
    private ArrayList<Row> rows;
<<<<<<< HEAD
    private ArrayList<Integer> accessibleRows;

    public Theater(String name, ArrayList<Row> rows) {
        if (name.length() == 0)
            throw new IllegalArgumentException("Theater name cannot be empty!");
        if (rows.isEmpty())
            throw new IllegalArgumentException("No seat row for the theater!");
        this.name = name;
        this.rows = rows;
        this.numberOfRows = this.rows.size();
        for (Row row : rows) {
            if (row.isWheelchairAccessible())
                this.accessibleRows.add(row.getRowNum());
        }
    }
=======

>>>>>>> d595e92232fb4165abc36d043a88674a19927a4b
    public Theater(String name, int numberOfRows, int numSeat, ArrayList<Integer> accessibleRows) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        rows = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++) {
            boolean isAccessible = accessibleRows.contains(i + 1);
            Row row = new Row(numSeat, i + 1, isAccessible);
            rows.add(row);
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
<<<<<<< HEAD

    public ArrayList<Integer> getAccessibleRows() {
        return accessibleRows;
    }
=======
>>>>>>> d595e92232fb4165abc36d043a88674a19927a4b
}
