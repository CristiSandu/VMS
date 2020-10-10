import java.util.*;

public class ArrayMap<K,V> extends AbstractMap<K,V> {

    ArrayList <ArrayMapEntry<K,V> > list;

    public ArrayMap(){
        this.list=new ArrayList<ArrayMapEntry<K,V> >();

    }

    public class ArrayMapEntry<K,V> implements Map.Entry<K,V>{
        K key;
        V valoare;
        ArrayMapEntry(K key, V valoare){
            this.key=key;
            this.valoare=valoare;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.valoare;
        }

        public V setValue(V value){
            this.valoare=value;
            return valoare;
        }

        public String toString(){
            return (key+"--"+valoare);

        }

        public boolean equals(Object o){
            if (((ArrayMapEntry)o).getKey() == this.getKey())
                return  true;
            return false;
        }

        public int hashCode(){
            return super.hashCode();
        }
    }

    public Set<Entry<K, V>> entrySet() {
        HashSet has = new HashSet();
        Iterator itr= list.iterator();
        while (itr.hasNext()){
            has.add(itr.next());
        }
        return has;
    }

    public int size(){
        return list.size();
    }

    public V put(K k1, V v1){
        ArrayMapEntry arr= new ArrayMapEntry((K)k1, (V)v1);
        int ok=0;
        for (ArrayMapEntry i : list){
            if (i.getKey() == k1){
                i.setValue(v1);
                ok=1;
            }
        }
        if (ok ==0){
            list.add(arr);
        }
        return v1;
    }

    public boolean containsKey(Object key){
        for (ArrayMapEntry i : list){
            if(i.getKey()==(K)key)
                return true;
        }
        return false;
    }

    public V get(Object key){

        for (int i=0; i<this.list.size();i++)
        {
            if(list.get(i).getKey()==(K)key)
            {
                return list.get(i).getValue();
            }
        }

        return null;
    }
}
