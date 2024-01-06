package ma.dnaengineering.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean hasCSVformat(MultipartFile filePath);

    void SaveData(MultipartFile filePath);
}
