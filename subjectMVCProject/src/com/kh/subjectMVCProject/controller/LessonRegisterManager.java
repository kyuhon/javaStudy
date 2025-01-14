package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.LessonVO;



public class LessonRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	//전체 학생리스트를 출력요청
	public void selectManager() throws SQLException {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	public void insertManager() throws SQLException {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다. 
		System.out.print("과목요약입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String abbre= (sc.nextLine()).trim();

		System.out.print("과목명입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String name = (sc.nextLine()).trim();
		
		LessonVO lvo = new LessonVO(abbre, name); 
		boolean successFlag = ldao.lessonInsert(lvo); 
		
		//화면출력
		if(successFlag == true) {
			System.out.println(name +"과목을 입력 하였습니다.");
		}else {
			System.out.println(name +"과목을 입력 실패 하였습니다.");
		}
	}

	public void updateManager() throws SQLException {
			LessonDAO ldao = new LessonDAO();
	        LessonVO lvo = new LessonVO();
	        //수정하기 전체출력요청
	        ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
	        if(lessonList.size() != 0) {
	            printLessonList(lessonList); 
	        }else {
	            System.out.println("출력할 데이터가 없습니다.");
	            return; 
	        }
	        //화면으로부터 입력받는다.
	        System.out.print("수정할 번호선택>>");
	        int no = Integer.parseInt(sc.nextLine());

	        System.out.print("수정할과목입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
	        String abbre= (sc.nextLine()).trim();

	        System.out.print("수정할과목명입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
	        String name = (sc.nextLine()).trim();

	        lvo = new LessonVO(no, abbre, name); 
	        boolean successFlag = ldao.lessonUpdate(lvo); 

	        //화면출력
	        if(successFlag == true) {
	            System.out.println(no+"과목을 수정 하였습니다.");
	        }else {
	            System.out.println(no +"과목을 수정 실패 하였습니다.");
	        }
	}

	public void printLessonList(ArrayList<LessonVO> lessonList) {
		for( LessonVO data : lessonList ) {
			System.out.println(data);
		}
	}

	public void deleteManager() throws SQLException {
		LessonDAO ldao = new LessonDAO(); 
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	
		//화면으로부터 입력받는다. 
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(sc.nextLine());
		
		lvo.setNo(no);
		boolean successFlag = ldao.lessonDelete(lvo); 
		
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






