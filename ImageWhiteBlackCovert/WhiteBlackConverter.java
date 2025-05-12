package ImageWhiteBlackCovert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class WhiteBlackConverter {

    public static void main(String[] args) {
        File folder = new File("C:\\Java_Learning\\Codes\\Test_1\\Images_Tester\\OriginalImages");  // שנה לנתיב שלך
        File outputFolder = new File("C:\\Java_Learning\\Codes\\Test_1\\Images_Tester\\White&BlackImages"); // יעד לשמירת תמונות שחור לבן

        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("התיקיה ריקה או לא קיימת.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && isImage(file)) {
                try {
                    BufferedImage original = ImageIO.read(file);
                    BufferedImage gray = toGrayscale(original);

                    File output = new File(outputFolder, file.getName());
                    ImageIO.write(gray, "jpg", output);
                    System.out.println("נשמר: " + output.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("שגיאה בקובץ: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    // הופך תמונה לשחור לבן
    public static BufferedImage toGrayscale(BufferedImage img) {
        BufferedImage grayImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color color = new Color(img.getRGB(x, y));
                int grayLevel = (int)(0.3 * color.getRed() + 0.59 * color.getGreen() + 0.11 * color.getBlue());
                Color grayColor = new Color(grayLevel, grayLevel, grayLevel);
                grayImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        return grayImage;
    }

    // בודק אם הקובץ הוא תמונה
    public static boolean isImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }
}
