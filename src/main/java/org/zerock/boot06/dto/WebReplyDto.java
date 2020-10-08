package org.zerock.boot06.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.domain.WebReply;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@ToString(exclude = "board")
@NoArgsConstructor
public class WebReplyDto {
    private Long rno;
    private String replyText;
    private String replyer;
    private Timestamp regdate;
    private Timestamp updatedate;
    // JsonIgnore가 없고, 연결이 Lazy이기 때문에(board에 값이 없을 수 있음) Json으로 Convert할 때 Null을 만나서 오류가 생기는 것이다.
    @JsonIgnore
    private WebBoard board;

    public WebReplyDto(WebReply entity) {
        this.rno = entity.getRno();
        this.replyText = entity.getReplyText();
        this.replyer = entity.getReplyer();
        this.regdate = entity.getRegdate();
        this.updatedate = entity.getUpdatedate();
        this.board = entity.getBoard();
    }
}
