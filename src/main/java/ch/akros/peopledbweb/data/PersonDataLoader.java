package ch.akros.peopledbweb.data;

import ch.akros.peopledbweb.biz.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Component
//public class PersonDataLoader implements ApplicationRunner {
//    private PersonRepository personRepository;
//    public PersonDataLoader(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        if (personRepository.count() == 0) {
//            List<Person> people = List.of(
//                    new Person(null, "Felix", "Lobrecht", LocalDate.of(1988, 12, 24), "felix.lobrecht@hack.de", new BigDecimal("454000")),
//                    new Person(null, "James", "Harden", LocalDate.of(1989, 8, 26), "thebeard@nba.com", new BigDecimal("33000000")),
//                    new Person(null, "Matthew", "McConaughey", LocalDate.of(1969, 11, 4),"mmcc@hollywood.com", new BigDecimal("89227198")),
//                    new Person(null, "Mladen", "Petric", LocalDate.of(1981, 1, 1), "mpetric@sky.com", new BigDecimal("299000")),
//                    new Person(null, "Luka", "Modric", LocalDate.of(1985, 9, 9), "lukita@rm.com", new BigDecimal("19690000")),
//                    new Person(null, "Kyrie", "Irving", LocalDate.of(1992, 3, 23), "irving@mavs.com", new BigDecimal("36930000")),
//                    new Person(null, "Lovro", "Majer", LocalDate.of(1998, 1, 17), "lovro.majer@rennais.fr", new BigDecimal("1352000")),
//                    new Person(null, "James", "LeBron", LocalDate.of(1984, 12, 30), "king@lakers.com", new BigDecimal("44470000")),
//                    new Person(null, "Josip", "Sutalo", LocalDate.of(2000, 2, 28), "josip.sutalo@gnkdinamo.hr", new BigDecimal("109200")),
//                    new Person(null, "Josko", "Gvardiol", LocalDate.of(2002, 1, 23), "gladiator@bulls.com", new BigDecimal("3068000")),
//                    new Person(null, "Tommi", "Schmitt", LocalDate.of(1989, 1, 26), "tommi.schmitt@hack.de", new BigDecimal("389000")));
//            personRepository.saveAll(people);
//        }
//    }
//}
