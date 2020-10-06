package org.zerock.boot06.persistence;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.domain.WebReply;

import java.util.Arrays;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTest {

    @Autowired
    WebReplyRepository repository;

    @Test
    public void 더미데이터_생성_댓글() {
        Long[] arr = {305L, 304L, 303L};

        Arrays.stream(arr).forEach(
             bno -> {
                 WebBoard board = new WebBoard();
                 board.setBno(bno);

                 IntStream.range(0, 10).forEach(
                         i -> {
                             WebReply reply = new WebReply();
                             reply.setReplyText("REPLY..." + i);
                             reply.setReplyer("REPLYER" + i);
                             reply.setBoard(board); // 기본키만 동일하게하여 생성한 Board로 연관 -> 외래키로 저장될 것

                             repository.save(reply);
                         }
                 );
             }
        );
    }
}
