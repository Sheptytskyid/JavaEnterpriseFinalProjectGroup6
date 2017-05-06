package net.greatstart.services;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class IdenticonGeneratorService {

    private IdenticonGeneratorService() {
    }

    private static int width = 5;
    private static int height = 5;

    private static BufferedImage generateImage(String text, int imageWidth, int imageHeight) {

        byte[] hash = text.getBytes();

        BufferedImage identicon = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = identicon.getRaster();

        int[] background = new int[]{255, 255, 255, 0};
        int[] foreground = new int[]{hash[0] & 255, hash[1] & 255, hash[2] & 255, 255};

        for (int x = 0; x < width; x++) {
            int i = x < 3 ? x : 4 - x;
            for (int y = 0; y < height; y++) {
                int[] pixelColor;
                //toggle pixels based on bit being on/off
                if ((hash[i] >> y & 1) == 1) {
                    pixelColor = foreground;
                } else {
                    pixelColor = background;
                }
                raster.setPixel(x, y, pixelColor);
            }
        }

        BufferedImage finalImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        //Scale image to the size you want
        AffineTransform at = new AffineTransform();
        at.scale(imageWidth / width, imageHeight / height);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        finalImage = op.filter(identicon, finalImage);

        return finalImage;
    }

    public static byte[] generateByteImage(String text, int imageWidth, int imageHeight) {
        BufferedImage image = generateImage(text, imageWidth, imageHeight);
        byte[] photoBytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            photoBytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoBytes;
    }
}
