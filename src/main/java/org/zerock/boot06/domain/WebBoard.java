package org.zerock.boot06.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tbl_webboards")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class WebBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @Builder
    public WebBoard(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
