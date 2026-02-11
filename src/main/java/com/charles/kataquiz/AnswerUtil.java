package com.charles.kataquiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerUtil {
    public AnswerUtil (){

    }

    public static List<String> combineAndShuffle(Question question){
        List<String> answers = new ArrayList<String>();

        answers.add(question.getCorrectAnswer());

        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);

        return answers;
    }
}
