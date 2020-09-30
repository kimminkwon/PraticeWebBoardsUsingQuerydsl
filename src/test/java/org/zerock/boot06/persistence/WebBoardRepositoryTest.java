package org.zerock.boot06.persistence;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.boot06.domain.WebBoard;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class WebBoardRepositoryTest {

    @Autowired
    WebBoardRepository repository;

    @Test
    public void test() {

    }

    @Test
    public void insertBoardDummies() {
        IntStream.range(0, 300).forEach(
                i -> {
                    WebBoard board = new WebBoard();
                    board.setTitle("Sample Board Title " + i);
                    board.setContent("Sample Content..." + i);
                    board.setWriter("user00" + (i % 10));

                    repository.save(board);
                }
        );
    }

    @Test
    public void testfindAllUsingQuerydslWithoutKeyword() {
        // Bno 기준 역 방향 (300부터)으로 Page 0부터 20개의 Data를 가져온다.
        Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
        Page<WebBoard> results = repository.findAll(repository.makePrdicate(null, null), pageable);

        assertThat(results.getContent().size()).isEqualTo(20);
        assertThat(results.getContent().get(0).getBno()).isEqualTo(300L);

        log.info("PAGE: " + results.getPageable());
        log.info("=====================================");
        results.getContent().forEach(
                webBoard -> log.info("" + webBoard)
        );
    }

    @Test
    public void testfindAllUsingQuerydslWithKeyword() {
        Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
        Page<WebBoard> results = repository.findAll(repository.makePrdicate("t", "10"), pageable);

        results.getContent().forEach(
                webBoard -> assertThat(webBoard.getTitle().contains("10"))
        );

        log.info("PAGE: " + results.getPageable());
        log.info("=====================================");
        results.getContent().forEach(
                webBoard -> log.info("" + webBoard)
        );
    }
}
