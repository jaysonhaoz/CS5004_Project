package hw8;

import java.io.IOException;

public class Main {

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
