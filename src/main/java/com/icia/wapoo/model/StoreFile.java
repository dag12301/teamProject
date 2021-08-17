package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreFile {
    private int storefileId;
    private String regDate;
    private String orgName;
    private String name;
    private String filetype;
    private long filesize;
    private int store_id;
}
