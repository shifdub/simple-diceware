package edu.cnm.deepdive.simplediceware.controller;

import edu.cnm.deepdive.simplediceware.service.PassphraseGenerator;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DicewareController {

  private static final String LENGTH_PARAMETER = "length";
  private static final String DEFAULT_LENGTH = "6";

  private  final PassphraseGenerator generator;

  @Autowired
  public DicewareController(PassphraseGenerator generator) {
    this.generator = generator;
  }
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String[] get(
      @RequestParam(value = LENGTH_PARAMETER, defaultValue = DEFAULT_LENGTH) int length) {
    return generator.passphrase(length);

  }
  @GetMapping (produces = MediaType.TEXT_PLAIN_VALUE)
  public String get (
      @RequestParam(value = LENGTH_PARAMETER, defaultValue = DEFAULT_LENGTH) int length,
      @RequestParam String delimiter) {
    return String.join(delimiter, get(length));
  }



}

