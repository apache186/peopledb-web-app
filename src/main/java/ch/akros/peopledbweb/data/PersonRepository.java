package ch.akros.peopledbweb.data;

import ch.akros.peopledbweb.biz.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {

    @Query(nativeQuery = true, value = "SELECT PHOTO_FILE_NAME FROM PERSON WHERE ID IN :ids")
    public List<String> findPhotoFileNamesByIds(@Param("ids") Iterable<Long> ids);
}
