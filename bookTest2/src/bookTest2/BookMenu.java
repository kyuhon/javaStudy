package bookTest2;

public interface BookMenu {
	//상수, 추상메소드, 디폴트메소드, private 메소드
	//일반 멤버변수 x -> 모호성을 주어서는 안된다
		int PRINT = 1; 
		int	INSERT = 2;
		int	UPDATE = 3;
		int	DELETE = 4;
		int	SALARY_UP = 5;
		int	EXIT = 6;
	
}
