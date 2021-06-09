package fabbrica;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Factory {
    

    // Function può avere ? super A ma non ? extends A
    // Function può avere ? extends B ma non ? super B
    // In sintesi i parametri di un metodo possono essere sussunti (anche se questo java non lo permette nei metodi)
    // Il valore di ritorno di un metodo può essere subsunto
    public <A,B> List<B> produce(List<? extends A> listA, Function<A,B> f) {
        LinkedList<B> products = new LinkedList<>();
        for(A a : listA) {
            products.add(f.apply(a));
        }
        return products;
    }


}
