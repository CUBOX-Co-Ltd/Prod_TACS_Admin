package cubox.admin.main.service.vo;

import java.util.List;

public class MenuClVO {
	
	private String menuClCd;
	private String menuClNm;
	private String iconImage;
	private String sortOrdr;
	private String useYn;
	private List<MenuVO> list;
	
	public String getMenuClCd() {
		return menuClCd;
	}
	public void setMenuClCd(String menuClCd) {
		this.menuClCd = menuClCd;
	}
	public String getMenuClNm() {
		return menuClNm;
	}
	public void setMenuClNm(String menuClNm) {
		this.menuClNm = menuClNm;
	}
	public String getIconImage() {
		return iconImage;
	}
	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}
	public String getSortOrdr() {
		return sortOrdr;
	}
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public List<MenuVO> getList() {
		return list;
	}
	public void setList(List<MenuVO> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "MenuClVO [menuClCd=" + menuClCd + ", menuClNm=" + menuClNm + ", sortOrdr=" + sortOrdr
				+ ", useYn=" + useYn + ", list=" + list + "]";
	}
}
