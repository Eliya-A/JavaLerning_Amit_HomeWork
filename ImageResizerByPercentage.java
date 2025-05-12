package ImagesResizer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResizerByPercentage {

    public static void resizeImagesByPercentage(String folderPath, double percentage) {
        File folder = new File(folderPath);

        for (File file : folder.listFiles()) {
            String name = file.getName().toLowerCase();
            if (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png")) {
                try {
                    // קריאת התמונה המקורית
                    BufferedImage originalImage = ImageIO.read(file);
                    int originalWidth = originalImage.getWidth();
                    int originalHeight = originalImage.getHeight();

                    // חישוב גודל חדש לפי אחוז
                    int newWidth = (int) (originalWidth * percentage);
                    int newHeight = (int) (originalHeight * percentage);

                    // יצירת תמונה חדשה מוקטנת
                    Image resized = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
                    Graphics2D g2d = resizedImage.createGraphics();
                    g2d.drawImage(resized, 0, 0, null);
                    g2d.dispose();

                    // דריסת הקובץ המקורי
                    ImageIO.write(resizedImage, getFileExtension(file), file);

                    System.out.println("✔ " + file.getName() + " הוקטנה ל-" + (int)(percentage * 100) + "%.");

                } catch (IOException e) {
                    System.out.println("שגיאה בעיבוד הקובץ: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastDot = name.lastIndexOf('.');
        return (lastDot == -1) ? "" : name.substring(lastDot + 1);
    }

    public static void main(String[] args) {
        // שנה את הנתיב לתיקיית התמונות שלך
        resizeImagesByPercentage("C:\\Java_Learning\\Codes\\Test_1\\Images_Tester\\OriginalImages", 0.5);  // 50%
    }
}
