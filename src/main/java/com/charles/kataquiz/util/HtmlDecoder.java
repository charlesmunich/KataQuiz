package com.charles.kataquiz.util;

import org.apache.commons.text.StringEscapeUtils;

public class HtmlDecoder {
    public static String decode(String text){
        return StringEscapeUtils.unescapeHtml4(text);
    }
}
