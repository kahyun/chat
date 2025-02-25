package com.example.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    private final String UPLOAD_DIR = "uploads";  // 프로젝트 루트 내의 "uploads" 폴더

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("파일이 비어 있습니다.");
        }

        // 폴더가 없으면 생성
        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // 원본 파일명
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        // 파일명 중복 방지용 UUID
        String uuid = UUID.randomUUID().toString();
        // 실제 저장할 파일 이름
        String savedFilename = uuid + "_" + originalFilename;

        // 디스크에 저장
        File destination = new File(uploadFolder, savedFilename);
        file.transferTo(destination);

        // 업로드된 파일에 접근할 수 있는 URL 구성
        // /files/ 경로로 접근 시 FileConfig에서 매핑되도록 설정
        String fileUrl = "/files/" + savedFilename;

        log.info("File uploaded: {}", destination.getAbsolutePath());
        return ResponseEntity.ok().body("{\"fileUrl\": \"" + fileUrl + "\"}");
    }
}
