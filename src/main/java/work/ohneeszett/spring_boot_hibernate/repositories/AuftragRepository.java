package work.ohneeszett.spring_boot_hibernate.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import work.ohneeszett.spring_boot_hibernate.entities.Auftrag;

@Repository
@Transactional
public interface AuftragRepository extends CrudRepository<Auftrag, UUID> {

}
