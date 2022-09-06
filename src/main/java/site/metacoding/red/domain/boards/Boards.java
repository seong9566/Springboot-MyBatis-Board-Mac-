package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer userId;
	private Timestamp createdAt;
	
	//생성자를 만들면 기본 디폴트 생성자도 함게 필요하다.
	//Write에 사용
	public Boards(String title,String content,Integer userId)
	{
		this.title = title;
		this.content = content;
		this.userId = userId;
	}
}
