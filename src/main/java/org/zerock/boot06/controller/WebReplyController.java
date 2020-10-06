package org.zerock.boot06.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.boot06.domain.WebReply;
import org.zerock.boot06.service.WebReplyService;

@RequiredArgsConstructor
@RestController // REST 방식의 접근을 의미
@RequestMapping("/replies/*")
@Log
public class WebReplyController {

    /*
    특정 게시물의 ~
    1. 댓글 추가 기능 = POST
    2. 댓글 삭제 기능 = DELETE
    3. 댓글 수정 기능 = PUT
    4. 모든 댓글 조회 = GET
     */
    private final WebReplyService service;

    // 댓글 추가 기능 (POST)
    @PostMapping("/{bno}")
    public ResponseEntity<Void> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
        log.info("IN REPLY CONTROLLER: addReply() called...");
        log.info("BNO: " + bno);
        log.info("REPLY: " + reply);

        service.addReply(bno, reply);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
