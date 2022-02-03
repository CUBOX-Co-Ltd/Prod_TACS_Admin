package cubox.admin.main.service.vo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserChgLogVO {
	
	private int chg_seq;
	private String fpartcd1;
	private String fuid;
	private String funm;
	private String fcdno;
	private String chg_cl_cd;
	private String chg_cl_nm;
	private String chg_cnts;
	private String chg_resn;
	private String cntn_ip;
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private JSONObject chg_cnts_json;
	
	public int getChg_seq() {
		return chg_seq;
	}
	public void setChg_seq(int chg_seq) {
		this.chg_seq = chg_seq;
	}
	public String getFpartcd1() {
		return fpartcd1;
	}
	public void setFpartcd1(String fpartcd1) {
		this.fpartcd1 = fpartcd1;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFunm() {
		return funm;
	}
	public void setFunm(String funm) {
		this.funm = funm;
	}
	public String getFcdno() {
		return fcdno;
	}
	public void setFcdno(String fcdno) {
		this.fcdno = fcdno;
	}
	public String getChg_cl_cd() {
		return chg_cl_cd;
	}
	public void setChg_cl_cd(String chg_cl_cd) {
		this.chg_cl_cd = chg_cl_cd;
	}
	public String getChg_cl_nm() {
		return chg_cl_nm;
	}
	public void setChg_cl_nm(String chg_cl_nm) {
		this.chg_cl_nm = chg_cl_nm;
	}
	public String getChg_cnts() {
		return chg_cnts;
	}
	public void setChg_cnts(String chg_cnts) {
		this.chg_cnts = chg_cnts;
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(chg_cnts);
			JSONObject json = (JSONObject)obj;
			this.chg_cnts_json = json;
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	public String getChg_resn() {
		return chg_resn;
	}
	public void setChg_resn(String chg_resn) {
		this.chg_resn = chg_resn;
	}
	public String getCntn_ip() {
		return cntn_ip;
	}
	public void setCntn_ip(String cntn_ip) {
		this.cntn_ip = cntn_ip;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_nm() {
		return reg_nm;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public JSONObject getChg_cnts_json() {
		return chg_cnts_json;
	}
	/*public void setChg_cnts_json(String chg_cnts) {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(chg_cnts);
			JSONObject json = (JSONObject)obj;
			this.chg_cnts_json = json;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	public String toString() {
		return "UserChgLogVO [chg_seq="+chg_seq
							+", fpartcd1="+fpartcd1
							+", fuid="+fuid
							+", fcdno="+fcdno
							+", chg_cl_cd="+chg_cl_cd
							+", chg_cnts="+chg_cnts
							+", chg_cnts_json="+chg_cnts_json
							+", chg_resn="+chg_resn
							+", reg_dt="+reg_dt
							+", reg_id="+reg_id
							+", reg_nm="+reg_nm
							+"]";
	}	
	
}
