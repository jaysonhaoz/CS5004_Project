package hw8;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateProcessorTest {
  private TemplateProcessor templateProcessor1;
  private TemplateProcessor templateProcessor2;
  private String templateFile;
  private String content;
  private CsvParser csvParser;

  @BeforeEach
  void setUp() throws IOException {
    templateFile = "templateTest.txt";
    csvParser = new CsvParser("csvTest.csv");
    templateProcessor1 = new TemplateProcessor(templateFile, csvParser);
  }

  @Test
  void testExceptions() throws IOException{
    templateFile = "template";
    assertThrows(FileNotFoundException.class, ()->new TemplateProcessor(templateFile, csvParser));
  }

  @Test
  void testReplaceHolders() {
    String ex = "Benton, John B Jr.\n"
        + "James Butt\n"
        + "6649 N Blue Gum St, New Orleans,\n"
        + "Orleans, LA, 70116\n"
        + "(jbutt@gmail.com)\n"
        + "\n"
        + "\n"
        + "Dear James Butt,\n"
        + "As you may have heard or read, last month we learned that criminals forced their way into our systems,\n"
        + "and stole information about our customers. Late last week, as part of our ongoing investigation,\n"
        + "we learned that the taken information includes names, mailing addresses, phone numbers or email addresses.\n";
    assertEquals(ex, templateProcessor1.replaceHolders(csvParser.getMapList().get(0)));
  }
  @Test
  void writeFile() throws IOException{
    String ex = "Benton, John B Jr.\n"
        + "James Butt\n"
        + "6649 N Blue Gum St, New Orleans,\n"
        + "Orleans, LA, 70116\n"
        + "(jbutt@gmail.com)\n"
        + "\n"
        + "\n"
        + "Dear James Butt,\n"
        + "As you may have heard or read, last month we learned that criminals forced their way into our systems,\n"
        + "and stole information about our customers. Late last week, as part of our ongoing investigation,\n"
        + "we learned that the taken information includes names, mailing addresses, phone numbers or email addresses.\n";
    File output = new File("testOutFiles/James-email-template.txt");
    templateProcessor1.writeFile(templateProcessor1.replaceHolders(csvParser.getMapList().get(0))
    , "testOutFiles", "James-email-template.txt");
    BufferedReader reader = new BufferedReader(new FileReader(output));
    StringBuilder res = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      res.append(line).append(System.lineSeparator());
    }
    String result = res.toString();
    assertEquals(ex,result);

  }

  @Test
  void writeAllFiles() {
  }

  @Test
  void testEquals() throws IOException{
    assertTrue(templateProcessor1.equals(templateProcessor1));
    assertFalse(templateProcessor1.equals(null));
    assertFalse(templateProcessor1.equals(csvParser));

    templateProcessor2 = new TemplateProcessor(templateFile, csvParser);
    assertTrue(templateProcessor1.equals(templateProcessor2));

    templateProcessor2 = new TemplateProcessor("email-template.txt", csvParser);
    assertFalse(templateProcessor1.equals(templateProcessor2));

    templateProcessor2 = new TemplateProcessor(templateFile, new CsvParser("insurance-company-members.csv"));
    assertFalse(templateProcessor1.equals(templateProcessor2));

  }

  @Test
  void testHashCode() {
    int ex = Objects.hash(templateProcessor1.getTemplateFile(), templateProcessor1.getTemplateContent())
        + templateProcessor1.getCsv().hashCode();
    assertEquals(ex, templateProcessor1.hashCode());
  }

  @Test
  void testToString() {
    String ex = "TemplateProcessor{templateFile='templateTest.txt', templateContent='[[company_name]].\n"
        + "[[first_name]] [[last_name]]\n"
        + "[[address]], [[city]],\n"
        + "[[county]], [[state]], [[zip]]\n"
        + "([[email]])\n"
        + "\n"
        + "\n"
        + "Dear [[first_name]] [[last_name]],\n"
        + "As you may have heard or read, last month we learned that criminals forced their way into our systems,\n"
        + "and stole information about our customers. Late last week, as part of our ongoing investigation,\n"
        + "we learned that the taken information includes names, mailing addresses, phone numbers or email addresses.\n"
        + "', csv=CsvParser{header=[first_name, last_name, company_name, address, city, county, state, zip, phone1, phone2, email, web], "
        + "mapList=[{zip=70116, address=6649 N Blue Gum St, city=New Orleans, web=http://www.bentonjohnbjr.com, company_name=Benton, John B Jr, county=Orleans, phone2=504-845-1427, last_name=Butt, state=LA, first_name=James, email=jbutt@gmail.com, phone1=504-621-8927}, "
        + "{zip=48116, address=4 B Blue Ridge Blvd, city=Brighton, web=http://www.chanayjeffreyaesq.com, company_name=Chanay, Jeffrey A Esq, county=Livingston, phone2=810-374-9840, last_name=Darakjy, state=MI, first_name=Josephine, email=josephine_darakjy@darakjy.org, phone1=810-292-9388}]}}";
    assertEquals(ex, templateProcessor1.toString());
  }

  @Test
  void getTemplateFile() {
    String temp = "templateTest.txt";
    assertEquals(temp, templateProcessor1.getTemplateFile());
  }

  @Test
  void getTemplateContent() {
    content = "[[company_name]]." + System.lineSeparator()
        + "[[first_name]] [[last_name]]" + System.lineSeparator()
        + "[[address]], [[city]]," + System.lineSeparator()
        + "[[county]], [[state]], [[zip]]" + System.lineSeparator()
        + "([[email]])" + System.lineSeparator()
        + System.lineSeparator()
        + System.lineSeparator()
        + "Dear [[first_name]] [[last_name]]," + System.lineSeparator()
        + "As you may have heard or read, last month we learned that criminals forced their way into our systems," + System.lineSeparator()
        + "and stole information about our customers. Late last week, as part of our ongoing investigation," + System.lineSeparator()
        + "we learned that the taken information includes names, mailing addresses, phone numbers or email addresses." + System.lineSeparator();
    assertEquals(content, templateProcessor1.getTemplateContent());
  }

  @Test
  void getCsv() {
    CsvParser ex = new CsvParser("csvTest.csv");
    assertEquals(ex, templateProcessor1.getCsv());
  }

}