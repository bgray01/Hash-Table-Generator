package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with quadratic probing.
 */
public class CompletedQuadProbingHT<Key, Value> extends CompletedLinearProbingHT<Key, Value> {

    private int M;

    // any constructors must be made public
    public CompletedQuadProbingHT(){
    this(997);
    }

    public CompletedQuadProbingHT(int size){
    this.M = size;
    }


    @Override
    public int hash(Key key, int i) {
	return ((key.hashCode() & 0x7fffffff) + i * i) % M;
    }
   
}
