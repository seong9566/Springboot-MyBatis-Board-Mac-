package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Setter
@Getter
public class WriteDto {
	private String title;
	private String content;
	private Integer userId;
	
	public Boards toEntity(Integer userId) {
		Boards boards = new Boards();
		boards.setTitle(this.title);
		boards.setContent(this.content);
		//유저의 아이디는 세션에서 받아온다. 
		this.userId = userId;
		return boards;
	}

}
