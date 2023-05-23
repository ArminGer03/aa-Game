package utility;

public class RandomGenerator {
    public static String randomImagePath()
    {
        String[] paths =
                {
                        "/images/pic1.jpeg",
                        "/images/pic2.jpeg",
                        "/images/pic3.jpeg",
                        "/images/pic4.jpeg"
                };

        return paths[randomNumber(0, paths.length - 1)];
    }

    public static int randomNumber(int min, int max)
    {
        return (int)(Math.random()*(max-min+1)+min);
    }
}
