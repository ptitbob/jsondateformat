package org.shipstone.sandbox.jsondateformat.service;

import org.shipstone.sandbox.jsondateformat.domain.ToDoItem;
import org.shipstone.sandbox.jsondateformat.repository.ToDoItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author François Robert
 */
@Service
@Transactional(readOnly = true)
public class ToDoItemService {

  private final ToDoItemRepository toDoItemRepository;

  public ToDoItemService(ToDoItemRepository toDoItemRepository) {
    this.toDoItemRepository = toDoItemRepository;
  }

  public Page<ToDoItem> getList(Pageable pageable) {
    return toDoItemRepository.findAll(pageable);
  }

  public ToDoItem getToDoItemById(final Long toDoItemId) {
    return toDoItemRepository.findOne(toDoItemId);
  }

  @Transactional
  public ToDoItem createToDoItem(ToDoItem toDoItem) {
    toDoItem.setId(null);
    if (toDoItem.getCreated() == null) {
      toDoItem.setCreated(LocalDateTime.now());
    }
    return toDoItemRepository.saveAndFlush(toDoItem);
  }

  @Transactional
  public ToDoItem saveToDoItem(ToDoItem toDoItem) {
    ToDoItem existingToDoItem = getToDoItemById(toDoItem.getId());
    if (existingToDoItem != null) {
      existingToDoItem.setDescription(toDoItem.getDescription());
      return toDoItemRepository.saveAndFlush(existingToDoItem);
    }
    return null;
  }

  @Transactional
  public void deleteById(final Long toDoItemId) {
    ToDoItem existingToDoItem = getToDoItemById(toDoItemId);
    if (existingToDoItem != null) {
      toDoItemRepository.delete(existingToDoItem);
    }
  }

}
