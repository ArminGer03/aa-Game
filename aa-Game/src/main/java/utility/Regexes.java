package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regexes {
    USER_FORMAT(".*[^a-zA-z0-9].*"),

    ;

    String regex;

    Regexes(String regex)
    {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , Regexes command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
