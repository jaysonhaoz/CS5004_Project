package hw8;

import java.io.IOException;

/**
 * Main class that demonstrates the usage of the CsvParser, CommandLineParser, and TemplateProcessor classes.
 * This class reads command line arguments, processes the input CSV file, and generates emails or letters using
 * the provided templates.
 */
public class Main {

  /**
   * Main method of the application.
   * Reads command line arguments, initializes CsvParser and CommandLineParser objects, and processes the input CSV file.
   * If the email option is set, generates emails using the provided email template.
   * If the letter option is set, generates letters using the provided letter template.
   * @param args The command line arguments provided by the user.
   * @throws IOException If there is an error reading the input files.
   */
  public static void main(String[] args) throws IOException {
    CommandLineParser cmdParser = new CommandLineParser(args);
    CsvParser csvParser = new CsvParser(cmdParser.getCsvFile());
    if (cmdParser.getEmailOption()) {
      TemplateProcessor templateProcessor = new TemplateProcessor(cmdParser.getEmailTemplateFile(), csvParser);
      templateProcessor.writeAllFiles(csvParser.getMapList(), cmdParser.getOutDirectoryPath());
    }
    if (cmdParser.getLetterOption()) {
      TemplateProcessor templateProcessor = new TemplateProcessor(cmdParser.getLetterTemplateFile(), csvParser);
      templateProcessor.writeAllFiles(csvParser.getMapList(), cmdParser.getOutDirectoryPath());
    }
  }
}
