package enums;

public enum Country {

	UNITEDKINGDOM("United Kingdom"),
	UNITEDSTATESOFAMERICA("United States Of America"),
	CANADA("Canada");

	private String text;

	Country(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
