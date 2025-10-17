package edu.ser222.m03_04;

/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 */
import java.util.LinkedList;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {
    private int N;
    private int M;
    @SuppressWarnings("rawtypes")
    private LinkedList<Entry>[] st;


    /*
     *Entry class/constructor declared to
     *initialize LinkedList array of generic types
     */
    public class Entry<EntryKey, EntryValue>{
	private final EntryKey key;
	private EntryValue value;

	public Entry(EntryKey key, EntryValue val) {
	    this.key = key;
	    this.value = val;
	}
    }
    //any constructors must be made public
    @SuppressWarnings("unchecked")
    public CompletedTwoProbeChainHT(int size) {
	this.M = size;
	this.N = 0;
	st = new LinkedList[M];
	for(int i = 0; i < M; i++) {
	    st[i] = new LinkedList<>();
	}
    }

    public CompletedTwoProbeChainHT() {
   	this(997);
       }

    @Override
    public int hash(Key key) {
       return ((key.hashCode() & 0x7fffffff) % M);
    }

    @Override
    public int hash2(Key key) {
        return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    /*
     * Method iterates through hash table that utilizes
     * two hash functions ('hash' & 'hash2').  If hash
     * key equals key parameter. Sets the value of key
     * to passed in val parameter.
     */
    public void put(Key key, Value val) {

	for (int i = 0; i < st[hash(key)].size(); i++)
	    if((st[hash(key)].get(i).key).equals(key)) {
		st[hash(key)].get(i).value = val;
		return;
	    }
	for (int i = 0; i < st[hash2(key)].size(); i++)
	    if((st[hash2(key)].get(i).key).equals(key)) {
		st[hash2(key)].get(i).value = val;
		return;
	    }


	if (st[hash(key)].size() < st[hash2(key)].size())
	    st[hash(key)].add(new Entry(key, val));
	else st[hash2(key)].add(new Entry(key, val));

	N++;


    }
    @SuppressWarnings("unchecked")
    @Override
    /*
     * Method iterates through hash table that utilizes
     * two hash functions ('hash' & 'hash2').  If hash
     * key equals passed in key parameter. returns cast value
     * of key.
     */
    public Value get(Key key) {

	for(int i = 0; i < st[hash(key)].size(); i++)
	    if (st[hash(key)].get(i).key.equals(key)){
		return (Value) st[hash(key)].get(i).value;
	}
	for(int i = 0; i < st[hash2(key)].size(); i++)
	    if (st[hash2(key)].get(i).key.equals(key)){
		return (Value) st[hash2(key)].get(i).value;
	}

	return null;

    }

    @Override
    /*
     * Method iterates through hash table that utilizes
     * two hash functions ('hash' & 'hash2').  If hash
     * key equals passed in key parameter remove key at
     * 'i' and decrements N.
     */
    public void delete(Key key) {
	for(int i = 0; i < st[hash(key)].size(); i++) {
	    if((st[hash(key)].get(i).key).equals(key)) {
		st[hash(key)].remove(i);
		N--;
	    }
	}
	for(int i = 0; i < st[hash2(key)].size(); i++)
	    if((st[hash2(key)].get(i).key).equals(key)) {
		st[hash2(key)].remove(i);
		N--;
	    }
    }

    @Override
    public boolean contains(Key key) {
	if(get(key) == null)
	    return false;
	return true;
    }

    @Override
    public boolean isEmpty() {
	if(N == 0)
	    return true;
	return false;
    }

    @Override
    public int size() {
       return N;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<Key> keys() {
       LinkedList<Key> iterList = new LinkedList<>();
       for(int i = 0; i < M; i++)
	   for(int j = 0; j < st[i].size(); j++)
	       iterList.add((Key) st[i].get(j).key);
       return iterList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        //return M;

        return M;
    }

    @Override
    public int getChainSize(int i) {
        //TODO. We suggest something like:
        //return entries[i].size();

        return st[i].size();
    }
}
