package site.metacoding.red.web.dto.response.boards;

public class PagingDto {
	private Integer startNum;
	private Integer totalCount;// total 카운트를 알아야 total 페이지가 나온다. 
	private Integer totalPage;//23페이지면 23/한페이지당 갯수를 해야한다. 
	private Integer currentPage;
	private boolean isLast;
	private boolean isFirst;
	
}
