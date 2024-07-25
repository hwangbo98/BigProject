package com.project.dstj.s3;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    @Autowired
    private S3Upload s3Upload;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "imgurl2") MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();
           
        String url = s3Upload.upload(multipartFile);
        return ResponseEntity.ok().build();
    }
}


