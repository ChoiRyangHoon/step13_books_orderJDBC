package Dto;

public class RegBookDto {
	private int regNo;
	private String bookName ;
	private String bookWriter;
	private String bookpublisher;
	private String regDate;
	

	public RegBookDto() {}
	
	public RegBookDto(String bookName, String bookWriter, String bookpublisher) {
		super();
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookpublisher = bookpublisher;
	}
	
	public RegBookDto(int regNo, String bookName, String bookWriter, String bookpublisher, String regDate) {
		super();
		this.regNo = regNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookpublisher = bookpublisher;
		this.regDate = regDate;
	}
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookpublisher() {
		return bookpublisher;
	}
	public void setBookpublisher(String bookpublisher) {
		this.bookpublisher = bookpublisher;
	}
	
	
	@Override
	public String toString() {
		return bookName +" | "+ bookWriter +" | "+ bookpublisher;
	}
	
	
	

}







