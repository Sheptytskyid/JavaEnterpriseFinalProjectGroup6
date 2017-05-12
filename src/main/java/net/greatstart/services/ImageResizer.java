package net.greatstart.services;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.resizers.Resizers;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

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
            BufferedImage resize = thumbnailatorImage(file);
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
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(file.getBytes());
            return ImageIO.read(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }


    private static BufferedImage thumbnailatorImage(MultipartFile file) throws IOException {
        BufferedImage image = Thumbnails.of(convertoImage(file))
                .sourceRegion(Positions.CENTER, 3200, 3200)
                .size(1200, 1200)
                .resizer(Resizers.BICUBIC).asBufferedImage();
        return image;
    }
}
