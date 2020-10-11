package com.sorry.buskingbusking.domain.dto;

import com.sorry.buskingbusking.domain.Member;
import com.sorry.buskingbusking.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeDTO {

    private Long id;

    private String noticeTitle;

    private String noticeContents;

    private Integer viewCnt = 0;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    private Member member;

    private List<MultipartFile> noticeFileList = new ArrayList<>();

    public void addFile(MultipartFile file){
        this.noticeFileList.add(file);
    }

    public Notice toEntity(){
        return Notice.builder()
                .id(this.id)
                .noticeTitle(this.noticeTitle)
                .noticeContents(this.noticeContents)
                .viewCnt(this.viewCnt)
                .regDt(this.regDt)
                .updDt(this.updDt)
                .member(this.member)
                .build();
    }


    public void setInsert(){
        this.viewCnt = 0;
        this.regDt = LocalDateTime.now();
        //추후 삭제

        //추후 로그인 유저 등록
        //this.Member = member
    }

}
