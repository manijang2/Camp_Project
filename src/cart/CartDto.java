package cart;

import java.sql.Timestamp;

public class CartDto {
	private int c_num, c_pcode, c_quantity,p_price, p_quantity,p_shippingfee,m_mileage;
	private float p_mileagerate;
	private String c_id, p_image1,p_name,m_name,m_phone,m_zipcode,m_address;
	
	//memberdto
	private String m_id,m_pwd,m_email,m_grade;
	private Timestamp m_date;
	
	//productdto
	private String p_info ,p_image2,p_image3,p_image4,p_brand,p_origin,ct_name1, ct_name2;
	private int p_code, p_stock,ct_num, p_sales,p_cnum;
	
	
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
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
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
	public String getP_info() {
		return p_info;
	}
	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	public String getP_image2() {
		return p_image2;
	}
	public void setP_image2(String p_image2) {
		this.p_image2 = p_image2;
	}
	public String getP_image3() {
		return p_image3;
	}
	public void setP_image3(String p_image3) {
		this.p_image3 = p_image3;
	}
	public String getP_image4() {
		return p_image4;
	}
	public void setP_image4(String p_image4) {
		this.p_image4 = p_image4;
	}
	public String getP_brand() {
		return p_brand;
	}
	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}
	public String getP_origin() {
		return p_origin;
	}
	public void setP_origin(String p_origin) {
		this.p_origin = p_origin;
	}
	public String getCt_name1() {
		return ct_name1;
	}
	public void setCt_name1(String ct_name1) {
		this.ct_name1 = ct_name1;
	}
	public String getCt_name2() {
		return ct_name2;
	}
	public void setCt_name2(String ct_name2) {
		this.ct_name2 = ct_name2;
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	public int getP_stock() {
		return p_stock;
	}
	public void setP_stock(int p_stock) {
		this.p_stock = p_stock;
	}
	public int getCt_num() {
		return ct_num;
	}
	public void setCt_num(int ct_num) {
		this.ct_num = ct_num;
	}
	public int getP_sales() {
		return p_sales;
	}
	public void setP_sales(int p_sales) {
		this.p_sales = p_sales;
	}
	public int getP_cnum() {
		return p_cnum;
	}
	public void setP_cnum(int p_cnum) {
		this.p_cnum = p_cnum;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	private String p_date;
	
	
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
	public int getP_quantity() {
		return p_quantity;
	}
	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public int getC_pcode() {
		return c_pcode;
	}
	public void setC_pcode(int c_pcode) {
		this.c_pcode = c_pcode;
	}
	public int getC_quantity() {
		return c_quantity;
	}
	public void setC_quantity(int c_quantity) {
		this.c_quantity = c_quantity;
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
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getP_image1() {
		return p_image1;
	}
	public void setP_image1(String p_image1) {
		this.p_image1 = p_image1;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
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
	
}
