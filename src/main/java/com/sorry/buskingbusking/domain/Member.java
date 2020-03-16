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
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickName;

    private String mobile;

    private String msgYn;

    private String msgId;

    private String delYn;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

}
