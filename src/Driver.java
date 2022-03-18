import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
//        BufferedImage ogimg = ImageIO.read(new File("img.jpg"));
//        ogimg = resizeImage(ogimg, 300);
//        File outputFile = new File("outputFile.png");
//        ImageIO.write(ogimg, "png", outputFile);
//
//        for(int i=1;i<11;i++){
//            BufferedImage img = ImageIO.read(new File("village\\"+i+".jpg"));
//            img = resizeImage(img, 500,500);
//            File output = new File("villageTraining\\"+i+".png");
//            ImageIO.write(img, "png", output);
//        }

        
    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth){
        int targetHeight =(targetWidth* originalImage.getHeight())/originalImage.getWidth();
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
