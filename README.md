# KataQuiz

KataQuiz is a JavaFX desktop application that allows users to take
trivia quizzes using the Open Trivia Database API or create and export
their own custom quizzes.

This project was created as a technical submission for Direct Supply.

------------------------------------------------------------------------

## Running the Application

**Platform:** Windows

### Option 1: Executable (.exe)

1. Navigate to the **releases** section of this repository.
2. Download `KataQuiz-exe.zip`.
3. Extract the contents.
4. Open the extracted folder.
5. Double-click `KataQuiz.exe`.

> Note: Some antivirus software may block the application because the executable is unsigned.

---

### Option 2: Batch Launcher (.bat)

1. Navigate to the **releases** section of this repository.
2. Download `KataQuiz-bat.zip`.
3. Extract the contents.
4. Open the extracted folder.
5. Open the `bin` directory.
6. Double-click `KataQuiz.bat`.

> Note: This version displays console output and is recommended for debugging.


------------------------------------------------------------------------

## Project Status

This project is complete.\
No additional updates are currently being committed.

------------------------------------------------------------------------

## Technology Stack

-   Java 21
-   JavaFX 21
-   Gradle
-   IntelliJ IDEA
-   SceneBuilder
-   jpackage (for packaging)

The program was tested frequently during development. Known limitations
are listed below.

OpenAI ChatGPT was used to assist in writing documentation and this
README.

------------------------------------------------------------------------

## External Libraries

-   **Gson** -- JSON serialization/deserialization
-   **Apache Commons Text** -- HTML entity decoding
-   **JavaFX** -- User interface framework
-   **Atlantafx** -- UI styling theme
-   **jpackage** -- Native Windows packaging

------------------------------------------------------------------------

## Features

-   Take quizzes using the Open Trivia Database API
-   Select trivia category and number of questions
-   Automatic answer shuffling per question
-   Score calculation with percentage display
-   Import quizzes from JSON files
-   Create custom quizzes
-   Export quizzes to JSON format
-   Persistent answer selection when navigating between questions
- Answer validation is string-based, ensuring consistent correctness checking without additional identifier tracking.

------------------------------------------------------------------------

## Architecture Overview

The project follows a layered architecture:

-   **Controller Layer** -- Handles UI interaction
-   **Service Layer** -- Contains business logic
-   **Repository Layer** -- Manages JSON persistence
-   **Model Layer** -- Represents core data structures
-   **Utility Layer** -- Shared helper logic
-   **API Client** -- Handles communication with Open Trivia Database

A UML Class Diagram is available at:

docs/uml/uml-3.png

------------------------------------------------------------------------

## Known Issues / Limitations

-   Selecting an answer and pressing back before pressing "Next" does
    not save the answer.
-   Possible to trigger too many API requests in rapid succession.
-   "Add Question" only appends to the end of the quiz.
-   Export allows blank questions and answers.
-   Minor alignment issues in quiz radio buttons.
-   Only supports multiple choice questions (True/False supported by API
    but not implemented).
-   Titles and authors are converted to lowercase when generating
    filenames.
-   Limited handling for API response code 1 (not enough questions
    available).
-   File management logic could be moved into a utility class.
-   Category menu layout could be improved.
-   Layouts currently use VBox/HBox, AnchorPane could improve
    positioning.

------------------------------------------------------------------------

## Potential Enhancements

-   Add loading indicator during API requests
-   Implement API request rate limiting
-   Support True/False question types
-   Store quiz metadata (title/author) within the quiz model
-   Allow reordering of questions
-   Improve UI layout structure
-   Add stronger export validation

------------------------------------------------------------------------

## Author

Charles Loeffler\
Software Engineering Student\
Milwaukee School of Engineering