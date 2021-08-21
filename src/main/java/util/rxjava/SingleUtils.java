package util.rxjava;

import function.Function3;
import function.Function4;
import io.reactivex.rxjava3.core.Single;

import java.util.function.BiFunction;

public class SingleUtils {

    private SingleUtils() {
        super();
    }

    public static <A, B, R> Single<R> map2(Single<A> a, Single<B> b, BiFunction<A, B, R> f) {
        return a.flatMap( aR -> b.map( bR -> f.apply(aR, bR)));
    }

    public static <A, B, C, R> Single<R> map3(Single<A> a, Single<B> b, Single<C> c, Function3<A, B, C, R> f) {

        return a.flatMap(aR -> map2(b, c, (bR, cR) -> f.apply(aR, bR, cR)));
    }

    public static <A, B, C, D, R> Single<R> map4(Single<A> a, Single<B> b, Single<C> c, Single<D> d, Function4<A, B, C, D, R> f) {

        return a.flatMap(aR -> map3(b, c, d, (bR, cR, dR) -> f.apply(aR, bR, cR, dR)));
    }
}
