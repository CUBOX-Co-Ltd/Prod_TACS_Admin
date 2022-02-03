package cubox.admin.main.service.vo;

import java.util.List;
import java.util.Map;

import cubox.admin.main.service.vo.UserInfoSearchVO;

public class UserInfoVO extends UserInfoSearchVO {

	private String fuid;
	private String funm;
	private String futype;
	private String futypenm;
	private String fregdt;
	private	String fmoddt;
	private String fauthtype;
	private String fauthtypenm;
	private String fpin;
	private String fauthyn;
	private String fsidx;
	private String fgroupid;
	private String fpartnm1;
	private String fpartnm2;
	private String fpartnm3;
	private String fpartcd1;
	private String fpartcd2;
	private String fpartcd3;
	private String fvisitnum;
	private String fsstatus;
	private String fqstatus;
	private String factdt;
	private String famodt;
	private String faupdt;
	private String fqstdt;
	private String fusdt;
	private String fuedt;
	private String fusdt2;
	private String fuedt2;
	private String fustatus;
	private String fustatusnm;
	private String fwvname;
	private String fwedt;
	private String fwsdt;
	private String ncia_iden;
	private String fcardfg;
	private String fmobilefg;
	private String fevfg;
	private String ftel;
	private String fcarno;
	private String efgateuid;
	private String cfcdno;
	private String cfsdt;
	private String cfedt;
	private String cftype;
	private String cfstatus;
	private String cfstatusnm;
	private String cfsidx;
	private String cfauthyn;
	private String cfregdt;
	private String cfmoddt;
	private String cfamodt;
	private String cfaupdt;
	private String cfqstatus;
	private String cfsstatus;
	private String cfcdnum;
	private String fkind3;
	private String fpartcdnm1;
	private String fvalue;
	private String chkValueArray;
	private String chkTextArray;
	private String fetc1;
	private String fetc2;
	private List<Map<String, Object>> fuids;
	private String fsvtype;
	private String fbioyn;
	private String fexpireyn;
	private String authorGroupNm;
	private String newFcdno;/* 신규카드 번호*/
	private String fregid;
	private String fmodid;
	private String hpNo;
	private String siteNm;
	private String bfsstatus; /* bio상태 */
	private int fumoddays;
	private String fissyn;  /* 방문객카드 발급여부 */
	private String cf_uc_status; /* 출입자&카드 유효상태 */
	
	public String getCfUcStatus() {
		return cf_uc_status;
	}
	public void setCfUcStatus(String cf_uc_status) {
		this.cf_uc_status = cf_uc_status;
	}
	public String getFissyn() {
		return fissyn;
	}
	public void setFissyn(String fissyn) {
		this.fissyn = fissyn;
	}
	public int getFumoddays() {
		return fumoddays;
	}
	public void setFumoddays(int fumoddays) {
		this.fumoddays = fumoddays;
	}
	public String getNewFcdno() {
		return newFcdno;
	}
	public void setNewFcdno(String newFcdno) {
		this.newFcdno = newFcdno;
	}
	public String getAuthorGroupNm() {
		return authorGroupNm;
	}
	public void setAuthorGroupNm(String authorGroupNm) {
		this.authorGroupNm = authorGroupNm;
	}
	public String getFsvtype() {
		return fsvtype;
	}
	public void setFsvtype(String fsvtype) {
		this.fsvtype = fsvtype;
	}
	public List<Map<String, Object>> getFuids() {
		return fuids;
	}
	public void setFuids(List<Map<String, Object>> fuids) {
		this.fuids = fuids;
	}
	public String getFkind3() {
		return fkind3;
	}
	public void setFkind3(String fkind3) {
		this.fkind3 = fkind3;
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
	public String getFutype() {
		return futype;
	}
	public void setFutype(String futype) {
		this.futype = futype;
	}
	public String getFutypenm() {
		return futypenm;
	}
	public void setFutypenm(String futypenm) {
		this.futypenm = futypenm;
	}
	public String getFregdt() {
		return fregdt;
	}
	public void setFregdt(String fregdt) {
		this.fregdt = fregdt;
	}
	public String getFmoddt() {
		return fmoddt;
	}
	public void setFmoddt(String fmoddt) {
		this.fmoddt = fmoddt;
	}
	public String getFauthtype() {
		return fauthtype;
	}
	public void setFauthtype(String fauthtype) {
		this.fauthtype = fauthtype;
	}
	public String getFauthtypenm() {
		return fauthtypenm;
	}
	public void setFauthtypenm(String fauthtypenm) {
		this.fauthtypenm = fauthtypenm;
	}
	public String getFpin() {
		return fpin;
	}
	public void setFpin(String fpin) {
		this.fpin = fpin;
	}
	public String getFauthyn() {
		return fauthyn;
	}
	public void setFauthyn(String fauthyn) {
		this.fauthyn = fauthyn;
	}
	public String getFsidx() {
		return fsidx;
	}
	public void setFsidx(String fsidx) {
		this.fsidx = fsidx;
	}
	public String getFgroupid() {
		return fgroupid;
	}
	public void setFgroupid(String fgroupid) {
		this.fgroupid = fgroupid;
	}
	public String getFpartnm1() {
		return fpartnm1;
	}
	public void setFpartnm1(String fpartnm1) {
		this.fpartnm1 = fpartnm1;
	}
	public String getFpartnm2() {
		return fpartnm2;
	}
	public void setFpartnm2(String fpartnm2) {
		this.fpartnm2 = fpartnm2;
	}
	public String getFpartnm3() {
		return fpartnm3;
	}
	public void setFpartnm3(String fpartnm3) {
		this.fpartnm3 = fpartnm3;
	}
	public String getFpartcd1() {
		return fpartcd1;
	}
	public void setFpartcd1(String fpartcd1) {
		this.fpartcd1 = fpartcd1;
	}
	public String getFpartcd2() {
		return fpartcd2;
	}
	public void setFpartcd2(String fpartcd2) {
		this.fpartcd2 = fpartcd2;
	}
	public String getFpartcd3() {
		return fpartcd3;
	}
	public void setFpartcd3(String fpartcd3) {
		this.fpartcd3 = fpartcd3;
	}
	public String getFvisitnum() {
		return fvisitnum;
	}
	public void setFvisitnum(String fvisitnum) {
		this.fvisitnum = fvisitnum;
	}
	public String getFsstatus() {
		return fsstatus;
	}
	public void setFsstatus(String fsstatus) {
		this.fsstatus = fsstatus;
	}
	public String getFqstatus() {
		return fqstatus;
	}
	public void setFqstatus(String fqstatus) {
		this.fqstatus = fqstatus;
	}
	public String getFactdt() {
		return factdt;
	}
	public void setFactdt(String factdt) {
		this.factdt = factdt;
	}
	public String getFamodt() {
		return famodt;
	}
	public void setFamodt(String famodt) {
		this.famodt = famodt;
	}
	public String getFaupdt() {
		return faupdt;
	}
	public void setFaupdt(String faupdt) {
		this.faupdt = faupdt;
	}
	public String getFqstdt() {
		return fqstdt;
	}
	public void setFqstdt(String fqstdt) {
		this.fqstdt = fqstdt;
	}
	public String getFusdt() {
		return fusdt;
	}
	public void setFusdt(String fusdt) {
		this.fusdt = fusdt;
	}
	public String getFuedt() {
		return fuedt;
	}
	public void setFuedt(String fuedt) {
		this.fuedt = fuedt;
	}
	public String getFusdt2() {
		return fusdt2;
	}
	public void setFusdt2(String fusdt2) {
		this.fusdt2 = fusdt2;
	}
	public String getFuedt2() {
		return fuedt2;
	}
	public void setFuedt2(String fuedt2) {
		this.fuedt2 = fuedt2;
	}
	public String getFustatus() {
		return fustatus;
	}
	public void setFustatus(String fustatus) {
		this.fustatus = fustatus;
	}
	public String getFustatusnm() {
		return fustatusnm;
	}
	public void setFustatusnm(String fustatusnm) {
		this.fustatusnm = fustatusnm;
	}
	public String getFwvname() {
		return fwvname;
	}
	public void setFwvname(String fwvname) {
		this.fwvname = fwvname;
	}
	public String getFwedt() {
		return fwedt;
	}
	public void setFwedt(String fwedt) {
		this.fwedt = fwedt;
	}
	public String getFwsdt() {
		return fwsdt;
	}
	public void setFwsdt(String fwsdt) {
		this.fwsdt = fwsdt;
	}
	public String getNcia_iden() {
		return ncia_iden;
	}
	public void setNcia_iden(String ncia_iden) {
		this.ncia_iden = ncia_iden;
	}
	public String getFcardfg() {
		return fcardfg;
	}
	public void setFcardfg(String fcardfg) {
		this.fcardfg = fcardfg;
	}
	public String getFmobilefg() {
		return fmobilefg;
	}
	public void setFmobilefg(String fmobilefg) {
		this.fmobilefg = fmobilefg;
	}
	public String getFevfg() {
		return fevfg;
	}
	public void setFevfg(String fevfg) {
		this.fevfg = fevfg;
	}
	public String getEfgateuid() {
		return efgateuid;
	}
	public void setEfgateuid(String efgateuid) {
		this.efgateuid = efgateuid;
	}
	public String getCfcdno() {
		return cfcdno;
	}
	public void setCfcdno(String cfcdno) {
		this.cfcdno = cfcdno;
	}
	public String getCfsdt() {
		return cfsdt;
	}
	public void setCfsdt(String cfsdt) {
		this.cfsdt = cfsdt;
	}
	public String getCfedt() {
		return cfedt;
	}
	public void setCfedt(String cfedt) {
		this.cfedt = cfedt;
	}
	public String getCftype() {
		return cftype;
	}
	public void setCftype(String cftype) {
		this.cftype = cftype;
	}
	public String getCfstatus() {
		return cfstatus;
	}
	public void setCfstatus(String cfstatus) {
		this.cfstatus = cfstatus;
	}
	public String getCfstatusnm() {
		return cfstatusnm;
	}
	public void setCfstatusnm(String cfstatusnm) {
		this.cfstatusnm = cfstatusnm;
	}
	public String getCfsidx() {
		return cfsidx;
	}
	public void setCfsidx(String cfsidx) {
		this.cfsidx = cfsidx;
	}
	public String getCfauthyn() {
		return cfauthyn;
	}
	public void setCfauthyn(String cfauthyn) {
		this.cfauthyn = cfauthyn;
	}
	public String getCfregdt() {
		return cfregdt;
	}
	public void setCfregdt(String cfregdt) {
		this.cfregdt = cfregdt;
	}
	public String getCfmoddt() {
		return cfmoddt;
	}
	public void setCfmoddt(String cfmoddt) {
		this.cfmoddt = cfmoddt;
	}
	public String getCfamodt() {
		return cfamodt;
	}
	public void setCfamodt(String cfamodt) {
		this.cfamodt = cfamodt;
	}
	public String getCfaupdt() {
		return cfaupdt;
	}
	public void setCfaupdt(String cfaupdt) {
		this.cfaupdt = cfaupdt;
	}
	public String getCfqstatus() {
		return cfqstatus;
	}
	public void setCfqstatus(String cfqstatus) {
		this.cfqstatus = cfqstatus;
	}
	public String getCfsstatus() {
		return cfsstatus;
	}
	public void setCfsstatus(String cfsstatus) {
		this.cfsstatus = cfsstatus;
	}
	public String getFpartcdnm1() {
		return fpartcdnm1;
	}
	public void setFpartcdnm1(String fpartcdnm1) {
		this.fpartcdnm1 = fpartcdnm1;
	}
	public String getFtel() {
		return ftel;
	}
	public void setFtel(String ftel) {
		this.ftel = ftel;
	}
	public String getFcarno() {
		return fcarno;
	}
	public void setFcarno(String fcarno) {
		this.fcarno = fcarno;
	}
	public String getCfcdnum() {
		return cfcdnum;
	}
	public void setCfcdnum(String cfcdnum) {
		this.cfcdnum = cfcdnum;
	}
	public String getFvalue() {
		return fvalue;
	}
	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}
	public String getChkValueArray() {
		return chkValueArray;
	}
	public void setChkValueArray(String chkValueArray) {
		this.chkValueArray = chkValueArray;
	}
	public String getChkTextArray() {
		return chkTextArray;
	}
	public void setChkTextArray(String chkTextArray) {
		this.chkTextArray = chkTextArray;
	}
	public String getFetc1() {
		return fetc1;
	}
	public void setFetc1(String fetc1) {
		this.fetc1 = fetc1;
	}
	public String getFetc2() {
		return fetc2;
	}
	public void setFetc2(String fetc2) {
		this.fetc2 = fetc2;
	}

	public String getFbioyn() {
		return fbioyn;
	}
	public void setFbioyn(String fbioyn) {
		this.fbioyn = fbioyn;
	}

	public String getFexpireyn() {
		return fexpireyn;
	}
	public void setFexpireyn(String fexpireyn) {
		this.fexpireyn = fexpireyn;
	}
	public String getFregid() {
		return fregid;
	}
	public void setFregid(String fregid) {
		this.fregid = fregid;
	}
	public String getFmodid() {
		return fmodid;
	}
	public void setFmodid(String fmodid) {
		this.fmodid = fmodid;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}	
	public String getBfsstatus() {
		return bfsstatus;
	}
	public void setBfsstatus(String bfsstatus) {
		this.bfsstatus = bfsstatus;
	}
	
	@Override
	public String toString() {
		return "UserInfoVO [fuid=" + fuid + ", funm=" + funm + ", futype=" + futype + ", futypenm=" + futypenm
				+ ", fregdt=" + fregdt + ", fmoddt=" + fmoddt + ", fauthtype=" + fauthtype + ", fauthtypenm="
				+ fauthtypenm + ", fpin=" + fpin + ", fauthyn=" + fauthyn + ", fsidx=" + fsidx + ", fgroupid="
				+ fgroupid + ", fpartnm1=" + fpartnm1 + ", fpartnm2=" + fpartnm2 + ", fpartnm3=" + fpartnm3
				+ ", fpartcd1=" + fpartcd1 + ", fpartcd2=" + fpartcd2 + ", fpartcd3=" + fpartcd3 + ", fvisitnum="
				+ fvisitnum + ", fsstatus=" + fsstatus + ", fqstatus=" + fqstatus + ", factdt=" + factdt + ", famodt="
				+ famodt + ", faupdt=" + faupdt + ", fqstdt=" + fqstdt + ", fusdt=" + fusdt + ", fuedt=" + fuedt
				+ ", fusdt2=" + fusdt2 + ", fuedt2=" + fuedt2 + ", fustatus=" + fustatus + ", fwvname=" + fwvname
				+ ", fwedt=" + fwedt + ", fwsdt=" + fwsdt + ", ncia_iden=" + ncia_iden + ", fcardfg=" + fcardfg
				+ ", fmobilefg=" + fmobilefg + ", fevfg=" + fevfg + ", ftel=" + ftel + ", fcarno=" + fcarno
				+ ", efgateuid=" + efgateuid + ", cfcdno=" + cfcdno + ", cfsdt=" + cfsdt + ", cfedt=" + cfedt
				+ ", cftype=" + cftype + ", cfstatus=" + cfstatus + ", cfstatusnm=" + cfstatusnm + ", cfsidx=" + cfsidx
				+ ", cfauthyn=" + cfauthyn + ", cfregdt=" + cfregdt + ", cfmoddt=" + cfmoddt + ", cfamodt=" + cfamodt
				+ ", cfaupdt=" + cfaupdt + ", cfqstatus=" + cfqstatus + ", cfsstatus=" + cfsstatus + ", cfcdnum="
				+ cfcdnum + ", fkind3=" + fkind3 + ", fpartcdnm1=" + fpartcdnm1 + ", fvalue=" + fvalue
				+ ", chkValueArray=" + chkValueArray + ", chkTextArray=" + chkTextArray + ", fetc1=" + fetc1
				+ ", fetc2=" + fetc2 + ", fuids=" + fuids + ", fsvtype=" + fsvtype + ", fbioyn=" + fbioyn
				+ ", fexpireyn=" + fexpireyn + ", hpNo=" + hpNo + ", siteNm=" + siteNm
				+ ", authorGroupNm=" + authorGroupNm + ", bfsstatus=" + bfsstatus
				+ ", fregid=" + fregid + ", fmodid=" + fmodid + "]";
	}
}
