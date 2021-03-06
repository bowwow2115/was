package com.parksh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.parksh.member.dao.MemberDao;
import com.parksh.member.vo.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 넘어온 값 꺼내기
		String id = request.getParameter("id");
		String pw = (request.getParameter("pw"));
		//3. 처리 로직
		MemberDao dao = new MemberDao();
		Member member = dao.selectOneMember(id,pw);
		//4.결과 처리
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>로그인 결과</title></head><body>");
		if(member == null) {
			out.println("<h2>로그인 실패</h2>");
			out.println("<script>alert('아이디 또는 비밀번호를 확인하세요');</script>");
		}else {
			out.println("<h2>[" + member.getMemberName() + "] 님 환영합니다. <h2>");
			out.println("<script>alert('로그인 성공');</script>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
