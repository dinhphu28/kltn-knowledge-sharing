package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.File;
import com.ndp.knowsharing.Repositories.FileRepo;

@Service
public class FileService {
    @Autowired
    private FileRepo repo;

    public File retrieveOne(String uuid) {
        File sth = null;

        try {
            sth = repo.findById(uuid).get();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return sth;
    }

    public File createOne(File file) {
        File tmpFile = null;

        Boolean b_existed = true;

        try {
            tmpFile = repo.findById(file.getUuid()).get();
        } catch (Exception e) {
            //TODO: handle exception
            tmpFile = repo.save(file);

            b_existed = false;
        }

        if(b_existed) {
            return null;
        } else {
            return tmpFile;
        }
    }
}
