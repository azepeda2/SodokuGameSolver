
public class Square {
	
	private boolean original;
	private boolean set;
	private int value;
	
	public Square() {
		this.original = false;
		this.set = false;
		this.value = 0;
	}

	public boolean getOriginal() {
		return this.original;
				
	}
	
	public boolean getSet() {
		return this.set;
	}
	
	public void setOriginal(boolean original) {
		this.original = original;
	}
	
	public void setSet(boolean set) {
		this.set= set;
	}
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
    }

	public String toString() {
		if(this.value == 0) {
			return " ";
		} else {
			return (Integer.toString(this.value));
		}
	}
}
