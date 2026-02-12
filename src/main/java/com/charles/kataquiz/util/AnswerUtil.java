package com.charles.kataquiz.util;

import com.charles.kataquiz.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerUtil {
    public static List<String> combineAndShuffle(Question question){
        List<String> answers = new ArrayList<>();

        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);

        return answers;
    }
}
