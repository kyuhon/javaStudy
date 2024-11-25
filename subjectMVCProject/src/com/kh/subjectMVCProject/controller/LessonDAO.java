package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.StudentVO;


public class LessonDAO {
		
	public static final String LESSON_SELECT = "SELECT * FROM STUDENT";
    public static final String LESSON_INSERT = "INSERT INTO STUDENT(NO, NAME, KOR, ENG, MAT) VALUES(STUDENT_NO_SEQ.NEXTVAL, ?, ?, ?, ?)";
    public static final String LESSON_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
    public static final String LESSON_UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ?";
    public static final String LESSON_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
    public static final String LESSON_SORT = "SELECT *FROM STUDENT ORDER BY RANK";
	
	public static ArrayList<StudentVO> studentSelect() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(LESSON_SELECT);

		if(rs.next()) {
			do{
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				int kor = rs.getInt("KOR");
				int eng = rs.getInt("ENG");
				int mat = rs.getInt("MAT");
				int total = rs.getInt("TOTAL");
				int ave = rs.getInt("AVE");
				int rank = rs.getInt("RANK");

				StudentVO stu = new StudentVO();
				studentList.add(stu);
			}while (rs.next());
		}else {
			studentList = null; 
		}
		DBUtility.dbClose(con, stmt, rs);
		return studentList;
	}
	
	public static boolean studentInsert(StudentVO svo) throws SQLException {
		// Conection
		boolean successFlag = false; 
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(LESSON_INSERT);
		pstmt.setString(1, svo.getName());
		

		int result1 = pstmt.executeUpdate();
		System.out.println((result1 != 0) ? "입력성공" : "입력실패");

		cstmt = con.prepareCall(LESSON_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();
		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");

		DBUtility.dbClose(con, pstmt, cstmt);
		successFlag = (result1 != 0 && result2 != 0) ? true : false;
		
		return successFlag; 
	}

	public static boolean studentUpdate(StudentVO svo) throws SQLException {
		boolean successFlag = false; 
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(LESSON_UPDATE);
		pstmt.setString(1, svo.getName());
		

		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(LESSON_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();
		
		successFlag = (result1 != 0 && result2 != 0) ? true : false;

		DBUtility.dbClose(con, pstmt, cstmt);
		return successFlag; 
	}

	public static boolean studentDelete(StudentVO svo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(LESSON_DELETE);
		pstmt.setInt(1, svo.getNo());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}

	public static ArrayList<StudentVO> studentSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(LESSON_SORT);

		if(rs.next()) {
			do {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				int kor = rs.getInt("KOR");
				int eng = rs.getInt("ENG");
				int mat = rs.getInt("MAT");
				int total = rs.getInt("TOTAL");
				int ave = rs.getInt("AVE");
				int rank = rs.getInt("RANK");

				StudentVO stu = new StudentVO();
				studentList.add(stu);
			} while (rs.next());
		}else {
			studentList = null; 
		}

		DBUtility.dbClose(con, stmt, rs);
		return studentList; 
	}

}
