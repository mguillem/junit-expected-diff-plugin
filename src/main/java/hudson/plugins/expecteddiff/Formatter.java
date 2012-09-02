package hudson.plugins.expecteddiff;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class Formatter {

    public String format(String input) {
        return input.length() > 30 ?
                formatToString(input)
                :
                input;
    }

    private String formatToString(String input) {
        boolean splittable = StringUtils.containsAny(input, ", \n\t");
        if (splittable) {
            StringBuilder buf = new StringBuilder();
            boolean quoted = false;
            Character quote = null;
            Character last = null;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (isQuote(last, c)) {
                    if(quoted) {
                        if(quote.equals(c)) {
                            quoted = !quoted;
                            quote = null;
                        }
                    } else {
                        quoted = true;
                        quote = c;
                    }
                } else {

                }
                buf.append(c);
                last = c;
            }
            return buf.toString();
        } else
            return input;

    }

    private boolean isQuote(Character last, char c) {
        String potencial = last != null ? "" + last + c : "" + c;
        return Arrays.asList("\"", "'").contains(potencial);
    }
}
