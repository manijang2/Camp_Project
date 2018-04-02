package member;
import java.sql.*;
import java.sql.Timestamp;


public class MemberDto {
	
	private String m_id,m_pwd,m_name,m_email,m_phone,m_zipcode,m_address,m_grade;
	private int m_mileage;
	private Timestamp m_date;
	
	/*
	 * ischeckPasswd 가 왜 여기 구현됐는지 잘 모르겠음 !
	 */
	public boolean ischeckPasswd(String passwd){
		//수정시 비밀번호 확인
		if(this.m_pwd.equals(passwd)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "MemberDto [m_id=" + m_id + ", m_pwd=" + m_pwd + ", m_name=" + m_name + ", m_email=" + m_email
				+ ", m_phone=" + m_phone + ", m_zipcode=" + m_zipcode + ", m_address=" + m_address + ", m_grade="
				+ m_grade + ", m_mileage=" + m_mileage + ", m_date=" + m_date + "]";
	}

	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_address() {
		return m_address;
	}
	public void setM_address(String m_address) {
		this.m_address = m_address;
	}
	public String getM_grade() {
		return m_grade;
	}
	public void setM_grade(String m_grade) {
		this.m_grade = m_grade;
	}
	public int getM_mileage() {
		return m_mileage;
	}
	public void setM_mileage(int m_mileage) {
		this.m_mileage = m_mileage;
	}
	public Timestamp getM_date() {
		return m_date;
	}
	public void setM_date(Timestamp m_date) {
		this.m_date = m_date;
	}
	
	
}
