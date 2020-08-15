package com.sorry.buskingbusking.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginDTO {

    private Long id;
    private String email;
    private String nickName;
    private String mobile;

    @Builder
    public LoginDTO(Long id, String email, String nickName, String mobile) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.mobile = mobile;
    }

}
