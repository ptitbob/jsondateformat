package org.shipstone.sandbox.jsondateformat.web;

import org.shipstone.sandbox.jsondateformat.domain.ToDoItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author François Robert
 */
@RestController
@RequestMapping(value = "todo-items", produces = {APPLICATION_JSON_VALUE})
public class ToDoItemEndpoint {

  @GetMapping("{id}")
  public ToDoItem getTodoItemById(
    @PathVariable("id") Long id
  ) {
    return new ToDoItem(id, "Description de l'item " + id, LocalDateTime.now());
  }

}
