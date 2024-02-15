package sary.hei.api.endpoint.rest.controller.health;

import static java.util.UUID.randomUUID;
import static sary.hei.api.endpoint.rest.controller.health.PingController.KO;
import static sary.hei.api.endpoint.rest.controller.health.PingController.OK;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sary.hei.api.PojaGenerated;
import sary.hei.api.endpoint.event.EventProducer;
import sary.hei.api.endpoint.event.gen.UuidCreated;
import sary.hei.api.repository.DummyUuidRepository;

@PojaGenerated
@RestController
@AllArgsConstructor
public class HealthEventController {

  DummyUuidRepository dummyUuidRepository;
  EventProducer eventProducer;

  @GetMapping(value = "/health/event")
  public ResponseEntity<String> random_uuid_is_fired_then_created() throws InterruptedException {
    var randomUuid = randomUUID().toString();
    var event = new UuidCreated().toBuilder().uuid(randomUuid).build();

    eventProducer.accept(List.of(event));

    Thread.sleep(20_000);
    return dummyUuidRepository.findById(randomUuid).map(dummyUuid -> OK).orElse(KO);
  }
}
