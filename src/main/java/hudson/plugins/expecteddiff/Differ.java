package hudson.plugins.expecteddiff;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Differ {
    private static final Pattern EXPECTED_WAS_PATTERN = Pattern.compile("^junit[.]framework[.][a-zA-Z]+: expected:&lt;(.*)> but was:&lt;(.*)>",Pattern.DOTALL);

    public Diff diffy(String text) {
        String trimmed = StringUtils.trim(text);
        Matcher matcher = EXPECTED_WAS_PATTERN.matcher(trimmed);
        return matcher.lookingAt() ? extract(matcher) : null;
    }

    private Diff extract(Matcher matcher) {
        return new Diff(matcher.group(1),matcher.group(2));
    }
}
