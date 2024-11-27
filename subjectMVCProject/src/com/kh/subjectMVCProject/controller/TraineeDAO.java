package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.kh.subjectMVCProject.model.TraineeVO;



public class TraineeDAO {
		
	public static final String TRAINEE_SELECT = "SELECT * FROM TRAINEE";
	public static final String TRAINEE_ALL_SELECT = "select T.no, T.section, T.regdate, S.num, S.name, L.abbre, L.name as abbreName from trainee T inner join student S on T.s_num =S.num"
			+ " inner join lesson L on T.abbre = L.abbre "
			+ " order by T.no";
	public static final String TRAINEE_SORT = "SELECT *FROM TRAINEE ORDER BY S_NUM";
	public static final String TRAINEE_DELETE = "DELETE FROM TRAINEE WHERE NO = ?";
	public static final String TRAINEE_UPDATE = "UPDATE TRAINEE SET S_NUM = ?, ABBRE = ?, SECTION = ? WHERE NO = ?";
    public static final String TRAINEE_INSERT = "INSERT INTO TRAINEE VALUES(TRAINEE_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	
	public ArrayList<TraineeVO> traineeSelect(TraineeVO tvo) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SELECT);
		while(rs.next()) {
			int no = rs.getInt("NO");
			String s_num = rs.getString("S_NUM");
			String abbre = rs.getString("ABBRE");
			String section = rs.getString("SECTION");
			Date regdate = rs.getDate("REGDATE");
			TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, regdate);
			traineeList.add(traineeVO);
		}
		
		DBUtility.dbClose(con, stmt, rs);
		return traineeList;
	}
	
	public ArrayList<TraineeVO> traineeAllSelect(TraineeVO tvo) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_ALL_SELECT);
		while(rs.next()) {
			int no = rs.getInt("NO");
			String section = rs.getString("SECTION");
			Date regdate = rs.getDate("REGDATE");
			String s_num = rs.getString("NUM");
			String s_name = rs.getString("SNAME");
			String abbre = rs.getString("ABBRE");
			String l_name = rs.getString("LNAME");
			TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, regdate, s_name, l_name);
			traineeList.add(traineeVO);
		}
		
		DBUtility.dbClose(con, stmt, rs);
		return traineeList;
	}
	
	public static ArrayList<TraineeVO> traineeSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(TRAINEE_SELECT);
		while(rs.next()) {
			int no = rs.getInt("NO");
			String s_num = rs.getString("S_NUM");
			String abbre = rs.getString("ABBRE");
			String section = rs.getString("SECTION");
			Date regdate = rs.getDate("REGDATE");
			TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, regdate);
			traineeList.add(traineeVO);
		}
		
		DBUtility.dbClose(con, stmt, rs);
		return traineeList;
	}
	
	public boolean traineeInsert(TraineeVO tvo) throws SQLException {
		// Conection
		boolean successFlag = false; 
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		pstmt.setString(1, tvo.getS_num());
		pstmt.setString(2, tvo.getAbbre());
		pstmt.setString(3, tvo.getSection());
		

		int result1 = pstmt.executeUpdate();
		System.out.println((result1 != 0) ? "입력성공" : "입력실패");

		int result2 = cstmt.executeUpdate();
		System.out.println((result2 != 0) ? "프로시저성공" : "프로시저실패");

		DBUtility.dbClose(con, pstmt, cstmt);
		successFlag = (result1 != 0 && result2 != 0) ? true : false;
		
		return successFlag; 
	}

	public static boolean traineeUpdate(TraineeVO tvo) throws SQLException {
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(TRAINEE_UPDATE);
		pstmt.setString(1, tvo.getS_num());
		pstmt.setString(2, tvo.getAbbre());
		pstmt.setString(3, tvo.getSection());
		pstmt.setInt(4, tvo.getNo());
		
		int count = pstmt.executeUpdate();
		successFlag = (count != 0) ? (true) : false;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}

	public static boolean traineeDelete(TraineeVO tvo) {
		boolean successFlag =false; 
		Connection con = null;	//오라클에 DB접속
		PreparedStatement pstmt = null;	//오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문

		//커밋을 수동으로 바꾼다
		try {
			con = DBUtility.dbCon();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(TRAINEE_DELETE);
			pstmt.setInt(1, tvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? true : false ;
			if(count !=0) {
				successFlag = true;
				//삭제성공시 커밋기능을 부여한다
				con.commit();
			} else {
				successFlag = false;
				//삭제실패시 롤백기능을 부여한다.
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);			
		}
		return successFlag; 
	}

	

}
