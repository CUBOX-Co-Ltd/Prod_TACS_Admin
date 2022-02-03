package cubox.admin.menu.vo;

public class MenuDetailVO {
	private String menu_code;
	private String menu_nm;
	private String menu_cl_code;
	private String menu_cl_nm;
	private String menu_url;
	private String sort_ordr;
	private String use_at;

	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_cl_nm() {
		return menu_cl_nm;
	}
	public void setMenu_cl_nm(String menu_cl_nm) {
		this.menu_cl_nm= menu_cl_nm;
	}
	public String getMenu_cl_code() {
		return menu_cl_code;
	}
	public void setMenu_cl_code(String menu_cl_code) {
		this.menu_cl_code = menu_cl_code;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getSort_ordr() {
		return sort_ordr;
	}
	public void setSort_ordr(String sort_ordr) {
		this.sort_ordr = sort_ordr;
	}
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}
	
	@Override
	public String toString() {
		return "MenuDetailVO [menu_code=" + menu_code + ", menu_cl_code=" + menu_cl_code + ", menu_nm=" + menu_nm
				+ ", menu_url=" + menu_url + ", sort_ordr=" + sort_ordr + ", use_at=" + use_at + "]";
	}
}
