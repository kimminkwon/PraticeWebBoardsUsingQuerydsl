package org.zerock.boot06.persistence;

import org.springframework.data.repository.CrudRepository;
import org.zerock.boot06.domain.WebReply;

public interface WebReplyRepository extends CrudRepository<WebReply, Long> {
}
