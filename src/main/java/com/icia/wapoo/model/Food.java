package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Food {
    private int foodId;
    private String regDate;
    private String updateDate;
    private String name;
    private String description;
    private String price;
    private String status;
    private int store_id;
}
