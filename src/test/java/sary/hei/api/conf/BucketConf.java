package sary.hei.api.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import sary.hei.api.PojaGenerated;

@PojaGenerated
public class BucketConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.s3.bucket", () -> "dummy-bucket");
  }
}
