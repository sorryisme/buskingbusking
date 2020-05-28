package com.sorry.buskingbusking.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CommonCode {

    @Id
    @GeneratedValue
    @Column(name ="code_id")
    private Long id;

    private String codeName;

    private String codeDesc;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private CommonCode parent;

    @OneToMany(mappedBy = "parent")
    private List<CommonCode> child = new ArrayList<>();

    public void addChildCode(CommonCode child){
        this.child.add(child);
        child.parent = this;
    }

    @Builder
    public CommonCode(Long id, String codeName, String codeDesc, LocalDateTime regDt, LocalDateTime updDt){
        this.id = id;
        this.codeName = codeName;
        this.codeDesc = codeDesc;
        this.regDt = regDt;
        this.updDt = updDt;
    }

}
