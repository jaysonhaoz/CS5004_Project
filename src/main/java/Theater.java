import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Theater {
    private String name;
    private int numberOfRows;
    private ArrayList<Row> rows;

    public Theater(String name, int numberOfRows, int numSeat, ArrayList<Integer> accessibleRows) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        rows = new ArrayList<>();
        Random random = new Random();
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
}
