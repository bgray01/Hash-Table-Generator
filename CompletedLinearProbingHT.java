package edu.ser222.m03_04;

import java.util.LinkedList;

public class CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {
    private int N;
    private int M;
    private final Entry<Key, Value>[] st;

    public static class Entry<Key, Value> {
	private Key key;
	private Value value;

	public Entry(Key key, Value value) {
	    this.key = key;
	    this.value = value;
	}
    }

    /**
     * Instantiates a new Linear probing ht.
     */
    public CompletedLinearProbingHT() {
	this(997);
    }

    /**
     * Instantiates a new Linear probing ht.
     *
     * @param size the size
     */
    public CompletedLinearProbingHT(int size) {
	this.M = size;
	this.N = 0;
	this.st = new Entry[M];

    }

    @Override
    public int hash(Key key, int i) {
	return ((key.hashCode() & 0x7fffffff) + i) % M;
    }

    @Override
    public void put(Key key, Value val) {
	int i = 0;
	for (hash(key, i); st[i] != null; i = (i + 1) % M) {
	    if (st[i].key.equals(key)) {
		st[i].value = val;
		return;
	    }
	}
	st[i] = new Entry<Key, Value>(key, val);
	N++;

    }

    @Override
    public Value get(Key key) {
	int i = 0;
	for (hash(key, i); st[i] != null; i = (i + 1) % M) {
	    if (st[i].key.equals(key)) {
		return st[i].value;
	    }
	}
	return null;
    }

    @Override
    public void delete(Key key) {
	int i = 0;
	for (hash(key, i); st[i] != null; i = (i + 1) % M) {
	    if (st[i].key.equals(key)) {
		for (int j = i + 1; st[i] != null; j = (j + 1) % M) {
		    st[i] = st[j];
		    i = (i + 1) % M;
		}
		st[i] = null;
		N--;
	    }
	}
    }

    @Override
    public boolean contains(Key key) {
	return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
	return N == 0;
    }

    @Override
    public int size() {
	return N;
    }

    @Override
    public Iterable<Key> keys() {
	LinkedList<Key> list = new LinkedList<Key>();
	for (int i = 0; i < M; i++) {
	    if (st[i] != null) {
		list.add(st[i].key);
	    }
	}
	return list;
    }

    @Override
    public int getM() {

	return M;
    }

    @Override
    public Object getTableEntry(int i) {
	return null;
    }
}
