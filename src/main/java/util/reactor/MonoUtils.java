package util.reactor;

import function.Function3;
import function.Function4;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

public class MonoUtils {

    private MonoUtils() {
        super();
    }

    public static <A, B, R> Mono<R> map2(Mono<A> a, Mono<B> b, BiFunction<A, B, R> f) {
        return a.flatMap( aR -> b.map( bR -> f.apply(aR, bR)));
    }

    public static <A, B, C, R> Mono<R> map3(Mono<A> a, Mono<B> b, Mono<C> c, Function3<A, B, C, R> f) {
        return a.flatMap(aR -> map2(b, c, (bR, cR) -> f.apply(aR, bR, cR)));
    }

    public static <A, B, C, D, R> Mono<R> map4(Mono<A> a, Mono<B> b, Mono<C> c, Mono<D> d, Function4<A, B, C, D, R> f) {
        return a.flatMap(aR -> map3(b, c, d, (bR, cR, dR) -> f.apply(aR, bR, cR, dR)));
    }
}
