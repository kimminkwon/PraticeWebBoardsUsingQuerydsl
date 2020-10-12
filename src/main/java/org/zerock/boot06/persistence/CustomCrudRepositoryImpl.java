package org.zerock.boot06.persistence;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.boot06.domain.QWebBoard;
import org.zerock.boot06.domain.QWebReply;
import org.zerock.boot06.domain.WebBoard;

import java.util.ArrayList;
import java.util.List;

@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {

    public CustomCrudRepositoryImpl() {
        super(WebBoard.class);
    }


    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable pageable) {

        log.info("==================================");
        log.info("TYPE: " + type);
        log.info("KEYWORD: " + keyword);
        log.info("PAGEABLE: " + pageable);
        log.info("==================================");

        // Querydsl 객체를 받아온다.
        QWebBoard b = QWebBoard.webBoard;
        QWebReply r = QWebReply.webReply;

        JPQLQuery<WebBoard> query = from(b);

        JPQLQuery<Tuple> tupleJPQLQuery = query.select(b.bno, b.title, r.count(), b.writer, b.regdate);

        tupleJPQLQuery.leftJoin(r); // webReply와 Join 처리
        tupleJPQLQuery.on(b.bno.eq(r.board.bno)); // Join 조건
        tupleJPQLQuery.where(b.bno.gt(0l));

        // 들어온 조회 조건 처리
        if(type != null) {
            switch (type.toLowerCase()) {
                case "t":
                    tupleJPQLQuery.where(b.title.like("%" + keyword + "%"));
                    break;
                case "c":
                    tupleJPQLQuery.where(b.content.like("%" + keyword + "%"));
                    break;
                case "w":
                    tupleJPQLQuery.where(b.writer.like("%" + keyword + "%"));
                    break;
            }
        }

        tupleJPQLQuery.groupBy(b.bno); // 그룹화시켜서 댓글 개수 확인
        tupleJPQLQuery.orderBy(b.bno.desc());

        tupleJPQLQuery.offset(pageable.getOffset());
        tupleJPQLQuery.limit(pageable.getPageSize());

        List<Tuple> list = tupleJPQLQuery.fetch();
        List<Object[]> resultList = new ArrayList<>();
        list.forEach(
                tuple -> {
                    resultList.add(tuple.toArray());
                }
        );

        long total = tupleJPQLQuery.fetchCount();

        return new PageImpl<>(resultList, pageable, total);
    }
}
