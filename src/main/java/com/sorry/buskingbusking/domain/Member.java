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

    @Builder
    public Member(Long id, String email, String password, String nickName, String mobile, String msgYn, String msgId, String delYn, LocalDateTime regDt, LocalDateTime updDt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.mobile = mobile;
        this.msgYn = msgYn;
        this.msgId = msgId;
        this.delYn = delYn;
        this.regDt = regDt;
        this.updDt = updDt;
    }
}
