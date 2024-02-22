package hei.school.sarisary.service.event;

import hei.school.sarisary.file.BucketComponent;
import hei.school.sarisary.repository.model.Transformed;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
  private final BucketComponent bucket;

  public ImageService(BucketComponent bucket) {
    this.bucket = bucket;
  }

  private static final String ORIGINAL = "-original";
  private static final String BW = "-bw-image";

  @SneakyThrows
  public String upload(File file, String id) {
    if (file != null) {
      bucket.upload(blackAndWhite(file), id + BW);
      bucket.upload(file, id + ORIGINAL);
      return null;
    }
    return null;
  }

  public Transformed getImage(String id) {
    Transformed file = new Transformed();
    file.setOriginal_url(bucket.presign(id + ORIGINAL, Duration.ofHours(4)).toString());
    file.setTransformed_url(bucket.presign(id + BW, Duration.ofHours(4)).toString());
    return file;
  }

  @SneakyThrows
  private File blackAndWhite(File file) {
    if (file != null) {
      BufferedImage image = ImageIO.read(file);
      BufferedImage blackAndWhiteImg =
          new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
      Graphics2D graphics = blackAndWhiteImg.createGraphics();
      graphics.drawImage(image, 0, 0, null);
      graphics.dispose();

      File res = new File("output.png");
      ImageIO.write(image, "png", res);
      return res;
    }
    return null;
  }
}
