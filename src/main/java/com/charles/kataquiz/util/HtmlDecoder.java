package com.charles.kataquiz.util;

import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlDecoder {
    public static String decode(String text){
        return StringEscapeUtils.unescapeHtml4(text); //TODO is depracated
    }
}
