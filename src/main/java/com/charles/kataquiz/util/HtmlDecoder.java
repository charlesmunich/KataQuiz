/*
 * Author: Charles Loeffler
 * Last Updated: 02/12/2026
 */

package com.charles.kataquiz.util;

import org.apache.commons.text.StringEscapeUtils;

/**
 * Utility class for decoding HTML entities in text.
 */
public class HtmlDecoder {

    /**
     * Decodes HTML escape sequences in the given text.
     *
     * @param text the text containing HTML entities
     * @return the decoded text
     */
    public static String decode(String text){
        return StringEscapeUtils.unescapeHtml4(text);
    }
}
