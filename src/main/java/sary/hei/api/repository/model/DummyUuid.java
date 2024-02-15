package sary.hei.api.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import sary.hei.api.PojaGenerated;

@PojaGenerated
@Entity
@Getter
@Setter
public class DummyUuid {
  @Id private String id;
}
