package med.hospital.api.domain.usersapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserAPIRepository extends JpaRepository<UserAPI, Long> {
    UserDetails findByLogin(String username);
}
