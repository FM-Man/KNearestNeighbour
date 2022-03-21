import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
/*        for(int i=1;i<11;i++){
//            BufferedImage img = ImageIO.read(new File("village\\"+i+".jpg"));
//            img = resizeImage(img, 500,500);
//            File output = new File("villageTraining\\"+i+".png");
//            ImageIO.write(img, "png", output);
//        }
*/
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
            double[] distances = new double[18];
            int[] type = new int[18];

            int count = 0;
            for (int j =0; j<10; j++)
                if(i!=j){
                    distances[count] = distance(cityRGB[j], cityRGB[i] );
                    type[count] = 0;
                    count++;
                    distances[count] = distance(villageRGB[j], cityRGB[i] );
                    type[count] = 1;
                    count++;
                }

            for(int k=0; k<18;k++)
                for(int l=k+1; l<18; l++)
                    if(distances[k] > distances[l]){
                        double temp = distances[k];
                        distances[k] = distances[l];
                        distances[l] = temp;

                        int tType = type[k];
                        type[k] = type[l];
                        type[l] = tType;
                    }

            int[] knn = new int[2];
            for(int k=0; k<1;k++)
                knn[type[k]]++;

            if(knn[0] > knn[1]) {
                System.out.println("city "+i+" correct");
                cityCorrect++;
            } else System.out.println("wrong");
        }

        //village testing
        for(int i=0; i<10;i++){
            double[] distances = new double[18];
            int[] type = new int[18];

            int count = 0;
            for (int j =0; j<10; j++)
                if(i!=j){
                    distances[count] = distance(cityRGB[j], villageRGB[i] );
                    type[count] = 0;
                    count++;
                    distances[count] = distance(villageRGB[j], villageRGB[i] );
                    type[count] = 1;
                    count++;
                }

            for(int k=0; k<18;k++)
                for(int l=k+1; l<18; l++)
                    if(distances[k] > distances[l]){
                        double temp = distances[k];
                        distances[k] = distances[l];
                        distances[l] = temp;

                        int tType = type[k];
                        type[k] = type[l];
                        type[l] = tType;
                    }

            int[] knn = new int[2];
            for(int k=0; k<3;k++)
                knn[type[k]]++;

            if(knn[0] < knn[1]) {
                System.out.println("village "+i+" correct");
                villageCorrect++;
            } else System.out.println("wrong");
        }

        System.out.println("city: "+cityCorrect+"\nvillage: "+villageCorrect);
    }

    private static double distance(int[] a, int[] b){
        double result = 0;
        for (int i=0;i<a.length;i++){
            result+=Math.pow((a[i]-b[i]),2);
        }
        return Math.sqrt(result);
    }

//    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth){
//        int targetHeight =(targetWidth* originalImage.getHeight())/originalImage.getWidth();
//        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics2D = resizedImage.createGraphics();
//        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
//        graphics2D.dispose();
//        return resizedImage;
//    }
}
