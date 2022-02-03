package cubox.admin.cmmn.util;

public class ModuleUtil {
	private static ModuleUtil instance;
	private int totalRowCount = 0; // 전체 행 개수
    private int successRowCount = 0; // 성공한 데이터 개수
    private int failRowCount = 0; // 실패한 데이터 개수
    private int nullRowCount = 0; // 읽을 수 없는 데이터(null) 개수
    private int doubleRowCount = 0;// 중복된 데이터 개수
    
    private int currentStateCount = 0; 
    //위의 4개 변수들을 다 더한 값이다. 
    //for문이 돌면서 현재 몇개가 처리되었는지 알게 해주는 변수
    private String currentState; //현재 상태 
    
    static {
		instance = new ModuleUtil();
	}

	public static ModuleUtil getInstance() {
		return instance;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getSuccessRowCount() {
		return successRowCount;
	}

	public void setSuccessRowCount(int successRowCount) {
		this.successRowCount = successRowCount;
	}

	public int getFailRowCount() {
		return failRowCount;
	}

	public void setFailRowCount(int failRowCount) {
		this.failRowCount = failRowCount;
	}

	public int getNullRowCount() {
		return nullRowCount;
	}

	public void setNullRowCount(int nullRowCount) {
		this.nullRowCount = nullRowCount;
	}

	public int getDoubleRowCount() {
		return doubleRowCount;
	}

	public void setDoubleRowCount(int doubleRowCount) {
		this.doubleRowCount = doubleRowCount;
	}

	public int getCurrentStateCount() {
		return currentStateCount;
	}

	public void setCurrentStateCount(int currentStateCount) {
		this.currentStateCount = currentStateCount;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public static void setInstance(ModuleUtil instance) {
		ModuleUtil.instance = instance;
	}
	
	public void resetModuleUtil () {
		setCurrentStateCount(0);
		setTotalRowCount(0);
		setSuccessRowCount(0);
		setFailRowCount(0);
		setNullRowCount(0);
		setDoubleRowCount(0);
		setCurrentState(null);
	}
}
