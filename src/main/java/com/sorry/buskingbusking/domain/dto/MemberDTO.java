package com.sorry.buskingbusking.domain.dto;

import com.sorry.buskingbusking.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class MemberDTO {

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

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .mobile(mobile)
                .msgYn(msgYn)
                .delYn(delYn)
                .regDt(regDt)
                .updDt(updDt)
                .build();
    }
}
