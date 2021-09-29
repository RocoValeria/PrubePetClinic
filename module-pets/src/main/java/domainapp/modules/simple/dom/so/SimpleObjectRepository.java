package domainapp.modules.simple.dom.so;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleObjectRepository extends JpaRepository<PetOwner, Long> {

    List<PetOwner> findByNameContaining(final String name);

    PetOwner findByName(final String name);

}
