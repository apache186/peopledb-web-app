package ch.akros.peopledbweb.data;

import ch.akros.peopledbweb.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class FileStorageRepository {

    @Value("${STORAGE_FOLDER}")
    private String storageFolder;
    public void save(String originalFilename, InputStream inputStream) {
        try {
            Path filePath = Path.of(storageFolder).resolve(originalFilename).normalize();
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }

    public Resource findByName(String filename) {
        try {
            Path filePath = Path.of(storageFolder).resolve(filename).normalize();
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new StorageException(e);
        }
    }

    public void deleteAllByName(Collection<String> photoFileNames) {
        try {
            for (String photoFileName : photoFileNames.stream().filter(p -> p!= null).collect(Collectors.toList())) {
                Path filePath = Path.of(storageFolder).resolve(photoFileName).normalize();
                Files.deleteIfExists(filePath);
            }
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }
}
