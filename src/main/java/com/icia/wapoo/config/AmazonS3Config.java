package com.icia.wapoo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AmazonS3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accesskey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;


    /**
     * 아마존 S3 서버에 등록된 키값을통해 인스턴스를 생성하고 자격을 증명한다.
     * @return 증명
     */
    @Bean
    public AmazonS3Config amazonS3Config() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accesskey, secretKey);
        return (AmazonS3Config) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
