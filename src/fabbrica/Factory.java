package fabbrica;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Factory {
    
    public <A,B> List<B> produce(List<? extends A> listA, Function<A,B> f) {
        LinkedList<B> products = new LinkedList<>();
        for(A a : listA) {
            products.add(f.apply(a));
        }
        return products;
    }


}
