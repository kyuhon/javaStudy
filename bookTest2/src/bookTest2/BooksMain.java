package bookTest2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class BooksMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		// 사용자로부터 Books 출력, 입력, 수정, 삭제 요청받는다.
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case BookMenu.PRINT:
				booksPrint();
				break;
			case BookMenu.INSERT:
				booksInsert();
				break;
			case BookMenu.UPDATE:
				booksUpdate();
				break;
			case BookMenu.DELETE:
				booksDelete();
				break;
			case BookMenu.SALARY_UP:
				employeeSalaryUp();
				break;
			case BookMenu.EXIT:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
		}
		System.out.println("The end");
	}

	// 연봉인상 10%
	private static void employeeSalaryUp() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		
		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		System.out.println("인상될 ID 입력: >>");
		int Id = Integer.parseInt(scan.nextLine());
		System.out.println("인상률(ex)10%: 1.1): >>");
		int incrementSalary = Integer.parseInt(scan.nextLine());
		
		// stmt = con.createStatement();
		cstmt = con.prepareCall("{call EMP1_PROCEDURE(?)}");
		cstmt.setInt(1, department_id);
		int result = cstmt.executeUpdate();
//				int result = stmt.executeUpdate(
//						"update books set title = '" + books.getTitle() + "',publisher = '" + books.getPublisher() + "', year = '"
//								+ books.getYear() + "', price = " + books.getPrice() + " where id = " + books.getId() + "");
		// 4. 내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "프로시저성공" : "프로시저실패");
		// 5. 출력하기
		// 6. sql 객체 반납
		DBConnection.dbClose(con, cstmt);
	}

	// 삭제
	private static void booksDelete() throws SQLException {
		// Connection
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		System.out.println("삭제할번호>>");
		int no = Integer.parseInt(scan.nextLine());
		pstmt = con.prepareStatement("DELETE FROM BOOKS WHERE ID = ? ");
		pstmt.setInt(1, no);
		int result = pstmt.executeUpdate();
		// 4. 내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		// 5. 출력하기
		// 6. sql 객체 반납
		DBConnection.dbClose(con, pstmt);

	}

	// 수정
	private static void booksUpdate() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		// 수정할 데이터를 입력
		Books books = new Books(3, "JAVA java", "kdj", "2024", 44000);

		// stmt = con.createStatement();
		pstmt = con.prepareStatement("update books set title = ?, publisher = ?, year = ?, price = ? where id = ?");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
		pstmt.setInt(5, books.getId());
		int result = pstmt.executeUpdate();
//		int result = stmt.executeUpdate(
//				"update books set title = '" + books.getTitle() + "',publisher = '" + books.getPublisher() + "', year = '"
//						+ books.getYear() + "', price = " + books.getPrice() + " where id = " + books.getId() + "");
		// 4. 내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		// 5. 출력하기
		// 6. sql 객체 반납
		DBConnection.dbClose(con, pstmt);

	}

	// 입력
	private static void booksInsert() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		Books books = new Books(0, "Head First JAVA", "kdj", "2008", 23000);
		stmt = con.createStatement();
		pstmt = con.prepareStatement("INSERT INTO BOOKS VALUES (BOOKS_ID_SEQ.NEXTVAL, ?, ?, ?, ?)");
		pstmt.setString(1, books.getTitle());
		pstmt.setString(2, books.getPublisher());
		pstmt.setString(3, books.getYear());
		pstmt.setInt(4, books.getPrice());
//		int result = stmt.executeUpdate("INSERT INTO books VALUES " + "(books_id_seq.nextval, '" + books.getTitle()
//				+ "' ,' " + books.getPublisher() + "','" + books.getYear() + "'," + books.getPrice() + ")");
		int result = pstmt.executeUpdate();
		// 4. 내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		// 5. 출력하기
		// 6. sql 객체 반납
		DBConnection.dbClose(con, stmt);

	}

	// 출력
	public static void booksPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Books> booksList = new ArrayList<Books>();

		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM BOOKS");
		// 4. 테이블 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");
			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);
		}
		// 5. 출력하기
		booksListPrint(booksList);
		// 6. sql 객체 반납
		DBConnection.dbClose(con, stmt, rs);
	}

	private static void printMenu() {
		System.out.println("Books Menu(1.출력, 2.입력, 3.수정, 4.삭제 5.연봉인상(emp) 6.종료");
		System.out.println(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}

}
