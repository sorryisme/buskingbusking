package com.sorry.buskingbusking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Performance {

    @Id
    @Column(name = "performance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String performanceName;

    private String performanceGenre;

    private String performanceDate;

    private String performanceLocation;

    private String performanceDesc;

    private String performanceRemark;

    private Integer viewCnt;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    @Builder
    public Performance(Long id, String performanceName, String performanceGenre, String performanceDate, String performanceLocation,
                       String performanceDesc, String performanceRemark, Integer viewCnt,LocalDateTime regDt, LocalDateTime updDt){
        this.id = id;
        this.performanceName = performanceName;
        this.performanceGenre = performanceGenre;
        this.performanceDate = performanceDate;
        this.performanceLocation = performanceLocation;
        this.performanceDesc = performanceDesc;
        this.performanceRemark = performanceRemark;
        this.viewCnt = viewCnt;
        this.regDt = regDt;
        this.updDt = updDt;
    }


    public void addMember(Member member){
        this.member = member;
        member.getPerformanceList().add(this);
    }


}
