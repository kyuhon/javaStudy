package foodTest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;


import foodTest.DBConnection;

public class FoodMain {
	public static Scanner scan = new Scanner(System.in);
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, PRICE_UP = 4,
			PRICE_DOWN = 5, DELETE = 6;

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {

			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case PRINT:
				foodsPrint();
				break;
			case INSERT:
				foodsInsert();
				break;
			case UPDATE:
				foodsUpdate();
				break;
			case PRICE_UP:
				priceUpProc();
				break;
			case PRICE_DOWN:
				priceDownFunc();
				break;
			case DELETE:
				foodsDelete();
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + num);

			}
		}
		System.out.println("The end");

	}

	private static void priceUpProc() throws SQLException {
		// Connection
				Connection con = null;
				CallableStatement cstmt = null;

				// 1 Load,2 connect
				con = DBConnection.dbCon();
				System.out.print("인상될 ID 입력: >>");
				int id = Integer.parseInt(scan.nextLine());
				System.out.print("인상금액: >>");
				int price = Integer.parseInt(scan.nextLine());

				// 3. cstmt = con.prepareCall("{call EMP1_PROCEDURE(?,?,?)}");
				cstmt = con.prepareCall("{call FOOD_PROCEDURE(?, ?, ?)}");
				cstmt.setInt(1, id);
				cstmt.setDouble(2, price);
				// 출력될 데이터값으로 4번을 바인딩시킨다.
				cstmt.registerOutParameter(3, Types.VARCHAR);

				int result = cstmt.executeUpdate();
				String message = cstmt.getString(3);
				System.out.println(message);
				// 4.내용이 잘 입력이 되었는지 check
				System.out.println((result != 0) ? "가격 인상 프로시저성공" : "가격 인상 프로시저실패");
				// 6.sql 객체 반남
				DBConnection.dbClose(con, cstmt);
	}
	private static void priceDownFunc() throws SQLException {
		// Connection
				Connection con = null;
				CallableStatement cstmt = null;

				// 1 Load,2 connect
				con = DBConnection.dbCon();
						
				System.out.print("인하할 ID 입력: >>");
				int id = Integer.parseInt(scan.nextLine());
				System.out.println("인하금액: >>");
				int price = Integer.parseInt(scan.nextLine());
				
				// 3. cstmt = con.prepareCall("{ ? = call BOOKS_FUNCTION(?)}");
				cstmt = con.prepareCall("{ ? = call FOOD_FUNCTION(?,?)}");
				cstmt.registerOutParameter(1, Types.VARCHAR);
				cstmt.setInt(2, id);
				cstmt.setInt(3, price);
				// 출력될 데이터값으로 3번을 바인딩시킨다.

				int result = cstmt.executeUpdate();
				String message = cstmt.getString(1);
				System.out.println(message);
				// 4.내용이 잘 입력이 되었는지 check
				System.out.println((result != 0) ? "FUNCTION 성공" : "FUNCTION 실패");
				// 6.sql 객체 반남
				DBConnection.dbClose(con, cstmt);
	}


	private static void printMenu() {
		System.out.println("Foods Menu(1.출력, 2.입력, 3.수정, 4.가격인상, 5.가격인하, 6.종료)");
		System.out.println(">>");
	}

	private static void foodsPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Food> foodsList = new ArrayList<Food>();

		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM FOOD");
		// 4. 테이블 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			int price = rs.getInt("PRICE");
			Food foods = new Food(id, title, price);
			foodsList.add(foods);
		}
		// 5. 출력하기
		foodsListPrint(foodsList);
		// 6. sql 객체 반납
		DBConnection.dbClose(con, stmt, rs);
	}

	private static void foodsListPrint(ArrayList<Food> foodsList) {
		for (Food foods : foodsList) {
			System.out.println(foods.toString());
		}
	}

	private static void foodsInsert() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1 Load, 2 connect
		con = DBConnection.dbCon();
		// 3. statement
		System.out.print("추가할 제목을 입력하시오 :");
		String a = scan.nextLine();
		System.out.println("추가할 값을 입력하시오 :");
		String b = scan.nextLine();
		stmt = con.createStatement();
		int result = stmt.executeUpdate("INSERT INTO FOOD VALUES " + "(food_id_seq.nextval, '" + a + "' ,' " + b + "')");
		// 4. 내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		// 5. 출력하기
		// 6. sql 객체 반납
		DBConnection.dbClose(con, stmt);
	}

	private static void foodsUpdate() throws SQLException {
		// Connection
				Connection con = null;
				Statement stmt = null;

				// 1 Load, 2 connect
				con = DBConnection.dbCon();
				// 3. statement
				stmt = con.createStatement();
				System.out.print("수정할 id : ");
				int a = Integer.parseInt(scan.nextLine());
				System.out.print("수정할 이름 : ");
				String b = scan.nextLine();
				System.out.print("수정할 가격 : ");
				int c = Integer.parseInt(scan.nextLine());
				int result = stmt.executeUpdate("update food set title = '" + b + "', price = " + c + " where id = " + a + "");
				// 4. 내용이 잘 입력이 되었는지 check
				System.out.println((result != 0) ? "수정성공" : "수정실패");
				// 5. 출력하기
				// 6. sql 객체 반납
				DBConnection.dbClose(con, stmt);
	}

	private static void foodsDelete() throws SQLException {
		// Connection
				Connection con = null;
				PreparedStatement pstmt = null;

				// 1 Load, 2 connect
				con = DBConnection.dbCon();
				// 3. statement
				System.out.println("삭제할번호>>");
				int no = Integer.parseInt(scan.nextLine());
				pstmt = con.prepareStatement("DELETE FROM FOOD WHERE ID = ? ");
				pstmt.setInt(1, no);
				int result = pstmt.executeUpdate();
				// 4. 내용이 잘 입력이 되었는지 check
				System.out.println((result != 0) ? "삭제성공" : "삭제실패");
				// 5. 출력하기
				// 6. sql 객체 반납
				DBConnection.dbClose(con, pstmt);
	}

}
