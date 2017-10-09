package org.lognet.springboot.grpc.demo.translator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "word", uniqueConstraints = @UniqueConstraint(columnNames = {"word", "language"}))
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private long id;
    private String word;
    private String language;
    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    private Set<TranslationEntity> translations = new HashSet<>();

    public WordEntity() {
    }

    public WordEntity(String word, String language) {
        setWord(word);
        setLanguage(language);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language.toLowerCase();
    }

    public Set<TranslationEntity> getTranslations() {
        return translations;
    }

    void setTranslations(Set<TranslationEntity> translations) {
        this.translations = translations;
    }

    public void addTranslation(TranslationEntity translation) {
        translations.add(translation);
        translation.setWord(this);
    }
}
