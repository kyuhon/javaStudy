package publicDataTest.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import publicDataTest.model.LandPriceVO;

public class LandPriceDAO {

	public final String LANDPRICE_SELECT = "SELECT * FROM LANDPRICE";
	public final String LANDPRICE_CHECK_NODENO_SELECT = "SELECT count(*) as count FROM LANDPRICE where nodeno = ?";
	public final String LANDPRICE_INSERT = "INSERT INTO LANDPRICE VALUES(?, ?, ?, ?, ?)";
	public final String LANDPRICE_UPDATE = "UPDATE LANDPRICE SET gpslati = ?, gpslong = ?, nodeid = ?, nedenm = ? WHERE nodeno = ?";
	public final String LANDPRICE_DELETE = "DELETE FROM LANDPRICE WHERE nodeno = ?";
	public final String LANDPRICE_SORT = "SELECT *FROM LANDPRICE ORDER BY nedenm";

	public ArrayList<LandPriceVO> landPriceSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> landPriceList = new ArrayList<LandPriceVO>();

		con = DBUtility.dbCon();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(LANDPRICE_SELECT);
			
			if (rs.next()) {
				do {
					int nodeno = rs.getInt("nodeno");
					double gpslati = rs.getDouble("gpslati");
					double gpslong = rs.getDouble("gpslong");
					String nodeid = rs.getString("nodeid");
					String nedenm = rs.getString("nedenm");
					
					LandPriceVO lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nedenm);
					landPriceList.add(lvo);
				} while (rs.next());
			} else {
				landPriceList = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		return landPriceList;
	}

	public boolean landPriceInsert(LandPriceVO lvo) throws SQLException {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(LANDPRICE_INSERT);
		pstmt.setInt(1, lvo.getNodeno());
		pstmt.setDouble(2, lvo.getGpslati());
		pstmt.setDouble(3, lvo.getGpslong());
		pstmt.setString(4, lvo.getNodeid());
		pstmt.setString(5, lvo.getNedenm());

		int result = pstmt.executeUpdate();

		successFlag = (result != 0) ? true : false;
		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}

	public boolean landPriceUpdate(LandPriceVO lvo)  {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(LANDPRICE_UPDATE);
			pstmt.setInt(1, lvo.getNodeno());
			pstmt.setDouble(2, lvo.getGpslati());
			pstmt.setDouble(3, lvo.getGpslong());
			pstmt.setString(4, lvo.getNodeid());
			pstmt.setString(5, lvo.getNedenm());
			
			int result = pstmt.executeUpdate();
			
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	public boolean landPriceDelete(LandPriceVO lvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_DELETE);
			pstmt.setInt(1, lvo.getNodeno());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	public int landPriceCheckNodeNOSelect(LandPriceVO lvo) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> landPriceList = new ArrayList<LandPriceVO>();
		int count = 0;
		
		try {
		con = DBUtility.dbCon();
		stmt = con.createStatement();
			rs = stmt.executeQuery(LANDPRICE_CHECK_NODENO_SELECT);
			if (rs.next()) {
				count = rs.getInt("COUNT");
			} else {
				landPriceList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}

		return count;
	}


}
