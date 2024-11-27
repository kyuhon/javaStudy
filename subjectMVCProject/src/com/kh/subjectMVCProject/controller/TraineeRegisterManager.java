package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.kh.subjectMVCProject.model.StudentVO;
import com.kh.subjectMVCProject.model.TraineeVO;


public class TraineeRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	//전체 학생리스트를 출력요청
	
	public static void totalSelectManager()  {
		TraineeDAO tdao = new TraineeDAO(); 
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		
		traineeList = tdao.traineeAllSelect(new TraineeVO()); 
		if(traineeList.size() <= 0 ) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeAllList(traineeList); 
	}
	
	public static void SelectManager() throws SQLException {
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		traineeList = tdao.traineeSelect(new TraineeVO());
		if(traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeList(traineeList); 
	}

	public static void insertManager() throws SQLException {
		// public static final String TRAINEE_INSERT = "INSERT INTO TRAINEE VALUES(TRAINEE_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		TraineeDAO tdao = new TraineeDAO();
		
		System.out.print("(학생이름입력하세요)>> ");
		String name = sc.nextLine();
		StudentRegisterManager srm = new StudentRegisterManager();
		srm.selectNameSearchManager();
		System.out.println("학생번호등록>>");
		String name = sc.nextLine();
		
		
		//Lesson abbreviation 보여준다. 과목명요약, 과목명
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectSortManager();
		
		System.out.print("영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());

		StudentVO studentVO = new StudentVO();  
		boolean successFlag = StudentDAO.studentInsert(studentVO);
		
		if(successFlag == true) {
			System.out.println("입력처리 성공");
		}else {
			System.out.println("입력처리 실패");
		}
	}

	public static void updateManager() throws SQLException {
		System.out.print("수정할 학생의 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("새로운 국어 점수를 입력하세요: ");
		int kor = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 영어 점수를 입력하세요: ");
		int eng = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 수학 점수를 입력하세요: ");
		int mat = Integer.parseInt(sc.nextLine());
		
		StudentVO svo = new StudentVO();
		boolean successFlag = StudentDAO.studentUpdate(svo);
		
		if(successFlag == true) {
			System.out.println("입력처리 성공");
		}else {
			System.out.println("입력처리 실패");
		}
	}

	public static void deleteManager() throws SQLException {
		TraineeVO tvo = new TraineeVO();
		TraineeDAO tdao = new TraineeDAO();
		
		System.out.print("삭제할 수강신청번호를 입력하세요>> ");
		int no = Integer.parseInt(sc.nextLine());
		tvo.setNo(no);
		boolean successFlag = tdao.traineeDelete(tvo); 
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}
	}

	public static void sortManager() throws SQLException {
		TraineeDAO tdao = new TraineeDAO();
		ArrayList<TraineeVO> traineeList = null;
		
		traineeList = tdao.traineeSort();
		printTraineeList(traineeList);
		
		if(traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
	}

	//전체 학생리스트를 출력진행
	public static void printTraineeList(ArrayList<TraineeVO> traineeList) {
		System.out.println("============================================");
		for( TraineeVO tvo :  traineeList) {
			System.out.println(tvo.toString());
		}
		System.out.println("============================================");
	}
	//전체 학생리스트를 조인기능을 포함해서 출력진행
	public static void printTraineeAllList(ArrayList<TraineeVO> traineeList) {
		System.out.println("============================================");
		for( TraineeVO tvo :  traineeList) {
			System.out.println(tvo.toAllString());
		}
		System.out.println("============================================");
	}
	
}






