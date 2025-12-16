package com.example.springbootserver.personAlternateName;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonAlternateNameRepository extends JpaRepository<PersonAlternateName, PersonAlternateNameId> {
        List<PersonAlternateName> findByPerson_PersonMalId(Integer id);
}
