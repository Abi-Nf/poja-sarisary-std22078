package hei.school.sarisary.endpoint.rest.controller;

import hei.school.sarisary.repository.model.Transformed;
import hei.school.sarisary.service.event.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@CrossOrigin("*")
@RequestMapping("/black-and-white")
public class ImageController {
  private final ImageService service;

  public ImageController(ImageService service){
    this.service = service;
  }


  @PutMapping(value = "/{id}", consumes = MediaType.IMAGE_PNG_VALUE)
  public String createBlackAndWhiteFile(
    @PathVariable String id,
    @RequestPart File image
  )
  {
    return service.upload(image, id);
  }

  @GetMapping("/{id}")
  public Transformed getBlackAndWhiteFile(
      @PathVariable String id
  ){
    return service.getImage(id);
  }
}
