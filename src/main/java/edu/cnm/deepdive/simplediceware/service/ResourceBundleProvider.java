package edu.cnm.deepdive.simplediceware.service;

import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceBundleProvider implements WordProvider {
  private final ResourceBundle bundle;
  @Autowired
  public ResourceBundleProvider(ResourceBundle bundle) {
    this.bundle = bundle;
  }

  @Override
  public Collection<String> words() {
    return bundle.keySet().stream()
        .map(bundle::getString)
        .collect(Collectors.toList());
  }
}
