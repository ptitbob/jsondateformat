package org.shipstone.sandbox.jsondateformat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author François Robert
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class ToDoItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyyMMdd-HHmmSS"
  )
  private LocalDateTime created;

}
