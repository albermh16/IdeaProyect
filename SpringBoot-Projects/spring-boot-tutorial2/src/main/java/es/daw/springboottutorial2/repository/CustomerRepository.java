package es.daw.springboottutorial2.repository;


import es.daw.springboottutorial2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface CustomerRepository extends CrudRepository<Customer, Integer> {
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //Customer findCustomerById(Integer id);

//    Customer findCustomerByFirstName(String firstName);
//
//    Optional<Customer> findCustomerByLastName(String lastName);
//
//    @Override
//    Iterable<Customer> findAll();
}