package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regexes {
    USER_FORMAT(".*[^a-zA-z0-9].*"),
    PASS_UPPER(".*[A-Z].*"),
    PASS_LOWER(".*[a-z].*"),
    PASS_DIGIT(".*[\\d].*"),
    PASS_NO_SPACE("\\S+"),
    EMAIL_FORMAT("([^\\@\\s])+\\@([^\\.\\s])+\\.(\\S+)+");


    final String regex;

    Regexes(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , Regexes command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
