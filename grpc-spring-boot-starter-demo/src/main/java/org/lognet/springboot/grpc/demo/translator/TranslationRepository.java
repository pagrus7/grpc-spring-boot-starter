package org.lognet.springboot.grpc.demo.translator;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TranslationRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(WordEntity word) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(word);
    }

    public WordEntity getByWordAndLanguage(String word, String language) {
        Session session = sessionFactory.getCurrentSession();
        WordEntity answer = (WordEntity) session.createCriteria(WordEntity.class)
                .add(Restrictions.eq("word", word.toLowerCase()))
                .add(Restrictions.eq("language", language.toLowerCase()))
                .uniqueResult();
        return answer;
    }
}
