package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
// 글쓰기 인증 체크 완료 
// 글쓰기 회원 인증
// 글쓰기 완료
// View를 DTO로 변경 - 코드 리팩토링 , 여기까지는 월요일
@RequiredArgsConstructor
@Controller
public class BoardsController {
	
	// 필요 부분 DI 시켜준다 -> DI란? 생성자를 주입해주기 위해 만들어줌.
	private final HttpSession session;
	private final BoardsDao boardsDao;
	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")
	

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		//1. 세션에 회원 세션값 저장되있음 -> 가져와야한다.
		// 세션 값은 오브젝트 타입으로 저장되어있다.  값을 인증하기 위해서는 Users타입으로 다운캐스팅이 필요하다.
		Users principal = (Users)session.getAttribute("principal");
		//2. 회원이 null이 아니라면 글쓰기 폼으로, 널이라면 데이터를 초기화(redirect) 해서 다시 로그인폼으로 이동. 
		if(principal == null) {
			return "redirect:/login";
		}
		//3. 데이터들을 넘겨주어야한다. 
		
		return "boards/writeForm";
	}
	
	@GetMapping({"/", "/boards"})
	public String getBoardList() {
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id) {
		return "boards/detail";
	}
	

}
