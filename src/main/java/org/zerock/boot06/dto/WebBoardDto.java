package org.zerock.boot06.dto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zerock.boot06.domain.WebBoard;

import java.sql.Timestamp;

@Getter
@ToString
public class WebBoardDto {
    private Long bno;
    private String title;
    private String writer;
    private String content;
    private Timestamp regdate;
    private Timestamp updatedate;

    public WebBoardDto(WebBoard entity) {
        this.bno = entity.getBno();
        this.title = entity.getTitle();
        this.writer = entity.getWriter();
        this.content = entity.getContent();
        this.regdate = entity.getRegdate();
        this.updatedate = entity.getUpdatedate();
    }
}