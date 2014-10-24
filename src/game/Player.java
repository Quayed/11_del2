package game;

public class Player {
	private String name;
	final private int id;
	private int field;
	
	public Player(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setField(int field) {
		this.field = field;
	}
	
	public int getField() {
		return this.field;
	}
}
