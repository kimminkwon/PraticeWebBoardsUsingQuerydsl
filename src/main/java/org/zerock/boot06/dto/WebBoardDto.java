package org.zerock.boot06.dto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.domain.WebReply;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class WebBoardDto {
    private Long bno;
    private String title;
    private String writer;
    private String content;
    private Timestamp regdate;
    private Timestamp updatedate;
    private List<WebReply> replies;
    private Long numOfReplies;

    public WebBoardDto(WebBoard entity) {
        this.bno = entity.getBno();
        this.title = entity.getTitle();
        this.writer = entity.getWriter();
        this.content = entity.getContent();
        this.regdate = entity.getRegdate();
        this.updatedate = entity.getUpdatedate();
        this.replies = entity.getReplies();
    }

    public WebBoardDto(Object[] entity) {
        this.bno = (Long) entity[0];
        this.title = (String) entity[1];
        this.numOfReplies = (Long) entity[2];
        this.writer = (String) entity[3];
        this.regdate = (Timestamp) entity[4];
    }

}