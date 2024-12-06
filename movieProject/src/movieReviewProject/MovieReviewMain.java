package movieReviewProject;

import java.sql.SQLException;
import java.util.Scanner;

import movieReviewProject.controller.MovieRegisterManager;
import movieReviewProject.view.MenuChoice;
import movieReviewProject.view.MenuView;
import movieReviewProject.view.MoviesChoice;
import movieReviewProject.view.ReviewChoice;
import movieReviewProject.view.UserChoice;
import movieReviewProject.view.WatchHistoryChoice;

public class MovieReviewMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int no;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				MenuView.mainMenuView();
				no = Integer.parseInt(sc.nextLine());
				switch (no) {
				case MenuChoice.USER_CHOICE:
					UserMenu();
					break;
				case MenuChoice.MOVIES_CHOICE:
					MovieMenu();
					break;
				case MenuChoice.WATCH_HISTORY_CHOICE:
					WatchHistoryMenu();
					break;
				case MenuChoice.REVIEW_CHOICE:
					reviewMenu();
					break;
				case MenuChoice.EXIT:
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
			}
		}
	}

	private static void UserMenu() {
		int no;
		MenuView.usersMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch (no) {
		case UserChoice.USER_INSERT:
			System.out.println("");
			break;
		case UserChoice.USER_SELECT_ALL:
			System.out.println("");
			break;
		case UserChoice.USER_SELECT:
			System.out.println("");
			break;
		case UserChoice.USER_UPDATE:
			System.out.println("");
			break;
		case UserChoice.USER_DELETE:
			System.out.println("");
			break;
		case UserChoice.MAIN_MENU:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}

	}

	private static void MovieMenu() {

		int no;
		MovieRegisterManager mrm = new MovieRegisterManager();
		MenuView.moviesMenuView();
		no = Integer.parseInt(sc.nextLine());
		try {
			switch (no) {
			case MoviesChoice.MOVIE_INSERT:
				mrm.InsertManager();
				System.out.println("");
				break;
			case MoviesChoice.MOVIE_SELECT_ALL:
				System.out.println("");
				mrm.selectManager();
				break;
			case MoviesChoice.MOVIE_GENRE_SELECT:
				System.out.println("");
				break;
			case MoviesChoice.MOVIE_UPDATE:
				System.out.println("");
				mrm.updateManager();
				break;
			case MoviesChoice.MOVIE_DELETE:
				System.out.println("");
				mrm.deleteManager(); 
				break;
			case MoviesChoice.MAIN_MENU:
				;
				return;
			default:
				System.out.println("해당 메뉴 번호만 입력하세요.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void WatchHistoryMenu() {
		int no;
		MenuView.watchHistoryMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch (no) {
		case WatchHistoryChoice.WATCH_HISTORY_INSERT:
			System.out.println("");
			break;
		case WatchHistoryChoice.WATCH_HISTORY_SELECT_ALL:
			System.out.println("");
			break;
		case WatchHistoryChoice.WATCH_HISTORY_SELECT:
			System.out.println("");
			break;
		case WatchHistoryChoice.WATCH_HISTORY_UPDATE:
			System.out.println("");
			break;
		case WatchHistoryChoice.WATCH_HISTORY_DELETE:
			System.out.println("");
			break;
		case WatchHistoryChoice.MAIN_MENU:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}

	private static void reviewMenu() {
		int no;
		MenuView.reviewMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch (no) {
		case ReviewChoice.REVIEW_INSERT:
			System.out.println("");
			break;
		case ReviewChoice.REVIEW_SELECT_ALL:
			System.out.println("");
			break;
		case ReviewChoice.REVIEW_SELECT:
			System.out.println("");
			break;
		case ReviewChoice.REVIEW_UPDATE:
			System.out.println("");
			break;
		case ReviewChoice.REVIEW_DELETE:
			System.out.println("");
			break;
		case ReviewChoice.MAIN_MENU:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}

}
