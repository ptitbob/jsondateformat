package org.shipstone.sandbox.jsondateformat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author François Robert
 */
@Data
@NoArgsConstructor @AllArgsConstructor
public class ToDoItem {

  private Long id;

  private String description;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyyMMdd-HHmmSS"
  )
  private LocalDateTime created;

}
