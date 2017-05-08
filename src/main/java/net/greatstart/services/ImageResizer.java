package net.greatstart.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ImageResizer {

    public static byte[] getImage(MultipartFile file) throws IOException {
        log.info("Start to resize");
        byte[] result = null;
        BufferedImage image = convertoImage(file);
        log.info("img width:" + image.getWidth());
        log.info("img height:" + image.getHeight());
        int type;
        if (image.getType() == 0) {
            type = BufferedImage.TYPE_INT_ARGB;
        } else {
            log.info("Getting image type...");
            type = image.getType();
        }
        try {
            log.info("Start to resizing ...");
            BufferedImage resize = resizeImage(image,type,620,620);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resize, "png", baos);
            baos.flush();
            result = baos.toByteArray();
            log.info("Resizing complete");
            log.info("img width:" + resize.getWidth());
            log.info("img height:" + resize.getHeight());
        } catch (Exception e) {
            log.error("Error resizing file", e);
        }
        return result;


    }

    private static BufferedImage resizeImage(BufferedImage original, int type, int width, int heigth) {
        BufferedImage resizedImage = new BufferedImage(width, heigth, type);
        Graphics graphics = resizedImage.createGraphics();
        graphics.drawImage(original, 0, 0, width, heigth, null);
        graphics.dispose();
        return resizedImage;
    }

    private static BufferedImage convertoImage(MultipartFile file) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        return ImageIO.read(in);
    }

//    private static BufferedImage resizeImagePercent(BufferedImage original, int type, double percent) {
//        int scaledWidth = (int) (original.getWidth() * percent);
//        int scaledHeigth = (int) (original.getHeight() * percent);
//        return resizeImage(original, type, scaledWidth, scaledHeigth);
//    }
}
