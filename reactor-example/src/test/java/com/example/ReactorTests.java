package com.example;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

public class ReactorTests {

    @Test
    public void empty() {
        Mono<String> emptyMono = Mono.empty();
        StepVerifier.create(emptyMono).verifyComplete();

        Flux<String> emptyFlux = Flux.empty();
        StepVerifier.create(emptyFlux).verifyComplete();
    }

    @Test
    public void initialized() {
        Mono<String> nonEmptyMono = Mono.just("Joel");
        StepVerifier.create(nonEmptyMono).expectNext("Joel").verifyComplete();

        Flux<String> nonEmptyFlux = Flux.just("John", "Mike", "Sarah");
        StepVerifier.create(nonEmptyFlux).expectNext("John", "Mike", "Sarah").verifyComplete();

        Flux<String> fluxFromIterable = Flux.fromIterable(Arrays.asList("Tom", "Hardy", "Bane"));
        StepVerifier.create(fluxFromIterable).expectNext("Tom", "Hardy", "Bane").verifyComplete();
    }

    @Test
    public void operations() {
        Mono<String> monoMap = Mono.just("James").map(s -> s.toLowerCase());
        StepVerifier.create(monoMap).expectNext("james").verifyComplete();

        Flux<String> fluxMapFilter = Flux.just("Joel", "Kyle")
                .filter(s -> s.toUpperCase().startsWith("K"))
                .map(s -> s.toLowerCase());
        StepVerifier.create(fluxMapFilter).expectNext("kyle").verifyComplete();
    }
}
