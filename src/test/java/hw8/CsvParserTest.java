package hw8;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvParserTest {
  private CsvParser csvParserTest1;
  private CsvParser csvParserTest2;
  private List<String> testHeader;
  private List<HashMap<String, String>> mapList;
  private String filePath;


  @BeforeEach
  void setUp() {
    filePath = "csvTest.csv";
    csvParserTest1 = new CsvParser(filePath);
  }
  @Test
  void testIOException() throws IOException{
    filePath = "notExistFile";
    csvParserTest1 = new CsvParser(filePath);
  }

  @Test
  void getHeader() {
    List<String> ex = List.of("first_name", "last_name", "company_name",
        "address", "city", "county", "state", "zip", "phone1", "phone2", "email", "web");
    assertEquals(ex, csvParserTest1.getHeader());
  }

  @Test
  void getMapList() {
    List<String> keys = List.of("first_name", "last_name", "company_name",
        "address", "city", "county", "state", "zip", "phone1", "phone2", "email", "web");
    String[] customerValues1 = new String[]{"James","Butt","Benton, John B Jr","6649 N Blue Gum St",
        "New Orleans","Orleans","LA","70116","504-621-8927","504-845-1427","jbutt@gmail.com",
        "http://www.bentonjohnbjr.com"};
    String[] customerValues2 = new String[] {"Josephine","Darakjy","Chanay, Jeffrey A Esq","4 B Blue Ridge Blvd",
        "Brighton","Livingston","MI","48116","810-292-9388","810-374-9840","josephine_darakjy@darakjy.org",
        "http://www.chanayjeffreyaesq.com"};
    List<HashMap<String , String >> mapList1 = new ArrayList<>();
    HashMap<String , String > customer1 = new HashMap<>();
    HashMap<String , String > customer2 = new HashMap<>();
    int i = 0;
    for (String key : keys) {
      customer1.put(key, customerValues1[i]);
      customer2.put(key, customerValues2[i]);
      i++;
    }
    mapList1.add(customer1);
    mapList1.add(customer2);
    assertEquals(mapList1, csvParserTest1.getMapList());
  }

  @Test
  void testEquals() {
    assertTrue(csvParserTest1.equals(csvParserTest1));
    assertFalse(csvParserTest1.equals(null));

    csvParserTest2 = new CsvParser("csvTest.csv");
    assertTrue(csvParserTest1.equals(csvParserTest2));

    csvParserTest2 = new CsvParser("insurance-company-members.csv");
    assertFalse(csvParserTest1.equals(csvParserTest2));
  }

  @Test
  void testHashCode() {
    int ex = Objects.hash(csvParserTest1.getHeader(), csvParserTest1.getMapList());
    assertEquals(ex, csvParserTest1.hashCode());
  }

  @Test
  void testToString() {
    String ex = "CsvParser{header=[first_name, last_name, company_name, address, city, county, state, zip, phone1, phone2, email, web], "
        + "mapList=[{zip=70116, address=6649 N Blue Gum St, city=New Orleans, web=http://www.bentonjohnbjr.com, company_name=Benton, John B Jr, county=Orleans, phone2=504-845-1427, last_name=Butt, state=LA, first_name=James, email=jbutt@gmail.com, phone1=504-621-8927}, "
        + "{zip=48116, address=4 B Blue Ridge Blvd, city=Brighton, web=http://www.chanayjeffreyaesq.com, company_name=Chanay, Jeffrey A Esq, county=Livingston, phone2=810-374-9840, last_name=Darakjy, state=MI, first_name=Josephine, email=josephine_darakjy@darakjy.org, phone1=810-292-9388}]}";
    assertEquals(ex, csvParserTest1.toString());
  }
}