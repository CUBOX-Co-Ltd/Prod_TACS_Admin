package cubox.admin.menu.vo;

import java.util.List;

public class MenuClVO {
	private String menu_cl_code;
	private String menu_cl_nm;
	private String icon_img;
	private int sort_ordr;
	private String use_at;
	private List<MenuDetailVO> list;

	public String getMenu_cl_code() {
		return menu_cl_code;
	}
	public void setMenu_cl_code(String menu_cl_code) {
		this.menu_cl_code = menu_cl_code;
	}
	public String getMenu_cl_nm() {
		return menu_cl_nm;
	}
	public void setMenu_cl_nm(String menu_cl_nm) {
		this.menu_cl_nm = menu_cl_nm;
	}
	public String getIcon_img() {
		return icon_img;
	}
	public void setIcon_img(String icon_img) {
		this.icon_img = icon_img;
	}
	public int getSort_ordr() {
		return sort_ordr;
	}
	public void setSort_ordr(int sort_ordr) {
		this.sort_ordr = sort_ordr;
	}
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}
	public List<MenuDetailVO> getList() {
		return list;
	}
	public void setList(List<MenuDetailVO> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "MenuClVO [menu_cl_code=" + menu_cl_code + ", menu_cl_nm=" + menu_cl_nm + ", sort_ordr=" + sort_ordr
				+ ", use_at=" + use_at + ", list=" + list + "]";
	}
}
