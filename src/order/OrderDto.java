package order;

import java.sql.Timestamp;

public class OrderDto {
	//private Timestamp o_date;
	private String o_date;
	private int o_num,o_pcode,o_quantity,o_usemileage,o_pay,o_shippingfee,os_num,p_price,p_shippingfee,m_mileage,c_quantity,p_code;
	private float p_mileagerate;
	private String o_id, o_mname, o_phone,o_zipcode,o_address,o_status,os_status,p_name,p_image1,o_message,c_id;
	
	private String m_id,m_pwd,m_name,m_email,m_phone,m_zipcode,m_address,m_grade;
	
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
	public Timestamp getM_date() {
		return m_date;
	}
	public void setM_date(Timestamp m_date) {
		this.m_date = m_date;
	}
	private Timestamp m_date;
	
	
	@Override
	public String toString() {
		return "OrderDto [o_date=" + o_date + ", o_num=" + o_num + ", o_pcode=" + o_pcode + ", o_quantity=" + o_quantity
				+ ", o_usemileage=" + o_usemileage + ", o_pay=" + o_pay + ", o_shippingfee=" + o_shippingfee
				+ ", os_num=" + os_num + ", p_price=" + p_price + ", p_shippingfee=" + p_shippingfee + ", m_mileage="
				+ m_mileage + ", c_quantity=" + c_quantity + ", p_code=" + p_code + ", p_mileagerate=" + p_mileagerate
				+ ", o_id=" + o_id + ", o_mname=" + o_mname + ", o_phone=" + o_phone + ", o_zipcode=" + o_zipcode
				+ ", o_address=" + o_address + ", o_status=" + o_status + ", os_status=" + os_status + ", p_name="
				+ p_name + ", p_image1=" + p_image1 + ", o_message=" + o_message + ", c_id=" + c_id + "]";
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	public int getC_quantity() {
		return c_quantity;
	}
	public void setC_quantity(int c_quantity) {
		this.c_quantity = c_quantity;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getO_message() {
		return o_message;
	}
	public void setO_message(String o_message) {
		this.o_message = o_message;
	}
	public int getM_mileage() {
		return m_mileage;
	}
	public void setM_mileage(int m_mileage) {
		this.m_mileage = m_mileage;
	}
	public int getP_shippingfee() {
		return p_shippingfee;
	}
	public void setP_shippingfee(int p_shippingfee) {
		this.p_shippingfee = p_shippingfee;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public float getP_mileagerate() {
		return p_mileagerate;
	}
	public void setP_mileagerate(float p_mileagerate) {
		this.p_mileagerate = p_mileagerate;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_image1() {
		return p_image1;
	}
	public void setP_image1(String p_image1) {
		this.p_image1 = p_image1;
	}
	/*public Timestamp getO_date() {
		return o_date;
	}
	public void setO_date(Timestamp o_date) {
		this.o_date = o_date;
	}*/
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public int getO_pcode() {
		return o_pcode;
	}
	public void setO_pcode(int o_pcode) {
		this.o_pcode = o_pcode;
	}
	public int getO_quantity() {
		return o_quantity;
	}
	public void setO_quantity(int o_quantity) {
		this.o_quantity = o_quantity;
	}
	public int getO_usemileage() {
		return o_usemileage;
	}
	public void setO_usemileage(int o_usemileage) {
		this.o_usemileage = o_usemileage;
	}
	public int getO_pay() {
		return o_pay;
	}
	public void setO_pay(int o_pay) {
		this.o_pay = o_pay;
	}
	public int getO_shippingfee() {
		return o_shippingfee;
	}
	public void setO_shippingfee(int o_shippingfee) {
		this.o_shippingfee = o_shippingfee;
	}
	public int getOs_num() {
		return os_num;
	}
	public void setOs_num(int os_num) {
		this.os_num = os_num;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public String getO_mname() {
		return o_mname;
	}
	public void setO_mname(String o_mname) {
		this.o_mname = o_mname;
	}
	public String getO_phone() {
		return o_phone;
	}
	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}
	public String getO_zipcode() {
		return o_zipcode;
	}
	public void setO_zipcode(String o_zipcode) {
		this.o_zipcode = o_zipcode;
	}
	public String getO_address() {
		return o_address;
	}
	public void setO_address(String o_address) {
		this.o_address = o_address;
	}
	public String getO_status() {
		return o_status;
	}
	public void setO_status(String o_status) {
		this.o_status = o_status;
	}
	public String getOs_status() {
		return os_status;
	}
	public void setOs_status(String os_status) {
		this.os_status = os_status;
	}
	
	
}