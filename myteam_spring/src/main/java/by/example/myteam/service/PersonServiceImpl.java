package by.example.myteam.service;

import by.example.myteam.dao.PersonDAO;
import by.example.myteam.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonServise {

    private final PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public List<Person> getAllPerson() {
        return personDAO.getAllPerson();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        personDAO.savePerson(person);
    }

    @Override
    @Transactional
    public Person getPerson(int id) {
        return personDAO.getPerson(id);
    }

    @Override
    @Transactional
    public void deletePerson(int id) {
        personDAO.deletePerson(id);
    }

    @Override
    @Transactional
    public Person validateAndGetPerson(Person person) {
        List<Person> allPerson = personDAO.getAllPerson();
        Optional<Person> login = allPerson.stream()
                .filter(p -> p.getLogin().equals(person.getLogin()))
                .findAny();
        Optional<Person> password = allPerson.stream()
                .filter(p -> p.getLogin().equals(person.getPassword()))
                .findAny();
        if (login.isPresent() && password.isPresent()) {
            return login.get();
        }
        return null;
    }
}
