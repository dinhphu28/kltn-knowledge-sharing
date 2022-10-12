package com.ndp.knowsharing.Utils.UriParser;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class MyUriParserUtils {
    private String resultDateTimeString() {
        String resultStr = "";

        resultStr = "" + LocalDate.now() + LocalTime.now();

        resultStr = resultStr.replace(" ", "");
        resultStr = resultStr.replace(":", "");
        resultStr = resultStr.replace(".", "");
        resultStr = resultStr.replace("-", "");

        return resultStr;
    }

    private String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    private String execSpaceChar(String s) {
        String temp = "";
        temp = s.replace("?", "");
        temp = temp.replace(' ', '-');
        temp = temp.replace(" ", "-");
        temp = temp.replace("--", "-");
        temp = temp.replace("đ", "d");
        temp = temp.replace("Đ", "d");
        temp = temp.replace("\"", "");
        return temp;
    }

    public String getFinalArticleUrl(String s) {
        String temp = "";

        temp = removeAccent(s + "-" + resultDateTimeString());
        temp = execSpaceChar(temp);
        temp = temp.toLowerCase();

        return temp;
    }
    
}
