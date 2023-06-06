package utility;

import java.util.Random;

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
        int a = (int)(Math.random()*(20)+1);
        return a * PI / 10;
    }

    public static int randomWindAngle() {
        Random rand = new Random();
        return rand.nextInt(31) - 15;
    }
}
