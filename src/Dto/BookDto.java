package Dto;

public class BookDto {
	private String booksId;
	private String booksName;
	private int booksPrice;
	private int stock;
	private String regDate;
	  
	public BookDto() {}
	    
	public BookDto(String booksId, String booksName, int booksPrice, int stock) {
		super();
		this.booksId = booksId;
		this.booksName = booksName;
		this.booksPrice = booksPrice;
		this.stock = stock;
	}

	public String getBooksId() {
		return booksId;
	}

	public void setBooksId(String booksId) {
		this.booksId = booksId;
	}

	public String getBooksName() {
		return booksName;
	}

	public void setBooksName(String booksName) {
		this.booksName = booksName;
	}

	public int getBooksPrice() {
		return booksPrice;
	}

	public void setBooksPrice(int booksPrice) {
		this.booksPrice = booksPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return booksId +" | " + booksName +" | " + booksPrice +" | " + stock  ;
	}
		 
		 
	@Override
	public int hashCode() {
		return booksId.hashCode();
	}
		 
	@Override
	public boolean equals(Object obj) {	
		BookDto other = (BookDto) obj;
		if(booksId.equals(other.booksId)) {
			return true;
		}else {
			return false;
		}			
	}

	

}
