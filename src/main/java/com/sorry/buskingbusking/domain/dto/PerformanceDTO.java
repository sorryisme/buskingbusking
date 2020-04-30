package com.sorry.buskingbusking.domain.dto;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Performance;
import lombok.*;
import org.aspectj.weaver.patterns.PerObject;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PerformanceDTO {

    private Long id;

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

    public Performance toEntity(){
        return Performance.builder()
                            .id(this.id)
                            .member(this.member)
                            .performanceName(this.performanceName)
                            .performanceGenre(this.performanceGenre)
                            .performanceDate(this.performanceDate)
                            .performanceDate(this.performanceDate)
                            .performanceLocation(this.performanceLocation)
                            .performanceDesc(this.performanceDesc)
                            .performanceRemark(this.performanceRemark)
                            .viewCnt(this.viewCnt)
                            .regDt(this.regDt)
                            .updDt(this.updDt).build();
    }

}
