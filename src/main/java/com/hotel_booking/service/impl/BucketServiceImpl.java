package com.hotel_booking.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.hotel_booking.service.BucketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class BucketServiceImpl implements BucketService {

    private AmazonS3 amazonS3;

    public BucketServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            try {
                amazonS3.putObject(bucketName, convFile.getName(), convFile);
                return amazonS3.getUrl(bucketName, file.getOriginalFilename()).toString();
            } catch (AmazonS3Exception s3Exception) {
                return "Unable to upload file :" + s3Exception.getMessage();
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to upload file", e);
        }

    }

    @Override
    public byte[] getFile(String fileName, String bucketName) {
        try{
            S3Object object = amazonS3.getObject(bucketName, fileName);
            InputStream inputStream = object.getObjectContent();
            byte[] bytes = StreamUtils.copyToByteArray(inputStream);
            return bytes;
        }catch (AmazonS3Exception e) {
            throw new IllegalStateException("Failed to get file from S3: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
