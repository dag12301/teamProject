package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private int StoreId;
    private String regDate;
    private String updateDate;
    private String status;
    private String name;
    private String phone;
    private String address;
    private String addressDetail;
    private float localx;
    private float localy;
    private String storeKind;
    private String body;
    private int owner_id;
}
