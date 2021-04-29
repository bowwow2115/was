package kr.or.iei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sendData2
 */
@WebServlet("/sendData2")
public class sendData2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendData2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//문자열 받기
		String str = request.getParameter("str");
		//숫자 받기
		int num =Integer.parseInt( request.getParameter("num"));
		//라디오 받기
		String gender = request.getParameter("gender");
		//체크 박스
		String[] hobby = request.getParameterValues("hobby");
		//셀렉트
		String age = request.getParameter("age");
		//testStr
		String testStr = request.getParameter("testStr");
		
		System.out.println("str: " + str);
		System.out.println("num: " + num);
		System.out.println("gender: " + gender);
		for(int i=0; i<hobby.length; i++) {
			System.out.println(hobby[i]+"/");
		}
		System.out.println();
		System.out.println("age: "+ age );
		System.out.println("testStr: "+ testStr );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
