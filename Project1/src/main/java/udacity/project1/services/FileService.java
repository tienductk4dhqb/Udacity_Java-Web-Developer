package udacity.project1.services;

import udacity.project1.mappers.FileMapper;
import udacity.project1.models.File;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FileService {
    private final FileMapper fileMapper;

    public boolean checkExistFile(String fileName) {
        return !Objects.isNull(fileMapper.findByName(fileName));
    }

    public boolean add(MultipartFile file, int userId) throws IOException {
        File fileModel = File.builder()
                .fileData(file.getBytes())
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize() + " Bytes")
                .contentType(file.getContentType())
                .userId(userId)
                .build();
        fileMapper.insert(fileModel);

        return true;
    }

    public File get(int id) {
        return fileMapper.findById(id);
    }

    public void update(File file) {
        fileMapper.update(file);
    }

    public List<File> getAllByUser(int userId) {
        return fileMapper.getAllByUser(userId);
    }

    public boolean deleteById(int id) {
        return fileMapper.deleteById(id) == 1;
    }

    public File findById(int fileId) {
        return fileMapper.findById(fileId);
    }
}
