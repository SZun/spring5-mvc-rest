package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Stream.of("Fruits", "Dried", "Fresh", "Exotic", "Nuts")
                .forEach(i -> {
                    Category cat = new Category();
                    cat.setName(i);
                    categoryRepository.save(cat);
                });
        System.out.println("Categories loaded=" + categoryRepository.count());

    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        Stream.of(customer1, customer2).forEach(customerRepository::save);

        System.out.println("Customers Loaded: " + customerRepository.count());
    }

}
