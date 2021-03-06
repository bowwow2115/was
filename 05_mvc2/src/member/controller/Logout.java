package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet(name = "LogoutServ", urlPatterns = { "/Logout" })
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		//request.getsession(): 현재 존재하는 세션을 가지고옴/ 존재하지 않으면 생성해서 리턴
		//request.getsession(false): 현재 존재하는 세션을 가지고옴/ 존재하지 않으면 null 리턴
		if(session != null) {	//로그인 한 적이 있는 경우 파기
			session.invalidate();
		}
		//로그아웃 방법 1.
//		// 결과 처리할 페이지 지정
//		RequestDispatcher rd = request.getRequestDispatcher("/");
//		// 결과 화면에서 사용할 데이터 등록
//		//화면 이동
//		rd.forward(request, response);
		
		//결과를 처리할 화면에 전달할 객체가 없는 경우
		//로그아웃 방법 2.
		response.sendRedirect("/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
