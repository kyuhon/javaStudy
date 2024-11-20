package foodTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


import foodTest.DBConnection;

public class FoodMain {
	public static Scanner scan = new Scanner(System.in);
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, DELETE = 4, EXIT = 5;

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
			case DELETE:
				foodsDelete();
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + num);

			}
		}
		System.out.println("The end");

	}

	private static void printMenu() {
		System.out.println("Foods Menu(1.출력, 2.입력, 3.수정, 4.삭제 5.종료)");
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
