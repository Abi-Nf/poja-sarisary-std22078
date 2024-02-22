package hei.school.sarisary.endpoint.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hei.school.sarisary.PojaGenerated;
import hei.school.sarisary.conf.FacadeIT;
import hei.school.sarisary.endpoint.rest.controller.health.HealthDbController;
import hei.school.sarisary.endpoint.rest.controller.health.PingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@PojaGenerated
class HealthControllerIT extends FacadeIT {

  @Autowired
  PingController pingController;
  @Autowired
  HealthDbController healthDbController;

  @Test
  void ping() {
    assertEquals("pong", pingController.ping());
  }

  @Test
  void can_read_from_dummy_table() {
    var responseEntity = healthDbController.dummyTable_should_not_be_empty();
    Assertions.assertEquals(PingController.OK, responseEntity);
  }
}
