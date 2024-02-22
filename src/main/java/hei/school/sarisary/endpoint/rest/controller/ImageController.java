package hei.school.sarisary.endpoint.rest.controller;

import hei.school.sarisary.repository.model.Transformed;
import hei.school.sarisary.service.event.ImageService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/black-and-white")
public class ImageController {
  private final ImageService service;

  public ImageController(ImageService service) {
    this.service = service;
  }

  @PutMapping(value = "/{id}", consumes = MediaType.IMAGE_PNG_VALUE)
  @SneakyThrows
  public String createBlackAndWhiteFile(@PathVariable String id, @RequestBody byte[] image) {
    File tempFile = File.createTempFile("temp", ".png");
    Path path = Path.of(tempFile.getAbsoluteFile().toURI());
    File file = Files.write(path, image).toFile();
    boolean ignored = file.setWritable(true);
    return service.upload(file, id);
  }

  @GetMapping("/{id}")
  public Transformed getBlackAndWhiteFile(@PathVariable String id) {
    return service.getImage(id);
  }
}
