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

        int[][] cityRGB = new int[10][500*500];
        int[][] villageRGB = new int[10][500*500];

        for (int i=1; i<11;i++){
            BufferedImage city = ImageIO.read(new File("cityTraining\\"+i+".png"));
            BufferedImage village = ImageIO.read(new File("villageTraining\\"+i+".png"));
            cityRGB[i-1] = city.getRGB(0,0,500,500,null,0,500);
            villageRGB[i-1] = village.getRGB(0,0,500,500,null,0,500);
        }
        int cityCorrect = 0;
        int villageCorrect = 0;
        //city testing
        for(int i=0; i<10;i++){
            double averageCity = 0;
            double averageVillage = 0;

            for (int j =0; j<10; j++){
                if(i!=j){
                    averageCity += distance(cityRGB[j], cityRGB[i] );
                    averageVillage += distance(villageRGB[j], cityRGB[i] );
                }
            }
            averageCity /= 9.0;
            averageVillage/= 9.0;

            if(averageCity < averageVillage) {
                System.out.println("city "+i+" correct");
                cityCorrect++;
            }
            else System.out.println("wrong");
        }

        //village testing
        for(int i=0; i<10;i++){
            double averageCity = 0;
            double averageVillage = 0;

            for (int j =0; j<10; j++){
                if(i!=j){
                    averageCity += distance(cityRGB[j], villageRGB[i] );
                    averageVillage += distance(villageRGB[j],villageRGB[i] );
                }
            }
            averageCity /= 9.0;
            averageVillage/= 9.0;

            if(averageCity > averageVillage) {
                System.out.println("village "+i+" correct");
                villageCorrect++;
            }
            else System.out.println("wrong");
        }

        System.out.println("city: "+cityCorrect+"\nvillage: "+villageCorrect);

    }

    private static double distance(int[] a, int[] b){
        double result = 0;
        for (int i=0;i<a.length;i++){
            result+=Math.pow((a[i]-b[i]),2);
        }
        result = Math.sqrt(result);
        return result;
    }








//    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
//        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics2D = resizedImage.createGraphics();
//        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
//        graphics2D.dispose();
//        return resizedImage;
//    }
//    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth){
//        int targetHeight =(targetWidth* originalImage.getHeight())/originalImage.getWidth();
//        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics2D = resizedImage.createGraphics();
//        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
//        graphics2D.dispose();
//        return resizedImage;
//    }
}
