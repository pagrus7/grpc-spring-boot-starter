package org.lognet.springboot.grpc.demo.translator;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.translator.TranslatorGrpc;
import io.grpc.examples.translator.TranslatorOuterClass;
import io.grpc.examples.translator.TranslatorOuterClass.Language;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lognet.springboot.grpc.demo.DemoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static io.grpc.examples.translator.TranslatorOuterClass.Language.ENGLISH;
import static io.grpc.examples.translator.TranslatorOuterClass.Language.RUSSIAN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {"grpc.port=8081",
                "spring.jpa.properties.hibernate.current_session_context_class=org.springframework.orm.hibernate5.SpringSessionContext",
                "spring.aop.proxy-target-class=true"    // <<< Important! So that proxied class propagates to the GRPC server
        })
public class TranslatorIntegrationTest {
    private ManagedChannel channel;
    private TranslatorGrpc.TranslatorBlockingStub client;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setupChannel() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext(true).build();
        client = TranslatorGrpc.newBlockingStub(channel);
    }

    @After
    public void shutdownChannel() {
        channel.shutdownNow();
    }

    @Test
    public void contextStarts() {
    }

    @Test
    public void newTranslationsGetPersisted() {
        // given
        TranslatorOuterClass.AddTranslationRequest request1 =
                addTranslationRequest(ENGLISH, "hello", RUSSIAN, "привет");
        TranslatorOuterClass.AddTranslationRequest request2 =
                addTranslationRequest(ENGLISH, "hello", RUSSIAN, "здравствуйте");

        // when
        TranslatorOuterClass.KnownTranslationsMessage response1 = client.addTranslation(request1);
        TranslatorOuterClass.KnownTranslationsMessage response2 = client.addTranslation(request2);

        // then
        assertThat(response1.getKnownTranslationsCount(), is(1));
        assertThat(response2.getKnownTranslationsCount(), is(2));

        assertTranslationPersisted(ENGLISH, "hello", RUSSIAN, "привет");
        assertTranslationPersisted(ENGLISH, "hello", RUSSIAN, "здравствуйте");
    }

    public void assertTranslationPersisted(Language wordLanguage, String word, Language translationLanguage, String expectedText) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select t.* from " +
                        "   translation t, word w " +
                        "where w.language=?" +
                        "   and w.word=?" +
                        "   and t.language=?" +
                        "   and t.text=?",
                wordLanguage.name().toLowerCase(),
                word,
                translationLanguage.name().toLowerCase(),
                expectedText);
        assertThat(String.format("Could not find '%s'", expectedText), list.size(), is(1));
    }

    private TranslatorOuterClass.AddTranslationRequest addTranslationRequest(Language fromLanguage, String word, Language toLanguage, String translationText) {
        return TranslatorOuterClass.AddTranslationRequest.newBuilder().setFromLanguage(fromLanguage).setWord(word).setToLanguage(toLanguage).setTranslation(translationText).build();
    }
}
