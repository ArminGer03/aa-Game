package utility;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RandomGenerator {
    public static String randomImagePath(int n)
    {
        String[] paths =
                {
                        "/images/pic1.jpeg",
                        "/images/pic2.jpeg",
                        "/images/pic3.jpeg",
                        "/images/pic4.jpeg"
                };
        return paths[n];
    }

    public static int randomNumber(int min, int max)
    {
        return (int)(Math.random()*(max-min+1)+min);
    }
}
