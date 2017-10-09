package org.lognet.springboot.grpc.demo.translator;

import javax.persistence.*;

@Entity
@Table(name = "translation")
public class TranslationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "translation_id")
    private long id;

    @ManyToOne
    private WordEntity word;
    private String language;
    private String text;

    public TranslationEntity() {
    }

    public TranslationEntity(String language, String text) {
        setText(text);
        setLanguage(language);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WordEntity getWord() {
        return word;
    }

    public void setWord(WordEntity word) {
        this.word = word;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language.toLowerCase();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.toLowerCase();
    }
}
