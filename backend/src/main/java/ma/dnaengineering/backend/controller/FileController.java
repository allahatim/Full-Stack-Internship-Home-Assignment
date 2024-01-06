package ma.dnaengineering.backend.controller;

import ma.dnaengineering.backend.response.ResponseMessage;
import ma.dnaengineering.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("files")
public class FileController {
    @Autowired
    private FileService service;


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        if (service.hasCSVformat(file)) {
            service.SaveData(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("File Uploaded successfully: " + file.getOriginalFilename()));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Invalid CSV File format: " + file.getOriginalFilename()));
    }
}
