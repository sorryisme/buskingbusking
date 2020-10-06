package com.sorry.buskingbusking.domain.dto;

import com.sorry.buskingbusking.domain.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AuditingDTO {


    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Member createdBy;

    private Member modifiedBy;
}
