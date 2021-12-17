package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.UserVO;
import service.UserService;
import service.UserServiceImple;

@WebServlet("/userCtrl/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UserService usv;
	private RequestDispatcher rdp;
	private int isUp;
	private String UTF8 = "utf-8";
	
	public UserController() {
		usv = new UserServiceImple();
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding(UTF8);
		res.setCharacterEncoding(UTF8);
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		
		// 세션에 유저가 없으면 로그인페이지로
		switch (path) {
		case "register":
			// 회원가입 페이지로
			req.getRequestDispatcher("/user/register.jsp").forward(req, res);
			break;
		case "insert":
			// 회원가입
			// 이후 로그인페이지로
			isUp = usv.register(new UserVO(req.getParameter("email"), 
					Integer.parseInt(req.getParameter("age")), req.getParameter("name"), 
					req.getParameter("pwd"), Boolean.parseBoolean(req.getParameter("isAdmin")), req.getParameter("nickName")
					));
			log.info(">> register {}", isUp > 0 ? "Success" : "Fail");
			req.getRequestDispatcher("/index.jsp").forward(req, res);
			break;
		case "list":
			// 관리자용 유저관리 리스트
			break;
		case "detail":
			// 회원정보 수정을 위한 페이지로
			break;
		case "modify":
			// 회원정보 수정
			// 이후 디테일 페이지로
			break;
		case "changePwd":
			// 비밀번호 수정 페이지로
			break;
		case "modifyPwd":
			// 비밀번호 수정
			// 이후 로그아웃시키고 로그인페이지로
			break;
		case "follow":
			// 비동기방식
			break;
		case "unfollow":
			// 비동기방식
			break;
		case "login":
			UserVO uvo = usv.logIn(new UserVO(req.getParameter("email"), req.getParameter("pwd")));
			if (uvo != null) {
				HttpSession ses = req.getSession();
				ses.setAttribute("ses", uvo);
				ses.setMaxInactiveInterval(60 * 20);
			} else {
				req.setAttribute("msg_u_login", 0);
			}
			rdp = req.getRequestDispatcher("/post/list.jsp");
			rdp.forward(req, res);
			break;
		case "logout":
			// 로그아웃
			// 인덱스 페이지로
			break;
		case "remove":
			// 회원정보 삭제
			// 이후 로그아웃시키고 인덱스페이지로
			break;
		
		default:
			break;
		}
	}
}
