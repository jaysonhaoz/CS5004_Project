package hw9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * The Theater class represents a theater with multiple rows.
 * It extends ArrayList<Row> and stores information about the theater's name,
 * number of rows, and accessible rows for wheelchair users.
 */
public class Theater extends ArrayList<Row> {
    private String theaterName;
    private int numberOfRows;
    private ArrayList<Integer> accessibleRows;

    /**
     * Constructs a Theater object with the given parameters.
     * @param theaterName the name of the theater.
     * @param numberOfRows the number of rows in the theater.
     * @param seatsInARow the number of seats in a row.
     * @param accessibleRows an ArrayList of integers representing the accessible rows for wheelchair users.
     */
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

    /**
     * Returns the nth Row in the theater.
     * @param n the row number.
     * @return the nth Row object in the theater.
     * @throws IllegalArgumentException if the row number is not found.
     */
    public Row getNthRow(int n) {
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            Row r = this.get(i);
            if (r.getRowNum() == n) return r;
        }
        throw new IllegalArgumentException("Cannot find row n!");
    }

    /**
     * @return an Iterator of Row objects for the Theater.
     */
    @Override
    public Iterator<Row> iterator() {
        return new RowIterator(this);
    }

    /**
     * Returns the name of the theater.
     * @return the name of the theater.
     */
    public String getTheaterName() {
        return theaterName;
    }

    /**
     * Returns the number of rows in the theater.
     * @return the number of rows in the theater.
     */

    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Returns an ArrayList of integers representing the accessible rows for wheelchair users.
     * @return an ArrayList of integers representing the accessible rows.
     */
    public ArrayList<Integer> getAccessibleRows() {
        return accessibleRows;
    }

    /**
     * Returns a string representation of the theater, including each row and its seats.
     * @return the string representation of the theater.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= this.numberOfRows; i++) {
            stringBuilder.append(this.getNthRow(i).toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Indicates whether the given object is equal to this Theater object. Two Theater objects are considered equal
     * if they have the same theater name, number of rows, accessible rows for wheelchair users, and the same Row
     * objects stored in them.
     *
     * @param o the object to be compared for equality with this Theater object
     * @return true if the specified object is equal to this Theater object; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Theater rows = (Theater) o;
        return numberOfRows == rows.numberOfRows && Objects.equals(theaterName,
            rows.theaterName) && Objects.equals(accessibleRows, rows.accessibleRows);
    }

    /**
     * Returns a hash code value for the Theater object. The hash code is calculated based on the theater's
     * name, number of rows, accessible rows for wheelchair users, and the Row objects stored in it.
     *
     * @return a hash code value for this Theater object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), theaterName, numberOfRows, accessibleRows);
    }
}
