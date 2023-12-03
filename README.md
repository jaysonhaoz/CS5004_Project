# 🛠️ Java Command Line Applications


This repository contains two Java command line applications that demonstrate my expertise in Java programming and an understanding of object-oriented design principles. These projects, completed as part of a course on Object-Oriented Design, allowed me to lead a dedicated group of three. My pivotal role in driving the projects to completion involved significant contributions to the work. The projects reflect not only our technical skills but also our strong communication, collaboration, and collective growth.

---

## Projects Overview


### Project 1: 📧 Java Email Template Processor

#### Project Description

The application parses a CSV file containing recipient details and a text file containing an email template. It then fills the template with individual information from the CSV to generate personalized emails for each recipient. The application is designed with extensibility and modularity in mind, following best practices in Java development. It showcases extensive use of Java I/O, exception handling, and command line parsing to create a robust and user-friendly application.

#### Features

- **CsvParser**: Parses CSV files and stores information for template processing.
- **TemplateProcessor**: Processes templates by replacing placeholders with actual data.
- **CommandLineParser**: Handles command line arguments to provide a dynamic user interface.
- **Exception Handling**: Custom exception for robust error management.
- **UML-Driven Design**: Follows a UML diagram for clear and organized object-oriented design.

#### Usage

The application expects a CSV file for recipient details and a template text file. Command line arguments are used to specify file paths and options for processing.
After compiling the application, you can run it using command line arguments to specify the email template, the output directory, and the CSV file containing the email data. 

Here is an example of how to run the application:

`java Main --email --email-template templateTest.txt --output-dir testOutFiles --csv-file dataTest.csv`

This will instruct the application to generate emails using `templateTest.txt` as the template and `dataTest.csv` for the email data, outputting the results to the `testOutFiles` directory.

#### Testing

The application includes a suite of JUnit tests that provide full coverage of its functionality. These tests ensure that each component of the application behaves as expected and that they work together seamlessly.

Key test cases include:
- Parsing command line arguments with different flags and inputs.
- Handling invalid arguments and triggering appropriate exceptions.
- Generating emails and letters with correct template data substitution.
- Ensuring that output files are correctly created in the specified directory.

Each test case is documented with comments explaining its purpose, which helps maintain the tests and understand their scope at a glance.

---

### Project 2: 🎭 Theater Seat Reservation System

#### Project Description

This application is a command-line based Theater Seat Reservation System designed following the Model-View-Controller (MVC) architecture. It showcases Object-Oriented Design (OOD) principles, creating a user-friendly interface that allows interaction through the command line. The application displays seat arrangements and allows users to reserve seats interactively.

#### 🔍 Features

- **Theater Model**: Represents the theater with rows and seats, tracking reservations and accessibility features.
- **Row Iterator**: A custom iterator that calculates the best available seats based on proximity to the center of the theater, optimizing the user's viewing experience by suggesting middle seats first, then front and rear.
- **Viewer**: Command line UI that displays the current state of seat reservations.
- **Reservation Service**: Manages the reservation logic, allowing seats to be reserved and checked.
- **JUnit Tests Covered**: The application is fully covered with JUnit tests, ensuring reliability and robust error handling.


#### Usage

To interact with the Theater Seat Reservation System:

1. Compile and run the `ReservationSystem` class.
2. Following example commands are available when interating with the application
   - `show` - Displays the current seating layout.
   - `reserve [number_of_seats]` - Initiates the reservation process for the specified number of seats.
   - `done` - Exits the application.

For reservations, follow the on-screen prompts to enter your name and specify if wheelchair-accessible seating is required.

#### Testing

Run the test classes to verify the system's reservation functionality and error handling.

---

## Acknowledgments and Team Experience

I would like to express my profound gratitude to my teammates, who were instrumental in the success of these projects. Their dedication and input were invaluable, and our collaboration was a significant learning experience. Together, we navigated complex challenges and achieved our goals through clear communication and a shared commitment to quality.

I also extend my heartfelt thanks to our professors, whose guidance and expertise in the field of object-oriented design were pivotal to our success. Their teachings not only enriched our technical knowledge but also emphasized the importance of the "food principle" in software development—a principle that advocates for mindful and purposeful coding practices.

The success of these projects is a testament to the power of teamwork and the deep understanding that can be achieved through cooperative learning and mutual support.

