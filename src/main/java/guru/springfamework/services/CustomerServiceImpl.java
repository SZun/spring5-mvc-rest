package guru.springfamework.services;

import guru.springfamework.api.v1.mappers.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResouceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnCustomer(customerMapper.customerDTOtoCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnCustomer(Customer customer){
        customer = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(
                customer
        );
        customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer toReturn = customerMapper.customerDTOtoCustomer(customerDTO);
        toReturn.setId(id);
        return saveAndReturnCustomer(toReturn);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(i -> {
            if(customerDTO.getFirstName() != null) i.setFirstName(customerDTO.getFirstName());
            if(customerDTO.getLastName() != null) i.setLastName(customerDTO.getLastName());
            CustomerDTO customerDTO2 = customerMapper.customerToCustomerDTO(customerRepository.save(i));
            customerDTO2.setCustomerUrl("/api/v1/customer/" + i.getId());
            return customerDTO2;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
