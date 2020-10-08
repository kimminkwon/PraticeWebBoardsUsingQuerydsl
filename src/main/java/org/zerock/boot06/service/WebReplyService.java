package org.zerock.boot06.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.domain.WebReply;
import org.zerock.boot06.dto.WebReplyDto;
import org.zerock.boot06.persistence.WebReplyRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log
public class WebReplyService {

    private final WebReplyRepository repository;

    @Transactional
    public ResponseEntity<List<WebReplyDto>> addReply(Long bno, WebReply reply) {
        log.info("IN SERVICE(WEB REPLY) : addReply() called...");
        WebBoard board = new WebBoard();
        board.setBno(bno);
        reply.setBoard(board);
        repository.save(reply);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    private List<WebReplyDto> getListByBoard(WebBoard board) {
        log.info("IN SERVICE(WEB REPLY) : getListByBoard() called...");
        log.info("BOARD" + board);
        List<WebReplyDto> dtoList = new ArrayList<>();
        repository.getRepliesOfBoard(board).forEach(
                webReply -> {
                    dtoList.add(new WebReplyDto(webReply));
                }
        );
        log.info("dtoList" + dtoList);
        return dtoList;
    }
}