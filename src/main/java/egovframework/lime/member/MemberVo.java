package egovframework.lime.member;

public class MemberVo {
	
	private String mbr_id;/*아이디*/
	private String auth_cd;/*권한코드*/
	private String mbr_nm;/*사용자명*/
	private String passwd;/*비밀번호*/
	private String inst_cd;/*소속기관_코드*/
	private String inst_etc;/*소속기관_기타*/
	private String dept_nm;/*담당부서*/
	private String inst_adm_cd;/*소속지역_코드*/
	private String email;/*이메일*/
	private String reg_dt;/*등록일*/
	private String chg_dt;/*수정일*/
	private boolean isAdmin;    //관리자 여부
	public String getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}
	public String getAuth_cd() {
		return auth_cd;
	}
	public void setAuth_cd(String auth_cd) {
		this.auth_cd = auth_cd;
	}
	public String getMbr_nm() {
		return mbr_nm;
	}
	public void setMbr_nm(String mbr_nm) {
		this.mbr_nm = mbr_nm;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getInst_cd() {
		return inst_cd;
	}
	public void setInst_cd(String inst_cd) {
		this.inst_cd = inst_cd;
	}
	public String getInst_etc() {
		return inst_etc;
	}
	public void setInst_etc(String inst_etc) {
		this.inst_etc = inst_etc;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getInst_adm_cd() {
		return inst_adm_cd;
	}
	public void setInst_adm_cd(String inst_adm_cd) {
		this.inst_adm_cd = inst_adm_cd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getChg_dt() {
		return chg_dt;
	}
	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}