package org.zerock.boot06.persistence;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class CustomRepositoryTest {
    @Autowired
    CustomCrudRepository repository;

    @Test
    public void 사용자정의_repository_Test() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        String type = "t";
        String keyword = "10";

        Page<Object[]> result = repository.getCustomPage(type, keyword, pageable);
        log.info("" + result);
        log.info("TOTAL PAGES: " + result.getTotalPages());
        log.info("TOTAL SIZE: " + result.getTotalElements());

        result.getContent().forEach(
                objects -> {
                    log.info(Arrays.toString(objects));
                }
        );
    }

    @Test
    public void 사용자정의_repository_Test_조건주고_댓글개수도() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        String type = "w";
        String keyword = "kwon";

        Page<Object[]> result = repository.getCustomPage(type, keyword, pageable);
        log.info("" + result);
        log.info("TOTAL PAGES: " + result.getTotalPages());
        log.info("TOTAL SIZE: " + result.getTotalElements());

        result.getContent().forEach(
                objects -> {
                    log.info(Arrays.toString(objects));
                }
        );
    }
}
