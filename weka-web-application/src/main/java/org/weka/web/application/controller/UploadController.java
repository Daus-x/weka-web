package org.weka.web.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.weka.web.application.service.FileService;
import org.weka.web.application.service.impl.FileServiceImpl;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by suad on 9/4/2014.
 */
@RestController
public class UploadController {

    @Autowired
    FileService fileService;

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File tempFile=new File(file.getOriginalFilename());
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(tempFile));
                stream.write(bytes);
                stream.close();

                fileService.saveFile(tempFile,tempFile.getName());

                return new ResponseEntity<String>("ok",HttpStatus.OK );
            } catch (Exception e) {
                return new ResponseEntity<String>("Exeption Happend",HttpStatus.BAD_REQUEST );
            }
        } else {
            return new ResponseEntity<String>("File is empty",HttpStatus.FORBIDDEN );
        }
    }
}
