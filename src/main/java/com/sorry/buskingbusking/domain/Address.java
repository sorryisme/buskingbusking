package com.sorry.buskingbusking.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    private String zipcode;
    private String address1;
    private String address2;
    private String addressExt;

    @Builder
    public Address(String zipcode, String address1, String address2, String addressExt){
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.addressExt = addressExt;
    }


}
