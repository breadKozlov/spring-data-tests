package by.kozlov.intergration.database.repository;

import by.kozlov.annotation.IT;
import by.kozlov.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@AllArgsConstructor
public class CompanyRepositoryTest {

    private final CompanyRepository companyRepository;

    private final EntityManager entityManager;

    @Test
    void updateCompanyById() {

        var company = companyRepository.findById(2).orElseThrow();
        assertEquals("Meta", company.getName());
        System.out.println(company.getName());
        var result = companyRepository.updateCompanyById("Pasha",2);
        assertEquals(1, result);
        var sameAdmin = companyRepository.findById(2).orElseThrow();
        assertEquals("Pasha", sameAdmin.getName());
    }

    @Test
    void deleteAllByNameStartingWith() {

        var maybeCompany = companyRepository.findById(3);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(it -> companyRepository.deleteAllByNameStartingWith("A"));
        entityManager.flush();
        assertTrue(companyRepository.findById(3).isEmpty());
    }
}
