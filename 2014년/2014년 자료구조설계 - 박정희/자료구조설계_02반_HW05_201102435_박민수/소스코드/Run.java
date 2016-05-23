package HW05;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable hash = new HashTable(17);
		HashTable2 hash2 = new HashTable2(17);
		HashTable3 hash3 = new HashTable3(17);
		
		hash.put("AT", "AT");
		hash.put("BE", "BE");
		hash.put("DE", "DE");
		hash.put("DK", "DK");
		hash.put("ES", "ES");
		hash.put("FR", "FR");
		hash.put("IT", "IT");
		hash.put("LU", "LU");
		hash.put("SE", "SE");
		
		hash2.put("AT", "AT");
		hash2.put("BE", "BE");
		hash2.put("DE", "DE");
		hash2.put("DK", "DK");
		hash2.put("ES", "ES");
		hash2.put("FR", "FR");
		hash2.put("IT", "IT");
		hash2.put("LU", "LU");
		hash2.put("SE", "SE");
		
		hash3.put("AT", "AT");
		hash3.put("BE", "BE");
		hash3.put("DE", "DE");
		hash3.put("DK", "DK");
		hash3.put("ES", "ES");
		hash3.put("FR", "FR");
		hash3.put("IT", "IT");
		hash3.put("LU", "LU");
		hash3.put("SE", "SE");
	
		
		System.out.println("Liner Probing:");
		System.out.print(hash.get("AT"));
		hash.printHash("AT");
		System.out.println();
		System.out.print(hash.get("BE"));
		hash.printHash("BE");
		System.out.println();
		System.out.print(hash.get("DE"));
		hash.printHash("DE");
		System.out.println();
		System.out.print(hash.get("DK"));
		hash.printHash("DK");
		System.out.println();
		System.out.print(hash.get("ES"));
		hash.printHash("ES");
		System.out.println();
		System.out.print(hash.get("FR"));
		hash.printHash("FR");
		System.out.println();
		System.out.print(hash.get("IT"));
		hash.printHash("IT");
		System.out.println();
		System.out.print(hash.get("LU"));
		hash.printHash("LU");
		System.out.println();
		System.out.print(hash.get("SE"));
		hash.printHash("SE");
		System.out.println();
		System.out.println(hash.collisions()+" collisions");
		
		System.out.println("Quaratic Probing:");
		System.out.print(hash2.get("AT"));
		hash2.printHash("AT");
		System.out.println();
		System.out.print(hash2.get("BE"));
		hash2.printHash("BE");
		System.out.println();
		System.out.print(hash2.get("DE"));
		hash2.printHash("DE");
		System.out.println();
		System.out.print(hash2.get("DK"));
		hash2.printHash("DK");
		System.out.println();
		System.out.print(hash2.get("ES"));
		hash2.printHash("ES");
		System.out.println();
		System.out.print(hash2.get("FR"));
		hash2.printHash("FR");
		System.out.println();
		System.out.print(hash2.get("IT"));
		hash2.printHash("IT");
		System.out.println();
		System.out.print(hash2.get("LU"));
		hash2.printHash("LU");
		System.out.println();
		System.out.print(hash2.get("SE"));
		hash2.printHash("SE");
		System.out.println();
		System.out.println(hash2.collisions()+" collisions");
		
		System.out.println("Double Probing:");
		System.out.print(hash3.get("AT"));
		hash3.printHash("AT");
		System.out.println();
		System.out.print(hash3.get("BE"));
		hash3.printHash("BE");
		System.out.println();
		System.out.print(hash3.get("DE"));
		hash3.printHash("DE");
		System.out.println();
		System.out.print(hash3.get("DK"));
		hash3.printHash("DK");
		System.out.println();
		System.out.print(hash3.get("ES"));
		hash3.printHash("ES");
		System.out.println();
		System.out.print(hash3.get("FR"));
		hash3.printHash("FR");
		System.out.println();
		System.out.print(hash3.get("IT"));
		hash3.printHash("IT");
		System.out.println();
		System.out.print(hash3.get("LU"));
		hash3.printHash("LU");
		System.out.println();
		System.out.print(hash3.get("SE"));
		hash3.printHash("SE");
		System.out.println();
		System.out.println(hash3.collisions()+" collisions");

	}

}
