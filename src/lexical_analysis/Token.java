package lexical_analysis;

public class Token {
	private String type;
	private String value;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Token(String type, String value){
		this.setType(type);
		this.setValue(value);
	}
	
}
