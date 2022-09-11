package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@RequiredArgsConstructor
@Controller
public class UsersController {
	
	private final HttpSession session; // 스프링이 서버시작시에 IoC 컨테이너에 보관함.
	private final UsersDao usersDao;

	@GetMapping("/users/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPs = usersDao.findById(id);
		Users principal = (Users)session.getAttribute("principal");
		
		// 비정상 요청 체크
		if(usersPs == null) {
			return "errors/badPage";
		}
		
		if(principal == null) {
			return "redirect:/loginForm";
		}
		if(usersPs.getId() != principal.getId()) {
			return "errors/badPage";
		}
		model.addAttribute("users", usersPs);
		return "users/updateForm";
	}
	
	//비밀번호, email 수정하기
	@PostMapping("/users/{id}/update")
	public String update(@PathVariable Integer id,UpdateDto updateDto) {
		//1 영속화
		Users usersPs = usersDao.findById(id);
		Users principal = (Users)session.getAttribute("principal");
		
		// 비정상 요청 체크
		if(usersPs == null) {
			return "errors/badPage";
		}
		
		if(principal == null) {
			return "redirect:/loginForm";
		}
		if(usersPs.getId() != principal.getId()) {
			return "errors/badPage";
		}
		
		//2. 변경
		usersPs.updateEmailPassword(updateDto);
		
		//3.전체수정
		usersDao.update(usersPs);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login") // 로그인만 예외로 select인데 post로 함.
	public String login(LoginDto loginDto) {
		Users usersPS = usersDao.login(loginDto);
		if(usersPS != null) { // 인증됨.
			session.setAttribute("principal", usersPS);
			return "redirect:/";
		}else { // 인증안됨.
			
			return "redirect:/loginForm";
		}
		
	}
	
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
}
