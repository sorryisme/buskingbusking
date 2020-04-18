package com.sorry.buskingbusking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Notice {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String noticeTitle;

    private String noticeContents;

    private Integer viewCnt;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Notice(Long id,String noticeTitle,String noticeContents, Integer viewCnt, LocalDateTime regDt, LocalDateTime updDt, Member member) {
       this.id = id;
       this.noticeTitle = noticeTitle;
       this.viewCnt = viewCnt;
       this.noticeContents = noticeContents;
       this.regDt = regDt;
       this.updDt = updDt;
       this.member = member;
    }

    public void addViewCnt(){
        this.viewCnt++;
    }


}
