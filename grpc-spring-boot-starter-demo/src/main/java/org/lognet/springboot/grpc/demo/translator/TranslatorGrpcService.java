package org.lognet.springboot.grpc.demo.translator;

import io.grpc.examples.translator.TranslatorGrpc;
import io.grpc.examples.translator.TranslatorOuterClass;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@GRpcService
@Transactional
public class TranslatorGrpcService extends TranslatorGrpc.TranslatorImplBase {
    @Autowired
    private TranslatorService translatorService;

    @Override
    public void addTranslation(TranslatorOuterClass.AddTranslationRequest request, StreamObserver<TranslatorOuterClass.KnownTranslationsMessage> responseObserver) {
        WordEntity wordEntity = translatorService.addTranslation(request.getWord(), request.getFromLanguage().name(), request.getTranslation(), request.getToLanguage().name());

        TranslatorOuterClass.KnownTranslationsMessage.Builder responseBuilder = TranslatorOuterClass.KnownTranslationsMessage.newBuilder();
        wordEntity.getTranslations().stream().map(this::toGrpcTranslation).forEach(responseBuilder::addKnownTranslations);

        TranslatorOuterClass.KnownTranslationsMessage response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private TranslatorOuterClass.WordTranslation toGrpcTranslation(TranslationEntity translationEntity) {
        TranslatorOuterClass.WordTranslation translation = TranslatorOuterClass.WordTranslation
                .newBuilder()
                .setText(translationEntity.getText())
                .setLanguage(TranslatorOuterClass.Language.valueOf(translationEntity.getLanguage().toUpperCase()))
                .build();
        return translation;
    }

    @Override
    public void getTranslations(TranslatorOuterClass.GetTranslationRequest request, StreamObserver<TranslatorOuterClass.KnownTranslationsMessage> responseObserver) {
        // implementation omitted, since method above is sufficient for demonstration purposes
        super.getTranslations(request, responseObserver);
    }
}
