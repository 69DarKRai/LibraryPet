package org.library.util;

import org.library.dao.PersonDAO;
import org.library.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;

        if(personDAO.getPersonName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Это имя уже занято...");
        }
    }
}
