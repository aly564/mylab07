package it.unibo.inner;
import java.util.Iterator;
import java.util.function.Predicate;
import it.unibo.inner.api.IterableWithPolicy;

public class UseIterableWithPolicy<T> implements IterableWithPolicy<T>{

    private T[] array;
    private int size;
    Predicate<T> filter;

    /** Predicate<T> è un' interfaccia con una funzione test che restituisce un booleano.
     * In questo caso implemento un metodo filter che fa override di test, restituendo true in qualsiasi condizione.
     * Al posto di creare questo metodo da un'altra parte in un'altra classe, uso le classi anonime, cioè lo creo velocemente li passandolo
     * 0 sbatti
     * @param vector
     * @param filter
     * In questo modo, se non ho filtri, la funzione di default restituisce true, altrimenti uso il costruttore a 2 param
     */
    public UseIterableWithPolicy(final T[] vector){
        this(vector, new Predicate<T>(){

            @Override 
            public boolean test(T elem){
                return true;
            }
        });
    }

    

    public UseIterableWithPolicy(final T[] vector, Predicate<T> filter ){
        this.size = vector.length;
        this.array = vector;
        this.filter=filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter){
       this.filter = filter; //  Aggiorna la policy quando viene chiamato questo metodo!
       
    }

    public Iterator<T> iterator(){    //metodo che ritorna un oggetto di tipo iteratore
        return new InnerIterator();
    }

    private class InnerIterator implements Iterator<T>{     //classe iteratore che implementa l interfaccia di sistema iteratore

        private int current;

        public InnerIterator(){
            current = 0;
        }

        public void moveToValid(){
            while(current < size && !filter.test(array[current])){
                current++;
            }
        }

        @Override 
        public T next(){

            if(hasNext()){
                return (T) array[current++];
            }else{

                return null;
            } 
        
          
        }

        public boolean hasNext(){

            moveToValid();
            if(current >= size){
                return false;
            }
            else{
                return true;
            }
        }
    }

    
    
}
