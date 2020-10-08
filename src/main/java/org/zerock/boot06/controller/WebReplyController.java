package org.zerock.boot06.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.boot06.domain.WebReply;
import org.zerock.boot06.dto.WebReplyDto;
import org.zerock.boot06.service.WebReplyService;

import java.util.List;

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
    public ResponseEntity<List<WebReplyDto>> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
        log.info("IN REPLY CONTROLLER: addReply() called...");
        log.info("BNO: " + bno);
        log.info("REPLY: " + reply);

        return service.addReply(bno, reply);
    }

    // 댓글 삭제 기능 (Delete): 댓글 삭제용 rno와 삭제 후 갱신을 위한 bno
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<WebReplyDto>> removeReply(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {
        log.info("IN REPLY CONTROLLER: removeReply() called...");
        log.info("BNO: " + bno);
        log.info("RNO: " + rno);

        return service.removeReply(bno, rno);
    }

    // 댓글 수정 기능 (PUT)
    @PutMapping("/{bno}")
    public ResponseEntity<List<WebReplyDto>> modifyReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
        log.info("IN REPLY CONTROLLER: modifyReply() called...");
        log.info("BNO: " + bno);
        log.info("REPLY: " + reply);

        return service.modifyReply(bno, reply);
    }
}
