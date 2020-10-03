package com.sorry.buskingbusking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Grape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grapeNo;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer chargingCnt;

    private Long amount;

    private String method;

    private LocalDateTime chargingDate;




    

}
