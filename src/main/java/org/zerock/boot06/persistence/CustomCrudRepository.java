package org.zerock.boot06.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.boot06.domain.WebBoard;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {

}
