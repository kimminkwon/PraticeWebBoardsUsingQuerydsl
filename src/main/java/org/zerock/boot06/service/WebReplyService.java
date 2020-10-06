package org.zerock.boot06.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.domain.WebReply;
import org.zerock.boot06.persistence.WebReplyRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log
public class WebReplyService {

    private final WebReplyRepository repository;

    @Transactional
    public ResponseEntity<List<WebReply>> addReply(Long bno, WebReply reply) {
        WebBoard board = new WebBoard();
        board.setBno(bno);
        reply.setBoard(board);
        repository.save(reply);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    private List<WebReply> getListByBoard(WebBoard board) {
        log.info("IN SERVICE(WEB REPLY) : getListByBoard() called...");
        log.info("BOARD" + board);
        return repository.getRepliesOfBoard(board);
    }
}
