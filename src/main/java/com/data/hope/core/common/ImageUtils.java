

package com.data.hope.core.common;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {


    public static void resize(String src, Double toWith, Double toHeight) throws Exception {
        if (toWith == null && toHeight == null) {
            return;
        }
        BufferedImage srcImage = ImageIO.read(new File(src));
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        double factor = 1.0;
        if (toHeight != null) {
            factor = toHeight / height;
        } else if (toWith != null) {
            factor = toWith / width;
        }

        Thumbnails.of(src)
                .scale(factor)
                .outputQuality(1)
                .allowOverwrite(true)
                .toFile(src);

    }

}
