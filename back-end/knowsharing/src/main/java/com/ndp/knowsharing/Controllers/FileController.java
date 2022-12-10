package com.ndp.knowsharing.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndp.knowsharing.Models.File.UploadFileResponse;
import com.ndp.knowsharing.Services.FileStorageService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/files")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(
        value = "/uploadFile"
    )
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/v1/files/downloadFile/")
            .path(fileName)
            .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    // @PostMapping(
    //     value = "/uploadFile"
    // )
    // public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
    //     String fileName = fileStorageService.storeFile(file);

    //     String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    //         .path("/downloadFile/")
    //         .path(fileName)
    //         .toUriString();

    //     UploadFileResponse zz =  new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    //     return new ResponseEntity<>(zz, HttpStatus.CREATED);
    // }

    @PostMapping(
        value = "/uploadMultipleFiles"
    )
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }

    @GetMapping(
        value = "/downloadFile/{fileName:.+}"
    )
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            //TODO: handle exception
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            // contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // "application/octet-stream";
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }
}
