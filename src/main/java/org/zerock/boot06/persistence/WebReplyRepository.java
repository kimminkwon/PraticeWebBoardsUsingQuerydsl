package org.zerock.boot06.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.domain.WebReply;

import java.util.List;

public interface WebReplyRepository extends CrudRepository<WebReply, Long> {

    // ?1은 첫번째 parameter를 의미
    @Query("SELECT r FROM WebReply r WHERE r.board = ?1 " + " AND r.rno > 0 ORDER BY r.rno ASC")
    public List<WebReply> getRepliesOfBoard(WebBoard board);

}
