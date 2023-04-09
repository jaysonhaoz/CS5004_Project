package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CsvParser {
  private List<String> header;
  private List<HashMap<String, String>> mapList;

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

  public List<String> getHeader() {
    return header;
  }

  public List<HashMap<String, String>> getMapList() {
    return mapList;
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(header, mapList);
  }

  @Override
  public String toString() {
    return "CsvParser{" +
        "header=" + header +
        ", mapList=" + mapList +
        '}';
  }
}
