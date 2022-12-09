package com.ndp.knowsharing.Services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ndp.knowsharing.Configs.Properties.FileStorageProperties;
import com.ndp.knowsharing.Entities.File;
import com.ndp.knowsharing.Utils.Files.Exceptions.FileStorageException;
import com.ndp.knowsharing.Utils.Files.Exceptions.MyFileNotFoundException;
import com.ndp.knowsharing.Utils.Files.FileNameString.FileNameStringUtil;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    private FileService fileService;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            //TODO: handle exception
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        // String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String fileNameExtension = FileNameStringUtil.getExtensionByStringHandling(file.getOriginalFilename()).get();

        UUID uuid = UUID.randomUUID();

        String fileName = StringUtils.cleanPath(uuid.toString() + "." + fileNameExtension);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            File tmpToSave = new File(uuid.toString(), fileNameExtension, fileName);

            File tmpSaved = fileService.createOne(tmpToSave);

            if(tmpSaved == null) {
                throw new FileStorageException("Sorry! File can't save to db " + fileName);
            }

            return fileName;
        } catch (IOException e) {
            //TODO: handle exception
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException e) {
            //TODO: handle exception
            throw new MyFileNotFoundException("File not found " + fileName, e);
        }
    }
}
