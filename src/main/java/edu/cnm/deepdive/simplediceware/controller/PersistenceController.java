package edu.cnm.deepdive.simplediceware.controller;


import edu.cnm.deepdive.simplediceware.model.entity.Passphrase;
import edu.cnm.deepdive.simplediceware.model.entity.Word;
import edu.cnm.deepdive.simplediceware.model.service.PassphraseRepository;
import edu.cnm.deepdive.simplediceware.service.PassphraseGenerator;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passphrases")
public class PersistenceController {

  private static final String LENGTH_PARAMETER = "length";
  private static final int DEFAULT_LENGTH = Integer.parseInt("6");

  private  final PassphraseGenerator generator;
  private final PassphraseRepository repository;

  @Autowired
  public PersistenceController(
      PassphraseGenerator generator,
      PassphraseRepository repository) {
    this.generator = generator;
    this.repository = repository;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Passphrase post(@RequestBody Passphrase passphrase) {
    List<Word> words = passphrase.getWords();
    if (words.isEmpty()) {

      for (String text : generator.passphrase(DEFAULT_LENGTH)) {
        Word word = new Word();
        word.setText(text);
        word.setPassphrase(passphrase);
        words.add(word);
      }
    }
    int counter = 0;
    for (Word word : words) {
      word.setOrder(counter++);
    }
    return repository.save(passphrase);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Passphrase getByName(@RequestParam(required = true) String name) {
    return repository.findFirstByName(name)
        .orElseThrow(NoSuchElementException::new);
  }
}
