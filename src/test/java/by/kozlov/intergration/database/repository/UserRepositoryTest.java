package by.kozlov.intergration.database.repository;

import by.kozlov.annotation.IT;
import by.kozlov.spring.database.entity.Role;
import by.kozlov.spring.database.repository.UserRepository;
import by.kozlov.spring.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void findAllByRoleAndBirthDateIsBetween() {

        var resultAdmins = userRepository.findAllByRoleAndBirthDateIsBetween(Role.ADMIN,
                LocalDate.of(1980,1,1),
                LocalDate.of(1990,12,31));
        assertThat(resultAdmins).hasSize(2);
        var lastnamesAdmins = resultAdmins.stream().map(User::getLastname).toList();
        assertThat(lastnamesAdmins).contains("Ivanov","Smith");
    }

    @Test
    void findFirst4BySortTest() {

        var sort = Sort.by("id").reverse();// for id`s
        var users = userRepository.findFirst4By(sort);
        var namesUsers = users.stream().map(User::getFirstname).toList();

        assertThat(users).hasSize(4);
        assertThat(namesUsers).contains("Kate", "Vlad", "Sveta", "Petr");
    }

    @Test
    void findFirst4BySortBirthTest() {

        var sort1 = Sort.by("birthDate");//for birthDate
        var users = userRepository.findFirst4By(sort1);

        assertFalse(users.isEmpty());
        assertThat(users).hasSize(4);
        var birthDateUsers = users.stream().map(User::getBirthDate).map(String::valueOf).toList();
        assertThat(birthDateUsers).contains("1984-03-14", "1984-03-14", "1990-01-10", "1995-10-19");
    }

    @Test
    void findFirst4BySortBirthAndFioTest() {

        var sort2 = Sort.by("birthDate").and(Sort.by("firstname"))//for birthDate and fio
                .and(Sort.by("lastname"));
        var users = userRepository.findFirst4By(sort2);
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(4);
        var namesLastnamesUsers = users.stream().map(it -> it.getFirstname() + " " + it.getLastname()).toList();
        assertThat(namesLastnamesUsers).contains("Kate Smith", "Vlad Vladikov", "Ivan Ivanov", "Petr Petrov");

    }
    @Test
    void findAllByRoleTest() {

        var admins = userRepository.findAllBy(Role.ADMIN, Pageable.unpaged());
        assertThat(admins).hasSize(2);
        var users = userRepository.findAllBy(Role.USER, Pageable.unpaged());
        assertThat(users).hasSize(3);

    }

    @Test
    void findAllByRoleAndSort() {

        var pageable = PageRequest.of(0, 2, Sort.by("birthDate"));
        var users = userRepository.findAllBy(Role.ADMIN, pageable);
        assertThat(users).hasSize(2);
        assertEquals(users.stream().map(User::getId).collect(Collectors.toList()), List.of(5L, 1L));

    }

    @Test
    void findAllByRoleAndPage() {

        var page = PageRequest.of(0, 4);
        var users = userRepository.findAllBy(Role.USER, page);
        assertThat(users).hasSize(3);
        page = PageRequest.of(2,1);
        users = userRepository.findAllBy(Role.USER, page);
        assertThat(users).hasSize(1);
        page = PageRequest.of(3,1);
        users = userRepository.findAllBy(Role.USER, page);
        assertThat(users).hasSize(0);

    }
}
