package hw8;

/**
 * Custom exception class for handling illegal command line arguments.
 * This exception is thrown when the command line arguments provided by the user are not valid or as expected.
 */
public class IllegalCommandLineArgumentsException extends RuntimeException {

  /**
   * Constructor for the IllegalCommandLineArgumentsException class.
   * @param s A string representing the error message to be displayed when the exception is thrown.
   */
  public IllegalCommandLineArgumentsException(String s) {
  }
}
