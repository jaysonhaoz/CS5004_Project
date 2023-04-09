package hw8;


import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class CommandLineParser {

    private static final String EMAIL = "--email";
    private static final String LETTER = "--letter";
    private static final String EMAIL_TEMPLATE = "--email-template";
    private static final String LETTER_TEMPLATE = "--letter-template";
    private static final String OUTPUT_DIR = "--output-dir";
    private static final String CSV_FILE = "--csv-file";
    private static final Set<String> setOfCommands = Set.of(EMAIL, EMAIL_TEMPLATE, LETTER,
        LETTER_TEMPLATE, OUTPUT_DIR, CSV_FILE);
    private static final int MIN_LENGTH = 7;
    private static final int MAX_LENGTH = 10;
    private static final String HELP_MESSAGE = " ";
    private String[] args;
    private Boolean emailOption = Boolean.FALSE;
    private String emailTemplateFile;
    private Boolean letterOption = Boolean.FALSE;
    private String letterTemplateFile;
    private String outDirectoryPath;
    private String csvFile;

    public CommandLineParser(String[] inStrings) {
        if (this.checkLength(inStrings)) {
            this.args = inStrings;
            if (this.checkEmailOption())
                this.emailOption = Boolean.TRUE;
            if (this.checkLetterOption())
                this.letterOption = Boolean.TRUE;
            if (!this.letterOption && !this.emailOption) {
                throw new IllegalCommandLineArgumentsException("--email or --letter command needed!");
            } else if (this.checkCSVFile() && this.checkOutputOption()) {
                System.out.println("Successfully processed command line.");
            } else {
                System.out.println(HELP_MESSAGE);
            }
        }
    }

    private Boolean checkLength(String[] args) {
        if (args.length == MIN_LENGTH || args.length == MAX_LENGTH)
            return Boolean.TRUE;
        else {
            System.err.println("Invalid command line length!");
            throw new IllegalCommandLineArgumentsException("Error command line args!");
        }
    }

    private Boolean checkEmailOption() {
        for (String arg : this.args) {
            if (arg.equals(EMAIL)) {
                for (int j = 0; j < this.args.length; j++) {
                    if (this.args[j].equals(EMAIL_TEMPLATE)
                        && j + 1 < args.length && !setOfCommands.contains(args[j + 1])) {
                        this.emailTemplateFile = this.args[j+1];
                        return Boolean.TRUE;
                    }
                }
                System.err.println("Error: --email provided but no --email-template was given.");
            }
        }
        return Boolean.FALSE;
    }

    private Boolean checkLetterOption() {
        for (String arg : this.args) {
            if (arg.equals(LETTER)) {
                for (int j = 0; j < this.args.length; j++) {
                    if (this.args[j].equals(LETTER_TEMPLATE)
                        && j + 1 < args.length && !setOfCommands.contains(args[j + 1])) {
                        this.letterTemplateFile = args[j+1];
                        return Boolean.TRUE;
                    }
                }
                System.err.println("Error: --letter provided but no --letter-template was given.");
            }
        }
        return Boolean.FALSE;
    }

    private Boolean checkOutputOption() {
        for (int i = 0; i < this.args.length; i++) {
            if (this.args[i].equals(OUTPUT_DIR)) {
                if (i + 1 < args.length && !setOfCommands.contains(args[i + 1])) {
                    this.outDirectoryPath = args[i+1];
                    return Boolean.TRUE;
                }
            }
        }
        System.err.println("ERROR: No --output- command!");
        return Boolean.FALSE;
    }

    private Boolean checkCSVFile() {
        for (int i = 0; i < this.args.length; i++) {
            if (this.args[i].equals(CSV_FILE)) {
                if (i + 1 < args.length && !setOfCommands.contains(args[i + 1])) {
                    this.csvFile = args[i+1];
                    return Boolean.TRUE;
                }
            }
        }
        System.out.println("ERROR: No --csv-file command!");
        return Boolean.FALSE;
    }

    public String[] getArgs() {
        return args;
    }

    public Boolean getEmailOption() {
        return emailOption;
    }

    public String getEmailTemplateFile() {
        return emailTemplateFile;
    }

    public Boolean getLetterOption() {
        return letterOption;
    }

    public String getLetterTemplateFile() {
        return letterTemplateFile;
    }

    public String getOutDirectoryPath() {
        return outDirectoryPath;
    }

    public String getCsvFile() {
        return csvFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandLineParser that = (CommandLineParser) o;
        return Arrays.equals(args, that.args) && Objects.equals(emailOption,
            that.emailOption) && Objects.equals(emailTemplateFile, that.emailTemplateFile)
            && Objects.equals(letterOption, that.letterOption) && Objects.equals(
            letterTemplateFile, that.letterTemplateFile) && Objects.equals(outDirectoryPath,
            that.outDirectoryPath) && Objects.equals(csvFile, that.csvFile);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(emailOption, emailTemplateFile, letterOption, letterTemplateFile,
            outDirectoryPath, csvFile);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    @Override
    public String toString() {
        return "CommandLineParser{" +
            "args=" + Arrays.toString(args) +
            ", emailOption=" + emailOption +
            ", emailTemplateFile='" + emailTemplateFile + '\'' +
            ", letterOption=" + letterOption +
            ", letterTemplateFile='" + letterTemplateFile + '\'' +
            ", outDirectoryPath='" + outDirectoryPath + '\'' +
            ", csvFile='" + csvFile + '\'' +
            '}';
    }
}
