package adrian.books.dto;

public class CommentDto {
	private Long id;

	private String publishtext;

	private int punctuation;


	public CommentDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPublishtext() {
		return publishtext;
	}

	public void setPublishtext(String publishtext) {
		this.publishtext = publishtext;
	}

	public int getPunctuation() {
		return punctuation;
	}

	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}
}
