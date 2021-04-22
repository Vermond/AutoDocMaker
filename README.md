# AutoDocMaker

구조

controller
데이터 클래스의 실제 처리(읽기, 쓰기)를 하여 UI로 결과를 전달하는 클래스
MVVM에서 VM에 해당함

data
데이터 저장용 클래스
여기에는 변수 및 변수의 getter/setter, 유효성 체크 로직만 넣는다

frame
UI로 출력하는 아이템 클래스
UI 출력만 담당한다. 자신의 UI 제어용이 아닌 로직은 넣지 않는다

frame - base
UI 화면이 아닌 UI 요소는 여기에 넣는다

frame - view
UI 요소가 모여서 실제 화면을 구성하는 클래스는 여기 넣는다

