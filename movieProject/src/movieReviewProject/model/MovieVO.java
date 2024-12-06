package movieReviewProject.model;

import java.sql.Date;

public class MovieVO {
	private int movieId;
	private String title;
	private Date releaseDate;
	private int duration;
	private String genre;
	
	public MovieVO() {
		super();
	}
	public MovieVO(int movieId, String title, Date releaseDate, int duration, String genre) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = genre;
	}
	public MovieVO(String title, Date releaseDate, int duration, String genre) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = genre;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "MovieVO [movieId=" + movieId + ", title=" + title + ", releaseDate=" + releaseDate + ", duration="
				+ duration + ", genre=" + genre + "]";
	}
	
	
}
