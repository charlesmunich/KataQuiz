# KataQuiz
My submission for Direct Supply's Kata technical project.

# Creation
- Java + Gradle
- Created in IntelliJ Idea + Scenebuilder
- OpenAI ChatGPT was used to assist in writing final documentation and readme

# External libraries
- GSON (Json handling)
- Apache Commons Text (Json decoding)
- JavaFX (UI)
- Atlanta FX (styling)
- jpackage (program packaging)

# Future things to add
- Could add some kind of loading bar / processing indication for API requests.
- Can expand quiz size adding more API requests
- Currently only supports multiple choice questions, open trivia database also offers true/false
- Right now, quiz titles and author are saved to the quiz filename, further implementation would store data to display quiz information to the user while taking the quiz
- Allow for reordering of questions
- could add more graceful handling of Response cod 1, not enough questions for category
- would move file management to a util class
- Category selection could be more graceful and fit the screen better
- Change layouts from HBOX/VBOX to anchor panes

# Bugs/Things noticed
- Selecting an answer and pressing back before next does not save the answer, can be fixed with an onaction when radio button is pressed
- Possible to trigger too many api requests, can add protection limiting to one request per 5 seconds
- Add question only adds a question to the end, not in between
- Export allows blank question and answers
- Quiz radio buttons and text not perfectly aligned


# Notes
- Title and author get set to lowercase in createquizcontroller, as they are only used for filenames.