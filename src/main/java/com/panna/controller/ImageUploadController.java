package com.panna.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageUploadController {

    //private static final String UPLOAD_DIR = "uploads/";
    private static final String UPLOAD_DIR =
            System.getProperty("user.dir") + "/uploads/";

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> uploadImage(

            @RequestParam("file") MultipartFile file

    ) throws IOException {

        // CREATE FOLDER IF NOT EXISTS
        File uploadFolder = new File(UPLOAD_DIR);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // GENERATE UNIQUE FILE NAME
        String fileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        // SAVE FILE
        File destinationFile =
                new File(UPLOAD_DIR + fileName);
        System.out.println(destinationFile.getAbsolutePath());
        file.transferTo(destinationFile);

        // IMAGE URL
        String imageUrl =
                "http://localhost:8080/uploads/" + fileName;

        return ResponseEntity.ok(imageUrl);
    }
}