package com.example.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class FileStorageService {

    public String saveFile(MultipartFile file) {
        String uploadDir = System.getProperty("user.dir") + "/uploads";   // ✅ 파일을 저장할 폴더
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();  // ✅ 폴더가 없으면 생성
        }

        try {
            String originalFileName = file.getOriginalFilename();  // ✅ 원래 파일 이름
            String safeFileName = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8);  // ✅ UTF-8로 인코딩
            String finalFileName = UUID.randomUUID().toString() + "_" + safeFileName;  // ✅ 파일명 앞에 UUID 추가 (중복 방지)

            File destinationFile = new File(uploadPath, finalFileName);
            file.transferTo(destinationFile);  // ✅ 파일 저장

            return "/uploads/" + finalFileName;  // ✅ 업로드된 파일의 URL 반환
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
