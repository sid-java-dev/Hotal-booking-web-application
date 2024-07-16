package com.hotel_booking.service;

import org.springframework.web.multipart.MultipartFile;

public interface BucketService {
    String uploadFile(MultipartFile file,String bucketName);
     byte[] getFile(String fileName,String bucketName);
}
