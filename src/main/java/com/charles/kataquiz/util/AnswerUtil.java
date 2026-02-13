/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.util;

import com.charles.kataquiz.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for working with quiz answers.
 */
public class AnswerUtil {

    /**
     * Combines the correct and incorrect answers for a question
     * and shuffles them into a random order.
     *
     * @param question the question containing answer choices
     * @return a shuffled list of all possible answers
     */
    public static List<String> combineAndShuffle(Question question){
        List<String> answers = new ArrayList<>();

        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);

        return answers;
    }
}
