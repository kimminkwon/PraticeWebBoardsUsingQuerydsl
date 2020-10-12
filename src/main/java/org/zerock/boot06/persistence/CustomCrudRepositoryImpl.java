package org.zerock.boot06.persistence;

import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.boot06.domain.WebBoard;

@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {

    public CustomCrudRepositoryImpl() {
        super(WebBoard.class);
    }


    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable pageable) {
        log.info("==================================");
        log.info("TYPE" + type);
        log.info("KEYWORD" + keyword);
        log.info("PAGEABLE" + pageable);
        log.info("==================================");

        return null;
    }
}
