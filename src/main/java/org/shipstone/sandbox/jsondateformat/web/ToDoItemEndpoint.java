package org.shipstone.sandbox.jsondateformat.web;

import org.shipstone.sandbox.jsondateformat.domain.ToDoItem;
import org.shipstone.sandbox.jsondateformat.service.ToDoItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author François Robert
 */
@RestController
@RequestMapping(value = "todo-items", produces = {APPLICATION_JSON_VALUE})
public class ToDoItemEndpoint {

  private final ToDoItemService toDoItemService;

  public ToDoItemEndpoint(ToDoItemService toDoItemService) {
    this.toDoItemService = toDoItemService;
  }

  @GetMapping
  public ResponseEntity<List<ToDoItem>> getList(
      Pageable pageable
  ) {
    Page<ToDoItem> toDoItemPage = toDoItemService.getList(pageable);
    HttpStatus httpStatus;
    HttpHeaders responseHttpHeaders = new HttpHeaders();
    responseHttpHeaders.set("x-total-count", String.valueOf(toDoItemPage.getTotalElements()));
    responseHttpHeaders.set("x-total-page", String.valueOf(toDoItemPage.getTotalPages()));
    if (toDoItemPage.getTotalElements() == 0) {
      httpStatus = NO_CONTENT;
    } else if (toDoItemPage.getTotalElements() < pageable.getPageSize()) {
      httpStatus = OK;
    } else {
      httpStatus = PARTIAL_CONTENT;
    }
    return new ResponseEntity<>(toDoItemPage.getContent(), responseHttpHeaders, httpStatus);
  }

  @GetMapping("{id}")
  public ToDoItem getTodoItemById(
    @PathVariable("id") Long id
  ) {
    return toDoItemService.getToDoItemById(id);
  }

  @PostMapping(consumes = {APPLICATION_JSON_VALUE})
  public ResponseEntity<ToDoItem> create(
      @RequestHeader HttpHeaders requestHttpHeaders,
      @RequestBody ToDoItem toDoItem,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    ToDoItem createdToDoItem = toDoItemService.createToDoItem(toDoItem);
    HttpHeaders responseHttpHeaders = new HttpHeaders();
    responseHttpHeaders.setLocation(
        uriComponentsBuilder.path("todo-items/{id}")
        .buildAndExpand(createdToDoItem.getId())
        .toUri()
    );
    return new ResponseEntity<>(responseHttpHeaders, CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<ToDoItem> update(
      @PathVariable("id") Long toDoItemId,
      @RequestBody ToDoItem toDoItem
  ) {
    if (Objects.equals(toDoItem.getId(), toDoItemId)) {
      toDoItemService.saveToDoItem(toDoItem);
    } else {
      // TODO: throw exception - :)
    }
    return new ResponseEntity<>(OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ToDoItem> delete(
      @PathVariable("id") Long toDoItemId
  ) {
    toDoItemService.deleteById(toDoItemId);
    return new ResponseEntity<>(OK);
  }

}
