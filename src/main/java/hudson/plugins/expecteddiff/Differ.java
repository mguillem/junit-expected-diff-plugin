package hudson.plugins.expecteddiff;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Differ {
    private static final Pattern EXPECTED_WAS_PATTERN = Pattern.compile("expected:&lt;(.*)> but was:&lt;(.*)>");

    public Diff diffy(String text) {
        String trimmed = StringUtils.trim(text);
        Matcher matcher = EXPECTED_WAS_PATTERN.matcher(trimmed);
        return matcher.matches() ? extract(matcher) : null;
    }

    private Diff extract(Matcher matcher) {
        return new Diff(matcher.group(1),matcher.group(2));
    }
}
