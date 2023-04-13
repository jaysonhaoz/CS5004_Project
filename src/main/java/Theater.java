import java.util.ArrayList;
import java.util.Random;

public class Theater {
    private String name;
    private int numberOfRows;
    private ArrayList<Row> rows;
    private ArrayList<Row> accessibleRows;


    public Theater(String name, int numberOfRows, int numSeat) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        Random random = new Random();
        for (int i = 0; i < numberOfRows; i++) {
            Row row = new Row(numSeat, i + 1, random.nextInt(0,2) == 1 ? true : false);
            if (row.isWheelchairAccessible())
            {
                accessibleRows.add(row);
            }
            rows.add(row);
        }
    }
}
