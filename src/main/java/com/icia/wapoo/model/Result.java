package com.icia.wapoo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 직렬화할때 동기화의 용도, 궁금하면 검색
     */
    private static final long serialVersionUID = 123L;
    /**
     * @Expose [ gson 어노테이션 ]
     * 해당 값이 null 이면, json 필드를 생략해줌.
     * Json,Gson 에 대한 설명 -> https://wky.kr/21
     */
    @Expose
    private int code; // 코드
    @SerializedName("message")
    @Expose
    private String msg; // 메시지
    @Expose
    private T data; // 데이터
}
