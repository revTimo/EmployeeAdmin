package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Employee;

import DAO.EmployeeDao;
/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao dao;

	public EmployeeController() {
		super();
		dao = new EmployeeDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("employees", dao.getAllEmployees());
		RequestDispatcher view = request.getRequestDispatcher("/listemployee.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String emp_id = request.getParameter("empid");
		String emp_name = request.getParameter("name");
		String emp_email = request.getParameter("email");
		String emp_confirm_email = request.getParameter("confirm_mail");
		String emp_gender = request.getParameter("gender");
		String emp_dept = request.getParameter("dept");
		String emp_input_date = request.getParameter("emp_date");
		// 社員番号とが必須、8桁以上、社員名は 1～32 文字のテキスト
		if (emp_id.length() == 0 && emp_name.length()== 0) {
			request.setAttribute("imp_id_errmsg", "0以上整数8桁以内で入力してください");
			request.setAttribute("imp_name_errmsg", "氏名は 1～32 文字のテキストで入力してください");
			if(emp_email.length() > 0 || emp_confirm_email.length() > 0) {
				if (emp_email.equals(emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "確認入力と一致しません");
				} else if (checkMailAddress(emp_email, emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "正しく入力してください");
				}
			}
			request.setAttribute("value_remain", inp_value(emp_id, emp_name, emp_email, emp_confirm_email, emp_gender, emp_dept, emp_input_date));
			RequestDispatcher view = request.getRequestDispatcher("/add.jsp");
			view.forward(request, response);
		} else if (emp_id.length() > 8) {
			// 社員番号エラーメッセージ
			request.setAttribute("imp_id_errmsg", "0以上整数8桁以内で入力してください");
			if (emp_name.length() > 32 || emp_name.length() == 0) {
				request.setAttribute("imp_name_errmsg", "氏名は 1～32 文字のテキストで入力してください");
			}
			if(emp_email.length() > 0 || emp_confirm_email.length() > 0) {
				if (emp_email.equals(emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "確認入力と一致しません");
				} else if (checkMailAddress(emp_email, emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "正しく入力してください");
				}
			}
			request.setAttribute("value_remain", inp_value(emp_id, emp_name, emp_email, emp_confirm_email, emp_gender, emp_dept, emp_input_date));
			RequestDispatcher view = request.getRequestDispatcher("/add.jsp");
			view.forward(request, response);
		} else if (isNumber(emp_id) == false || Integer.parseInt(emp_id) == 0) {
			request.setAttribute("imp_id_errmsg", "0以上整数8桁以内で入力してください");
			request.setAttribute("value_remain", inp_value(emp_id, emp_name, emp_email, emp_confirm_email, emp_gender, emp_dept, emp_input_date));
			if (emp_name.length() > 32 || emp_name.length() == 0) {
				request.setAttribute("imp_name_errmsg", "氏名は 1～32 文字のテキストで入力してください");
			}
			if(emp_email.length() > 0 || emp_confirm_email.length() > 0) {
				if (emp_email.equals(emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "確認入力と一致しません");
				} else if (checkMailAddress(emp_email, emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "正しく入力してください");
				}
			}
			RequestDispatcher view = request.getRequestDispatcher("/add.jsp");
			view.forward(request, response);
		} else if (emp_name.length() > 32 || emp_name.length() == 0) {
			// 社員名必須と1～32 文字のテキストエラーメッセージ
			request.setAttribute("imp_name_errmsg", "氏名は 1～32 文字のテキストで入力してください");
			request.setAttribute("value_remain", inp_value(emp_id, emp_name, emp_email, emp_confirm_email, emp_gender, emp_dept, emp_input_date));
			if(emp_email.length() > 0 || emp_confirm_email.length() > 0) {
				if (emp_email.equals(emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "確認入力と一致しません");
				} else if (checkMailAddress(emp_email, emp_confirm_email) == false) {
					request.setAttribute("imp_email_confirm_errmsg", "正しく入力してください");
				}
			}
			RequestDispatcher view = request.getRequestDispatcher("/add.jsp");
			view.forward(request, response);
		} else if (checkMailAddress(emp_email, emp_confirm_email) == false) {
			if (emp_email.equals(emp_confirm_email) == false) {
				request.setAttribute("imp_email_confirm_errmsg", "確認入力と一致しません");
			} else {
				request.setAttribute("imp_email_confirm_errmsg", "正しく入力してください");
			}
			request.setAttribute("value_remain", inp_value(emp_id, emp_name, emp_email, emp_confirm_email, emp_gender, emp_dept, emp_input_date));
			RequestDispatcher view = request.getRequestDispatcher("/add.jsp");
			view.forward(request, response);
		} else {
			Employee employee = new Employee();
			employee.setEmpID(Integer.parseInt(request.getParameter("empid")));
			employee.setName(request.getParameter("name"));
			employee.setEmail(request.getParameter("email"));
			employee.setGender(Integer.parseInt(request.getParameter("gender")));
			employee.setDept(request.getParameter("dept"));
			if (emp_input_date.length() == 0) {
				employee.setEmpdate(null);
			} else {
				try {
					Date empdate = new SimpleDateFormat("yyyy-MM-dd").parse(emp_input_date);
					employee.setEmpdate(empdate);
				} catch (ParseException e){
					e.printStackTrace();
					RequestDispatcher view = request.getRequestDispatcher("/error_page.jsp");
					view.forward(request, response);
				}
			}
			if (dao.addEmployee(employee) == false) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/error_page.jsp");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/register_successful.jsp");
				request.setAttribute("employees", dao.getAllEmployees());
				view.forward(request, response);
			}
		}
	}

	// 社員番号が整数である
	public boolean isNumber(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// 入力されたあたいを配列化し
	public Map<String, String> inp_value (String imp_id, String name, String email, String confirm_email, String gender, String dept, String imp_date) {
		Map<String, String> inp_map = new HashMap<String, String>();
		inp_map.put("imp_id", imp_id);
		inp_map.put("imp_name", name);
		inp_map.put("imp_email", email);
		inp_map.put("imp_confirm_email", confirm_email);
		inp_map.put("imp_gender", gender);
		inp_map.put("imp_dept", dept);
		inp_map.put("imp_date", imp_date);
		return inp_map;
	}

	// メールアドレスチェック
	public static boolean checkMailAddress(String address, String address2) {
		if (address.length() == 0) {
			return true;
		}
		if (address.equals(address2) == false) {
			return false;
		}
		String aText = "[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]";
		String dotAtom = aText + "+" + "(\\." + aText + "+)*";
		String regularExpression = "^" + dotAtom + "@" + dotAtom + "$";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(address);
		if (address.length() > 250) {
			return false;
		}
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}
