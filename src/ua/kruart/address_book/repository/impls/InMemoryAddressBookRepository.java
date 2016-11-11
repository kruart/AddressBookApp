package ua.kruart.address_book.repository.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.kruart.address_book.model.Person;
import ua.kruart.address_book.repository.AddressBookRepository;

/**
 * Created by Arthur on 10.11.2016.
 */
public class InMemoryAddressBookRepository implements AddressBookRepository {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

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

    public void fillTestData() {
        personList.add(new Person("John Doe", "3399390"));
        personList.add(new Person("Michael Schumacher", "3400355"));
        personList.add(new Person("Alessandro Del Piero", "4343553"));
        personList.add(new Person("Paolo Dibala", "5782265"));
        personList.add(new Person("Antonio Conte", "2526483"));
        personList.add(new Person("Frank Lampard", "1568933"));
        personList.add(new Person("Andrea Pirlo", "5477700"));
        personList.add(new Person("Clarence Seedorf ", "5599330"));
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
}
