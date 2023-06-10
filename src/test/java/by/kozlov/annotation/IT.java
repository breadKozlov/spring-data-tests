package by.kozlov.annotation;

import by.kozlov.SpringDataApplicationRunner;
import by.kozlov.TestSpringDataApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {TestSpringDataApplicationRunner.class,
        SpringDataApplicationRunner.class})
@Transactional
@Sql({"/sql/init.sql","/sql/data.sql"})
public @interface IT {
}
