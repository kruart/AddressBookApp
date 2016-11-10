package ua.kruart.address_book.repository.impls;

import ua.kruart.address_book.model.Person;
import ua.kruart.address_book.repository.AddressBookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 10.11.2016.
 */
public class InMemoryAddressBookRepository implements AddressBookRepository {

    public List<Person> personList = fillTestData();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {
        //Realization of this method doesn't mean in-memory impl
        //because when we change object it also will change in collection
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public List<Person> fillTestData() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John Doe", "3399390"));
        persons.add(new Person("Michael Schumacher", "3400355"));
        persons.add(new Person("Alessandro Del Piero", "4343553"));
        persons.add(new Person("Paolo Dibala", "5782265"));
        persons.add(new Person("Antonio Conte", "2526483"));
        persons.add(new Person("Frank Lampard", "1568933"));
        persons.add(new Person("Andrea Pirlo", "5477700"));
        persons.add(new Person("Clarence Seedorf ", "5599330"));

        return persons;
    }

}
