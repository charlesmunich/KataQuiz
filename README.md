# KataQuiz
My submission for Direct Supply's Kata technical project.

# Notes
- Could add some kind of loading bar / processing indication for API requests.
- Can expand quiz size by adding pagination to API requests.
- Currently only supports multiple choice questions, open trivia database also offers true/false
- Selecting an answer and pressing back before next does not save the answer, can be fixed with an onaction when radio button is pressed
- Right now, quiz titles and author are saved to the quiz filename, further implementation would store data to display quiz information to the user while taking the quiz

# TODO
- Major UI refactoring
- CSS stylesheet
- Test edge cases
- Fix accessing window when chooser is open