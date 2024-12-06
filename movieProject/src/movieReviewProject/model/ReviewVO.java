package movieReviewProject.model;

public class ReviewVO {
	private int review_id;
	private int watch_id;
	private int rating;
    private String comment;
    
	public ReviewVO() {}

	public ReviewVO(int review_id, int watch_id, int rating, String comment) {
		super();
		this.review_id = review_id;
		this.watch_id = watch_id;
		this.rating = rating;
		this.comment = comment;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getWatch_id() {
		return watch_id;
	}

	public void setWatch_id(int watch_id) {
		this.watch_id = watch_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ReviewVO [review_id=" + review_id + ", watch_id=" + watch_id + ", rating=" + rating + ", comment="
				+ comment + "]";
	}
    
}
