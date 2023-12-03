package hw8;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {
  private static final String EMAIL = "--email";
  private static final String LETTER = "--letter";
  private static final String EMAIL_TEMPLATE = "--email-template";
  private static final String LETTER_TEMPLATE = "--letter-template";
  private static final String OUTPUT_DIR = "--output-dir";
  private static final String CSV_FILE = "--csv-file";
  private static final Set<String> setOfCommands = Set.of(EMAIL, EMAIL_TEMPLATE, LETTER,
      LETTER_TEMPLATE, OUTPUT_DIR, CSV_FILE);
  private CommandLineParser commandLineParser1;
  private CommandLineParser commandLineParser2;
  private CommandLineParser commandLineParser3;
  private String[] args1;
  private String[] args2;
  private String[] args3;



  @BeforeEach
  void setUp() {
    args1 = "--email --email-template templateTest.txt --output-dir testOutFiles --csv-file csvTest.csv".split(" ");
    args2 = "--letter --letter-template templateTest.txt --output-dir testOutFiles --csv-file csvTest.csv".split(" ");
    args3 ="--letter --letter-template templateTest.txt --output-dir --csv-file csvTest.csv outPutDir".split(" ");
    commandLineParser1 = new CommandLineParser(args1);
    commandLineParser2 = new CommandLineParser(args2);
    commandLineParser3 = new CommandLineParser(args3);
  }

  @Test
  void testIllegalCMLException() {
    String[] args4 ="--letter --letter-template templateTest.txt --output-dir --csv-file csvTest.csv".split(" ");
    assertThrows(IllegalCommandLineArgumentsException.class, ()->new CommandLineParser(args4));

    args3 ="--lette --letter-template templateTest.txt --output-dir out --csv-file csvTest.csv".split(" ");
    assertThrows(IllegalCommandLineArgumentsException.class, ()->new CommandLineParser(args3));
  }

  @Test
  void getArgs() {
    String[] ex = new String[]{"--email", "--email-template", "templateTest.txt", "--output-dir", "testOutFiles", "--csv-file", "csvTest.csv"};
    assertTrue(Arrays.equals(ex, commandLineParser1.getArgs()));
  }

  @Test
  void getEmailOption() {
    assertTrue(commandLineParser1.getEmailOption());
    assertFalse(commandLineParser2.getEmailOption());
  }

  @Test
  void getEmailTemplateFile() {
    assertEquals(commandLineParser1.getEmailTemplateFile(), "templateTest.txt");
    assertNull(commandLineParser2.getEmailTemplateFile());
  }

  @Test
  void getLetterOption() {
    assertFalse(commandLineParser1.getLetterOption());
    assertTrue(commandLineParser2.getLetterOption());
  }

  @Test
  void getLetterTemplateFile() {
    assertEquals(commandLineParser2.getLetterTemplateFile(), "templateTest.txt");
    assertNull(commandLineParser1.getLetterTemplateFile());
  }

  @Test
  void getOutDirectoryPath() {
    assertEquals("testOutFiles",commandLineParser1.getOutDirectoryPath());

    String[] args3 ="--letter --letter-template templateTest.txt --output-dir --csv-file csvTest.csv outPutDir".split(" ");
    assertNull((new CommandLineParser(args3)).getOutDirectoryPath());
  }

  @Test
  void getCsvFile() {
    String csv = "csvTest.csv";
    assertEquals(csv, commandLineParser1.getCsvFile());

    String[] args3 ="--letter --letter-template templateTest.txt --output-dir outPutDir csvTest.csv --csv-file".split(" ");
    assertNull((new CommandLineParser(args3)).getCsvFile());
  }

  @Test
  void testEquals() {
    assertTrue(commandLineParser1.equals(commandLineParser1));
    assertFalse(commandLineParser1.equals(null));
    assertFalse(commandLineParser1.equals(commandLineParser2));

    assertFalse(commandLineParser1.equals(commandLineParser3));
    assertFalse(commandLineParser1.equals(commandLineParser1.getArgs()));

    String[] args3 ="--letter --letter-template templateTest.txt --output-dir outPutDir csvTest.csv --csv-file".split(" ");
    commandLineParser3 = new CommandLineParser(args3);
    assertFalse(commandLineParser1.equals(commandLineParser3));

    args3 = "--email --email-template templateTest.txt --output-dir testOut --csv-file csvTest.csv".split(" ");
    commandLineParser3 = new CommandLineParser(args3);
    assertFalse(commandLineParser1.equals(commandLineParser3));

    args3 = "--email --email-template template.txt --output-dir testOutFiles --csv-file csvTest.csv".split(" ");
    commandLineParser3 = new CommandLineParser(args3);
    assertFalse(commandLineParser1.equals(commandLineParser3));

    args3 = "--email --email-template templateTest.txt --output-dir testOutFiles --csv-file csvTest".split(" ");
    commandLineParser3 = new CommandLineParser(args3);
    assertFalse(commandLineParser1.equals(commandLineParser3));
  }

  @Test
  void testHashCode() {
    int ex = commandLineParser1.hashCode();
    assertEquals(ex, commandLineParser1.hashCode());
  }

  @Test
  void testToString() {
    String ex = "CommandLineParser{args=[--email, --email-template, templateTest.txt, --output-dir, testOutFiles, --csv-file, csvTest.csv], "
        + "emailOption=true, emailTemplateFile='templateTest.txt', letterOption=false, letterTemplateFile='null', "
        + "outDirectoryPath='testOutFiles', csvFile='csvTest.csv'}";
    assertEquals(ex, commandLineParser1.toString());
  }
}