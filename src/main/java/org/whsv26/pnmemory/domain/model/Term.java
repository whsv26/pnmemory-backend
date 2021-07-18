package org.whsv26.pnmemory.domain.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "terms")
public class Term {
  @Id
  @GeneratedValue
  private UUID id;

  private String title;

  private String body;
}
