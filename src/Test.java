
public class Test {

	public static void main(String[] args) {
		
		int size=7,j=0,h=0;
		
		Board b1=new Board(size);
		
		for (Field[] field : b1.getBoard()) {
			j=0;
			for(Field i : field) {
				System.out.println(i.getP().toString()+" number ( "+h+ " , " +j+" )");
				j++;
			}
			h++;
		}

	}

}
