package com.sorry.buskingbusking.domain;

import com.sorry.buskingbusking.util.ContextUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Context;
import javax.persistence.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends AuditingEntity {

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

    @OneToMany
    private List<CommonFile> fileList = new ArrayList<>();

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

        if(hasCookie()){
           return;
        }

       /* if(viewCnt == null){
            viewCnt = 0;
        }*/

        this.viewCnt++;
        addCookieNoticeSeq();
    }

    private Boolean hasCookie(){
        HttpServletRequest request = ContextUtil.getRequest();
        Cookie[] cookies = request.getCookies();

        Stream<Cookie> cookieStream = Arrays.stream(cookies);

        return cookieStream.anyMatch(cookie -> {
            return cookie.getName().equals("notice"+this.id);
        });
    }

    private void addCookieNoticeSeq(){
        HttpServletResponse response = ContextUtil.getResponse();
        Cookie noticeCookie = new Cookie("notice"+this.id,  String.valueOf(this.id));
        response.addCookie(noticeCookie);
    }



}
