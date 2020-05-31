package com.sorry.buskingbusking.domain.dto;

import com.sorry.buskingbusking.domain.CommonFile;
import com.sorry.buskingbusking.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MemberDTO {

    private Long id;

    private String email;

    private CommonFile profile;

    private String password;

    private String nickName;

    private String mobile;

    private String msgYn;

    private String msgId;

    private String delYn;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    @Builder
    public MemberDTO(Long id,String email, CommonFile profile,String password, String nickName, String mobile, String msgYn, String msgId, String delYn, LocalDateTime regDt, LocalDateTime updDt){
        this.id = id;
        this.email = email;
        this.profile = profile;
        this.password = password;
        this.nickName = nickName;
        this.mobile = mobile;
        this.msgYn = msgYn;
        this.msgId = msgId;
        this.delYn = delYn;
        this.regDt = regDt;
        this.updDt = updDt;
    }

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .profile(profile)
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
