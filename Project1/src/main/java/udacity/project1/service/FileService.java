package udacity.project1.service;

import java.util.List;
import org.springframework.stereotype.Service;

import udacity.project1.mapper.FileMapper;
import udacity.project1.model.File;

@Service
public class FileService {

    private final FileMapper files;

    public FileService(FileMapper mapper) {
        files = mapper;
    }

    public File get(File file) {
        return files.get(file);
    }

    public List<File> allBy(String UID) {
        return files.allFrom(UID);
    }

    public void remove(File file) {
        files.delete(file);
    }

    public boolean exists(File file) {
        return files.find(file) != null;
    }

    public void store(File file) {
        files.insert(file);
    }

}
