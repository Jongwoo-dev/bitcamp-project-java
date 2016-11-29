package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component(value="teacher/list") // ApplicationContext가 관리하는 대상 클래스임을 표시한다.
public class TeacherListController extends AbstractCommand {
  // 의존 객체 DAO를 저장할 변수 선언
  TeacherDao teacherDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 teacherDao를 사용할 것이기 때문에
    // 더이상 이 메서드에서 TeacherDao 객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 TeacherDao가 주입되어 있어야 한다.
    ArrayList<Teacher> list = teacherDao.getList();

    for (Teacher teacher : list) {
      out.printf("%s, %s, %s, %s, %s, %d, %s, %d, %d, %s\n",
          teacher.getUserId(),
          teacher.getPassword(),
          teacher.getName(),
          teacher.getEmail(),
          teacher.getTel(),
          teacher.getAge(),
          teacher.getSubject(),
          teacher.getCarrer(),
          teacher.getSalary(),
          teacher.getAddress());
    }
  }
}
