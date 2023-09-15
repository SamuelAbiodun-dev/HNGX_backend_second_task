package hngTask2.backend.data.repositories;

import hngTask2.backend.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
