package edu.cnm.deepdive.simplediceware.model.service;

import edu.cnm.deepdive.simplediceware.model.entity.Passphrase;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassphraseRepository
    extends JpaRepository<Passphrase, Long> {

  Optional<Passphrase> findFirstByName(String name);




}
