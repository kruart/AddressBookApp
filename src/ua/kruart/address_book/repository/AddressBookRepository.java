package ua.kruart.address_book.repository;

import ua.kruart.address_book.model.Person;

/**
 * Entry point to perform operations over Person's objects
 *
 * @author kruart
 */
public interface AddressBookRepository {

    void add(Person person);

    void update(Person person);

    void delete(Person person);
}
