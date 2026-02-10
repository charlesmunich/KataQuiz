package com.charles.kataquiz;

import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlDecoder {
    public HtmlDecoder() {
        // cannot be instantiated
    }

    public static String decode(String text){
        return StringEscapeUtils.unescapeHtml4(text);
    }
}
