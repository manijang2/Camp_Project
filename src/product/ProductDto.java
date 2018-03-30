package product;

public class ProductDto {
	private String p_name, p_info, p_image1= "ready.png",p_image2,p_image3,p_image4,p_brand,p_origin,ct_name1, ct_name2;
	private int p_code, p_stock, p_price,ct_num,p_shippingfee = 2500, p_sales  = 0,p_cnum;
	private float p_mileagerate  = (float) 0.01;
	private String p_date;

	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	@Override
	public String toString() {
		return "ProductDto [p_name=" + p_name + ", p_info=" + p_info + ", p_image1=" + p_image1 + ", p_image2="
				+ p_image2 + ", p_image3=" + p_image3 + ", p_image4=" + p_image4 + ", p_brand=" + p_brand
				+ ", p_origin=" + p_origin + ", ct_name1=" + ct_name1 + ", ct_name2=" + ct_name2 + ", p_code=" + p_code
				+ ", p_stock=" + p_stock + ", p_price=" + p_price + ", ct_num=" + ct_num + ", p_shippingfee="
				+ p_shippingfee + ", p_sales=" + p_sales + ", p_cnum=" + p_cnum + ", p_mileagerate=" + p_mileagerate
				+ ", p_date=" + p_date + "]";
	}
	public String getP_info() {
		return p_info;
	}
	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	public String getP_image1() {
		return p_image1;
	}
	public void setP_image1(String p_image1) {
		this.p_image1 = p_image1;
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
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getCt_num() {
		return ct_num;
	}
	public void setCt_num(int ct_num) {
		this.ct_num = ct_num;
	}
	public int getP_shippingfee() {
		return p_shippingfee;
	}
	public void setP_shippingfee(int p_shippingfee) {
		this.p_shippingfee = p_shippingfee;
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
	public float getP_mileagerate() {
		return p_mileagerate;
	}
	public void setP_mileagerate(float p_mileagerate) {
		this.p_mileagerate = p_mileagerate;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}	
}
