package edu.cnm.deepdive.simplediceware.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Passphrase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "passphrase_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(nullable = false, unique = true, length = 20)
  private String name;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  @NonNull
  @OneToMany(mappedBy = "passphrase", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("order ASC")
  private final List<Word> words = new ArrayList<>();

  public Long getId() {
    return id;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  @NonNull
  public List<Word> getWords() {
    return words;
  }

}
