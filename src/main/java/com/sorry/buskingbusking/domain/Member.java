package com.sorry.buskingbusking.domain;

import com.sorry.buskingbusking.domain.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private CommonFile profile;

    private String password;

    private String nickName;

    private String mobile;

    private String msgYn;

    private String msgId;

    private String delYn;

    @Embedded
    private Address address;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Performance> performanceList = new ArrayList<>();

    @OneToMany(mappedBy ="member", fetch = FetchType.LAZY)
    private List<PerformanceRequest> performanceRequestList = new ArrayList<>();

    @Builder
    public Member(Long id,String email, CommonFile profile, String password, String nickName, String mobile, String msgYn, String msgId, String delYn, Address address , LocalDateTime regDt, LocalDateTime updDt) {
        this.id = id;
        this.email = email;
        this.profile = profile;
        this.password = password;
        this.nickName = nickName;
        this.mobile = mobile;
        this.msgYn = msgYn;
        this.msgId = msgId;
        this.delYn = delYn;
        this.address = address;
        this.regDt = regDt;
        this.updDt = updDt;
    }
    public void updateMember(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.nickName = member.getNickName();
        this.mobile = member.getMobile();
        this.msgYn = member.getMsgYn();
        this.msgId = member.getMsgId();
        this.delYn = member.getDelYn();
        this.address = member.getAddress();
        this.updDt = LocalDateTime.now();

    }
    public MemberDTO toDTO(){
        return MemberDTO.builder()
                        .id(this.id)
                        .email(this.email)
                        .profile(this.profile)
                        .password(this.password)
                        .nickName(this.nickName)
                        .mobile(this.mobile)
                        .msgYn(this.msgYn)
                        .msgId(this.msgId)
                        .delYn(this.delYn)
                        .regDt(this.regDt)
                        .updDt(this.updDt)
                        .build();
    }






}
