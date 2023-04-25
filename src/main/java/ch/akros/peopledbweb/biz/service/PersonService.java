package ch.akros.peopledbweb.biz.service;

import ch.akros.peopledbweb.biz.model.Person;
import ch.akros.peopledbweb.data.FileStorageRepository;
import ch.akros.peopledbweb.data.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipInputStream;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final FileStorageRepository fileStorageRepository;

    public PersonService(PersonRepository personRepository, FileStorageRepository fileStorageRepository) {
        this.personRepository = personRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    @Transactional
    public Person save(Person entity, InputStream photo) {
        Person savedPerson = personRepository.save(entity);
        fileStorageRepository.save(savedPerson.getPhotoFileName(), photo);
        return savedPerson;
    }

    public Optional<Person> findById(Long aLong) {
        return personRepository.findById(aLong);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public void deleteAllById(Iterable<Long> ids) {
        List<String> photoFileNames = personRepository.findPhotoFileNamesByIds(ids);
        personRepository.deleteAllById(ids);
        fileStorageRepository.deleteAllByName(photoFileNames);
    }

    public void importCSV(InputStream csvFileStream) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(csvFileStream);
            zipInputStream.getNextEntry();
            InputStreamReader inputStreamReader = new InputStreamReader(zipInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.lines()
                    .skip(1)
                    .limit(20)
                    .map(Person::parse)
                    .forEach(p -> personRepository.save(p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
