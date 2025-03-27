package mk.ukim.finki.eimt_lab_group_B.repository;

import mk.ukim.finki.eimt_lab_group_B.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
