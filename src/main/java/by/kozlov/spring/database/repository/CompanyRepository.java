package by.kozlov.spring.database.repository;

import by.kozlov.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("update Company c set c.name = :name " +
            "where c.id = :id")
    int updateCompanyById(String name, Integer id);
    void deleteAllByNameStartingWith(String prefix);
}
