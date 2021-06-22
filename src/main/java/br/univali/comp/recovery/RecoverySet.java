package br.univali.comp.recovery;

import br.univali.comp.javacc.gen.Sintatico;

import java.util.HashSet;
import java.util.Iterator;

public class RecoverySet extends HashSet<Integer> {

    // Create new empty RecoverySet instance
    public RecoverySet() {
        super();
    }

    // Create new RecoverySet instance with one token
    public RecoverySet(int t) {
        this.add(t);
    }

    // Testes whether the token belongs is found within this set
    public boolean contains(int t) {
        return super.contains(t);
    }

    // Union of two RecoverySet instances, without modifying either
    // The return is a third, separate, RecoverySet with the resulting data
    // if RecoverySet "s" is null, returns null
    public RecoverySet union(RecoverySet s) {
        RecoverySet t = null;
        if (s != null) {
            t = (RecoverySet) this.clone();
            t.addAll(s);
        }
        return t;
    }

    // Returns another RecoverySet instance, without the specified token.
    public RecoverySet remove(int n) {
        RecoverySet t = (RecoverySet) this.clone();
        t.remove(n);
        return t;
    }

    public String toString() {
        Iterator<Integer> it = this.iterator();
        StringBuilder s = new StringBuilder();
        int k;

        while (it.hasNext()) {
            k = it.next();
            s.append(Sintatico.im(k)).append(" ");
        }
        return s.toString();
    }

}
