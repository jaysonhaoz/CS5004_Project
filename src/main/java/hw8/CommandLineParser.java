package hw8;


import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * The CommandLineParser class is responsible for processing and validating command line arguments.
 * It checks the validity of the input options and stores the values for later use. The class
 * supports the following options: --email, --letter, --email-template, --letter-template, --output-dir,
 * and --csv-file.
 */
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

    /**
     * Constructor for the CommandLineParser class. It takes an array of input command line arguments,
     * validates and processes them. The constructor sets the appropriate instance variables based on the
     * provided options.
     * If the input arguments do not satisfy the required conditions, it displays an error message or help message.
     * @param inStrings the input command line arguments
     */
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

    /**
     * This private method checks whether the length of the input command line arguments is within the
     *  allowed range (MIN_LENGTH or MAX_LENGTH).
     *  If the length is not within the allowed range, it prints an error message and throws an
     *  IllegalCommandLineArgumentsException.
     * @param args the input command line arguments as a string array
     * @return true if the length is within the allowed range, false otherwise
     * @throws IllegalCommandLineArgumentsException if the command line argument length is invalid
     */
    private Boolean checkLength(String[] args) {
        if (args.length == MIN_LENGTH || args.length == MAX_LENGTH)
            return Boolean.TRUE;
        else {
            System.err.println("Invalid command line length!");
            throw new IllegalCommandLineArgumentsException("Error command line args!");
        }
    }

    /**
     * This private method checks if the --email option is provided and if so, looks for the corresponding
     * --email-template option. It sets the emailTemplateFile instance variable with the provided file path
     * if both options are found and valid.
     * If the --email option is provided without a valid --email-template, it prints an error message.
     *
     * @return true if the --email option is provided with a valid --email-template, false otherwise
     */
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

    /**
     * This private method checks if the --letter option is provided and if so, looks for the corresponding
     * --letter-template option. It sets the letterTemplateFile instance variable with the provided file path
     * if both options are found and valid.
     * If the --letter option is provided without a valid --letter-template, it prints an error message.
     * @return true if the --letter option is provided with a valid --letter-template, false otherwise
     */
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

    /**
     * This private method checks if the --output-dir option is provided and if so, looks for the corresponding
     * output directory path. It sets the outDirectoryPath instance variable with the provided path
     * if the option is found and valid.
     * If the --output-dir option is provided without a valid output directory path, it prints an error message.
     * @return true if the --output-dir option is provided with a valid output directory path, false otherwise
     */
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

    /**
     * This private method checks if the --csv-file option is provided and if so, looks for the corresponding
     * CSV file path. It sets the csvFile instance variable with the provided file path if the option is found
     * and valid.
     * If the --csv-file option is provided without a valid CSV file path, it prints an error message.
     * @return true if the --csv-file option is provided with a valid CSV file path, false otherwise
     */
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

    /**
     * This public method returns the command line arguments as a string array.
     * @return the command line arguments as a String array
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * This public method returns the email option status as a boolean value.
     * @return true if the --email option is provided and valid, false otherwise
     */
    public Boolean getEmailOption() {
        return emailOption;
    }

    /**
     * This public method returns the email template file path as a string.
     * @return the email template file path, or null if the --email-template option is not provided or invalid
     */
    public String getEmailTemplateFile() {
        return emailTemplateFile;
    }

    /**
     * This public method returns the letter option status as a boolean value.
     * @return true if the --letter option is provided and valid, false otherwise
     */
    public Boolean getLetterOption() {
        return letterOption;
    }

    /**
     * This public method returns the letter template file path as a string.
     * @return the letter template file path, or null if the --letter-template option is not provided or invalid
     */
    public String getLetterTemplateFile() {
        return letterTemplateFile;
    }

    /**
     * This public method returns the output directory path as a string.
     * @return the output directory path, or null if the --output-dir option is not provided or invalid
     */
    public String getOutDirectoryPath() {
        return outDirectoryPath;
    }

    /**
     * This public method returns the CSV file path as a string.
     * @return the CSV file path, or null if the --csv-file option is not provided or invalid
     */
    public String getCsvFile() {
        return csvFile;
    }

    /**
     * Determines if two CommandLineParser objects are equal.
     * @param o the object to compare with the current instance
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
        CommandLineParser that = (CommandLineParser) o;
        return Arrays.equals(args, that.args) && Objects.equals(emailOption,
            that.emailOption) && Objects.equals(emailTemplateFile, that.emailTemplateFile)
            && Objects.equals(letterOption, that.letterOption) && Objects.equals(
            letterTemplateFile, that.letterTemplateFile) && Objects.equals(outDirectoryPath,
            that.outDirectoryPath) && Objects.equals(csvFile, that.csvFile);
    }

    /**
     * @return hash val
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(emailOption, emailTemplateFile, letterOption, letterTemplateFile,
            outDirectoryPath, csvFile);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    /**
     * @return a string
     */
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
