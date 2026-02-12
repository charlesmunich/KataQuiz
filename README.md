# KataQuiz
My submission for Direct Supply's Kata technical project.

# Notes
- Could add some kind of loading bar / processing indication for API requests.
- Can expand quiz size adding more API requests
- Possible to trigger too many api requests, can add protection limiting to one request per 5 seconds
- Currently only supports multiple choice questions, open trivia database also offers true/false
- Selecting an answer and pressing back before next does not save the answer, can be fixed with an onaction when radio button is pressed
- Right now, quiz titles and author are saved to the quiz filename, further implementation would store data to display quiz information to the user while taking the quiz
- Title and author get set to lowercase in createquizcontroller, as they are only used for filenames.
- OpenAI ChatGPT was used to assist in writing final documentation and readme
- Add question only adds a question to the end, remove question only removed the last question.
- Export allows blank question and answers
- could add more graceful handling of Response cod 1, not enough questions for category
- would move file management to a util class

# TODO
- Major UI refactoring
- javadocs
- CSS stylesheet
- Final edge case test
- jpackage (to exe)

# UI
- Font: Inter
- Main Title: 60px
- Main Buttons: 20px
- Secondary Titles: 40px
- Secondary Buttons / Text 18px

# What I would have done differently
- Anchor pane instead of VBOX/HBOX
- 