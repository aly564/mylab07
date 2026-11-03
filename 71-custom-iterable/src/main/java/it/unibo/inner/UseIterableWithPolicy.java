package it.unibo.inner;
import java.util.Iterator;
import java.util.function.Predicate;
import it.unibo.inner.api.IterableWithPolicy;

public class UseIterableWithPolicy<T> implements IterableWithPolicy<T>{

    private T[] array;
    private int size;
  

    public UseIterableWithPolicy(final T[] vector){
        this.size = vector.length;
        this.array = vector;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter){
       
       
    }

    public Iterator<T> iterator(){    //metodo che ritorna un oggetto di tipo iteratore
        return new InnerIterator();
    }

    private class InnerIterator implements Iterator<T>{     //classe iteratore che implementa l interfaccia di sistema iteratore

        private int current;

        public InnerIterator(){
            current = 0;
        }

        @Override 
        public T next(){

            if(hasNext()){

                return (T) array[current++];
                
            }else{
                throw new java.util.NoSuchElementException();
            } 
           
        }

        public boolean hasNext(){

            if(current >= UseIterableWithPolicy.this.size){
                
                return false;

            }else{

                return true;
            }
            
        }
    }

    
    
}
