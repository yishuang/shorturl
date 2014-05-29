package myself.shorturl;

/**
 * Database table url_map and JSON model
 * */
public class URLMap {

	private int id;
	private String tinyURL;
	private String longURL;

	public URLMap(int id, String tinyURL, String longURL) {
		super();
		this.id = id;
		this.tinyURL = tinyURL;
		this.longURL = longURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTinyURL() {
		return tinyURL;
	}

	public void setTinyURL(String tinyURL) {
		this.tinyURL = tinyURL;
	}

	public String getLongURL() {
		return longURL;
	}

	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}

	public String toString() {
		return "[id: " + id + " tinyURL: " + tinyURL + " longURL: " + longURL
				+ "]";
	}
}
