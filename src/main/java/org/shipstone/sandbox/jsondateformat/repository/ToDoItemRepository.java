package org.shipstone.sandbox.jsondateformat.repository;

import org.shipstone.sandbox.jsondateformat.domain.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author François Robert
 */
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}
