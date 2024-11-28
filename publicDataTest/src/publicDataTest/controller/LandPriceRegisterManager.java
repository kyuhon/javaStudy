package publicDataTest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


import publicDataTest.PublicDataAPITest;
import publicDataTest.model.LandPriceVO;



public class LandPriceRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	//전체 학생리스트를 출력요청
	public void selectManager() throws SQLException {
		LandPriceDAO ldao = new LandPriceDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LandPriceVO lvo = new LandPriceVO();
		ArrayList<LandPriceVO> list = ldao.landPriceSelect();
		//화면출력
		if(list.size() != 0) {
			printLessonList(list); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	public void insertManager() {
		LandPriceDAO ldao = new LandPriceDAO(); 
		boolean successFlag = false;
		//네트워크로부터 데이터를 입력받는다.
		ArrayList<LandPriceVO> landPricelist = PublicDataAPITest.apiDataLoad();
		
		try {
			for(LandPriceVO lvo : landPricelist) {
				int count = ldao.landPriceCheckNodeNOSelect(lvo);
				if(count <= 0) {
					successFlag = ldao.landPriceInsert(lvo);
				}else {
					successFlag = ldao.landPriceUpdate(lvo);
				}
			}
			
			//화면출력
			if(successFlag == true) {
				System.out.println("데이터를 입력 또는 수정하였습니다");
			}else {
				System.out.println("데이터를 입력 또는 수정 실패하였습니다");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
	}

	public void updateManager() {
			LandPriceDAO ldao = new LandPriceDAO(); 
			LandPriceVO lvo = new LandPriceVO();
	        //수정하기 전체출력요청
	        ArrayList<LandPriceVO> list = ldao.landPriceSelect(lvo); 
	        
	        if(list.size() != 0) {
	            printLessonList(list); 
	        }else {
	            System.out.println("출력할 데이터가 없습니다.");
	            return; 
	        }
	        //화면으로부터 입력받는다.
	        System.out.print("수정할 번호선택>>");
	        int nodeno = Integer.parseInt(sc.nextLine());

	        System.out.print("수정할 위도입력>>");
	        double gpslati = Double.parseDouble(sc.nextLine().trim());
	        
	        System.out.print("수정할 경도입력>>");
	        double gpslong = Double.parseDouble(sc.nextLine().trim());
	        
	        System.out.print("수정할 아이디입력>>");
	        String nodeid = (sc.nextLine().trim());

	        System.out.print("수정할 정류소명>>");
	        String nodenm = (sc.nextLine().trim());
	        
	        lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);
	        boolean successFlag = ldao.landPriceUpdate(lvo);

	        //화면출력
	        if(successFlag == true) {
	            System.out.println(no+"과목을 수정 하였습니다.");
	        }else {
	            System.out.println(no +"과목을 수정 실패 하였습니다.");
	        }
	}

	public void printLessonList(ArrayList<LandPriceVO> lessonList) {
		for( LandPriceVO data : lessonList ) {
			System.out.println(data);
		}
	}

	public void deleteManager() throws SQLException {
		LandPriceDAO ldao = new LandPriceDAO(); 
		LandPriceVO lvo = new LandPriceVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	
		//화면으로부터 입력받는다. 
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(sc.nextLine().trim());
		System.out.println("no="+no);
		LandPriceVO lvo = new LandPriceVO();
		//화면출력
		if(successFlag == true) {
			System.out.println(no +"번호를 삭제 하였습니다.");
		}else {
			System.out.println(no +"번호 삭제 실패하였습니다.");
		}
	}

	public void selectSortManager() throws SQLException {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelectSort(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	
}





