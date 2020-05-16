package com.sorry.buskingbusking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class PerformanceRequest {

    @Id
    @GeneratedValue
    @Column
    private Long RequestNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String requestContents;

    private String requestGenre;

    private LocalDateTime requestDt;

    private String requestLocation;

    private String remark;

    private int viewCnt;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

}
