package org.lognet.springboot.grpc.demo.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
    @Autowired
    private TranslationRepository translationRepository;

    public WordEntity addTranslation(String word, String fromLanguage, String text, String toLanguage) {
        WordEntity wordEntity = translationRepository.getByWordAndLanguage(word, fromLanguage);
        if(wordEntity == null) {
            wordEntity = new WordEntity(word, fromLanguage);
        }

        wordEntity.addTranslation(new TranslationEntity(toLanguage.toLowerCase(), text));

        translationRepository.save(wordEntity);
        return wordEntity;
    }
}
