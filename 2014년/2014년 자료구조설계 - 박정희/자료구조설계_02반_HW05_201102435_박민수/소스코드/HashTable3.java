package HW05;

public class HashTable3 implements Map {
	private Entry[] entries;
	private int size,used;
	private float loadFactor;
	private final Entry NIL = new Entry(null,null);
	private int collisions =0 ;
	
	/* 생성자 */
	public HashTable3(int capacity, float loadFactor){
		entries = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	
	public HashTable3(int capacity){
		this(capacity,0.75f);
	}
	
	public HashTable3(){
		this(10);
	}
	
	
	//
	public Object get(Object key){
		int h = hash(key);
		int d = hash2(key);
		for(int i= 0; i <entries.length; i++){
			int j = nextProbe(h,d,i);
			Entry entry = entries[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) return entry.value;
		}
		return null;
	}
	
	//
	public Object put(Object key, Object value){
		if(used>loadFactor*entries.length) rehash();
		int h = hash(key);
		int d = hash2(key);
		for(int i=0; i<entries.length; i++){
			int j = nextProbe(h,d,i);
			Entry entry = entries[j];
			if(entry == null){
				entries[j] = new Entry(key,value);
				++size;
				++used;
				return null;
			}
			if (entry == NIL) continue;
			if (entry.key.equals(key)){
				Object oldValue = entry.value;
				entries[j].value = value;
				return oldValue;
			}
		}
		return null;
	}
	
	//
	public Object remove(Object key){
		int h = hash(key);
		int d = hash2(key);
		for (int i= 0; i<entries.length; i++){
			int j= nextProbe(h,d,i);
			Entry entry = entries[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)){
				Object oldValue = entry.value;
				entries[j] = NIL;
				--size;
				return oldValue;
			}
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	public int collisions(){
		return collisions;
	}
	
	public void printHash(Object key){
		
			int h = hash(key);
			int d = hash2(key);
			for(int i= 0; i <entries.length; i++){
				int j = nextProbe(h,d,i);
				
				Entry entry = entries[j];
				System.out.print("->"+j);
				if(entry == null) break;
				if(entry == NIL) continue;
				if(entry.key.equals(key)) break;
				++this.collisions;	
			}
			
	}
	
	
	private int hash(Object key){
		if(key == null) throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % entries.length;
	}
	private int hash2(Object key){
		if(key == null) throw new IllegalArgumentException();
		return 1+(key.hashCode() & 0x7FFFFFFF) % (entries.length-1);
	}
	//이중조사 
	private int nextProbe(int h,int d, int i){
		return (h+d*i) % entries.length;
	}
	
	private void rehash(){
		Entry[] oldEntries = entries;
		entries = new Entry[2*oldEntries.length+1];
		for(int k= 0; k < oldEntries.length; k++){
			Entry entry = oldEntries[k];
			if(entry == null || entry == NIL) continue;
			int h = hash(entry.key);
			int d = hash2(entry.key);
			for (int i=0 ; i <entries.length; i++){
				int j = nextProbe(h,d,i);
				if(entries[j] == null){
					entries[j] = entry;
					break;
				}
			}
		}
		used = size;
	}
	
	private class Entry{
		Object key, value;
		Entry(Object k, Object v){
			key = k;
			value = v;
		}
	}

	
}
