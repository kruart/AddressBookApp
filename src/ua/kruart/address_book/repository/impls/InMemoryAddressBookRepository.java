package ua.kruart.address_book.repository.impls;

import ua.kruart.address_book.model.Person;
import ua.kruart.address_book.repository.AddressBookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 10.11.2016.
 */
public class InMemoryAddressBookRepository implements AddressBookRepository {

    private List<Person> personList = new ArrayList<>();

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

    public List<Person> getPersonList() {
        return personList;
    }
}
