package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * CsvParser class is used to parse a CSV file and store its data in a list of maps.
 * Each map represents a row in the CSV file, with the keys being the column headers and the
 * values being the data in each cell.
 */
public class CsvParser {
  private List<String> header;
  private List<HashMap<String, String>> mapList;

  /**
   * Constructor for the CsvParser class.
   * Takes an input CSV file, reads it line by line, and stores the data in a list of maps.
   * @param inputFile The path to the input CSV file.
   */
  public CsvParser(String inputFile) {
    this.header = new ArrayList<>();
    this.mapList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      String eachLine;
      if ((eachLine = reader.readLine()) != null && eachLine.length() > 0) {
        this.header = lineSplitter(eachLine);
      }
      while ((eachLine = reader.readLine()) != null && eachLine.length() > 0) {
        List<String > values = lineSplitter(eachLine);
        HashMap<String, String> customerMap = new HashMap<>();
        for (int i = 0; i < header.size(); i++) {
          customerMap.put(header.get(i), values.get(i));
        }
        this.mapList.add(customerMap);
      }
    } catch (IOException ioException) {
      System.out.println("Something went wrong! : " + ioException.getMessage());
      ioException.printStackTrace();
    }
  }

  /**
   * Splits a given CSV line into a list of strings, preserving commas within double quotes.
   * This method is used to handle CSV lines where data values may contain commas within double quotes.
   * @param line The input CSV line to be split into individual values.
   * @return A list of strings representing the individual values in the CSV line.
   * @throws IllegalArgumentException If the input line is empty.
   */
  private List<String> lineSplitter(String line) {
    if (line.length() == 0) throw new IllegalArgumentException("String cannot be empty!");
    List<String> res = new ArrayList<>();
    String[] afterSplit = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    for (int i = 0; i < afterSplit.length; i++) {
      afterSplit[i] = afterSplit[i].replace("\"", "");
      res.add(afterSplit[i]);
    }
    return res;
  }

  /**
   * Returns the header of the CSV file as a list of strings.
   * The header is the first row of the CSV file and contains the column names.
   * @return  A list of strings representing the header of the CSV file.
   */
  public List<String> getHeader() {
    return header;
  }

  /**
   * Returns the parsed CSV data as a list of HashMaps.
   * Each HashMap represents a row in the CSV file, with the keys being the column headers and the
   * values being the data in each cell.
   * @return A list of HashMaps representing the parsed CSV data.
   */
  public List<HashMap<String, String>> getMapList() {
    return mapList;
  }

  /**
   * @param o object
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CsvParser csvParser = (CsvParser) o;
    return Objects.equals(header, csvParser.header) && Objects.equals(mapList,
        csvParser.mapList);
  }

  /**
   * @return hash val
   */
  @Override
  public int hashCode() {
    return Objects.hash(header, mapList);
  }

  /**
   * @return a string
   */
  @Override
  public String toString() {
    return "CsvParser{" +
        "header=" + header +
        ", mapList=" + mapList +
        '}';
  }
}
