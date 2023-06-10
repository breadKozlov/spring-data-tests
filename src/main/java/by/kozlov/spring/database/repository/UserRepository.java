package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.database.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRoleAndBirthDateIsBetween(Role role, LocalDate date1, LocalDate date2);

    List<User> findFirst4By(Sort sort);

    @Query("FROM User u" +
            " WHERE u.role = :role")
    List<User> findAllBy(Role role, Pageable pageable);

//    int updateRole(Role role, Sort sort, Pageable pageable, Long... ids);
}
