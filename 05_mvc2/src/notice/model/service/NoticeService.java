package notice.model.service;
import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.NoticePageData;
import notice.model.vo.NoticeViewData;

public class NoticeService {

	public NoticePageData selectNoticeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		// 1.한 페이지에 게시물을 몇개 보여줄지: 한페이지당 10개씩 보여줌
		int numPerPage = 10;

		// reqPage 를 통해서 게시물 시작 rnum 끝 rnum 계산
		// 1 -> start: 1, end: 10, 2-> start: 11, ...
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		// 요청한 페이지의 게시물을 조회
		NoticeDao dao = new NoticeDao();
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, start, end);

	
		// 전체 게시물 수 조회
		int totalCount = dao.totalCount(conn);
		// 전체 페이지 수 계산
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {

			totalPage = totalCount / numPerPage + 1;
		}
		// 페이지 네비 길이지정
		int pageNaviSize = 5;
		// 1~5 페이지 요청시 페이지 네비 시작번호 :1
		// 6~10 페이지 요청시 페이지 네비 시작번호 :2
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		System.out.println("first pageNaviNo: " + pageNo);
		String pageNavi = "<ul class = 'pagination pagination-lg'>";
		
		// 페이지 버튼 생성 1이 아닐떄만
		if (pageNo != 1) {
			pageNavi += "<li class = 'page-item'>";
			pageNavi += "<a class = 'page-link' href ='/noticeList?reqPage=" + (pageNo - 1) + "'>&lt;</a></li>";

		}
		// 페이지 숫자 생성
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class = 'page-item active'>";
				pageNavi += "<a class = 'page-link' href='/noticeList?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			} else {
				pageNavi += "<li class = 'page-item'>";
				pageNavi += "<a class = 'page-link' href='/noticeList?reqPage=" + pageNo + "'>" + pageNo + "</a></li>";
			} 
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//페이지 다음 벝큰 세엇ㅇ
		if (pageNo <= totalPage) {
			pageNavi += "<li class = 'page-item'>";
			pageNavi += "<a class = 'page-link' href ='/noticeList?reqPage=" + (pageNo) + "'>&gt;</a></li>";
			
		}
		pageNavi += "</ul>";
		JDBCTemplate.close(conn);
		NoticePageData npd = new NoticePageData(list, pageNavi);
		
		System.out.println("pageNaviNo: " + pageNo);
		
		return npd;
	}

	public int insertNotice(Notice n) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertNotice(conn, n);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Notice selectOneNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		Notice n = dao.selectOneNoice(conn, noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}
	public NoticeViewData selecNoticeView(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		Notice n = dao.selectOneNoice(conn, noticeNo);
		ArrayList<NoticeComment> list = dao.selectNoticeCommentList(conn, noticeNo);
		JDBCTemplate.close(conn);
		NoticeViewData nvd = new NoticeViewData(n,list);
		return nvd;
	}

	public int deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateNotice(Notice n) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().updateNotice(conn, n);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertComment(NoticeComment nc) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertComment(conn, nc);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateNoticeCommet(int ncNo, String ncContent) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().updateNoticeComment(conn,ncNo,ncContent);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteNoticeComment(int ncNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().deleteNoticeComment(conn,ncNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
}