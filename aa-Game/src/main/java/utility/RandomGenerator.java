package utility;

import static java.lang.Math.PI;

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

    public static double randomAngle () {
        int a = (int)(Math.random()*(20-0)+1);
        return a * PI / 10;
    }
}
