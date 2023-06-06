package utility;

import javafx.scene.paint.Color;

public class ColorConverter {
    public static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", (int)(color.getRed() * 255), (int)(color.getGreen() * 255), (int)(color.getBlue() * 255));
    }

    public static Color hexToColor(String hex) {
        hex = hex.replace("#", "");
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        return Color.rgb(r, g, b);
    }
}