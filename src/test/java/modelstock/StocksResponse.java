package modelstock;

import java.util.List;

public class StocksResponse {
	private List<Stock> data = null;
	private int currentPage;
	private int size;
	private int totalElements;
	private int totalPages;
	public List<Stock> getData() {
		return data;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public int getSize() {
		return size;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public StocksResponse(List<Stock> data, int currentPage, int size, int totalElements, int totalPages) {
		super();
		this.data = data;
		this.currentPage = currentPage;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
	public StocksResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
