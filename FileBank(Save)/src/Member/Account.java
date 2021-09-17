package Member;

public class Account {								//전에 회원 가입한 사람이 어디까지 번호가 부여되었는지 추적하기 위하여 ststic 변수로 선언하여 추적
	private static int system_lower;				//다음에 lower 변수에 저장해야할 번호를 추적
	private static int system_middle=100;			//다음에 middle 변수에 저장해야할 번호를 추적
	private static int system_upper=100;			//다음에 upper 변수에 저장해야할 번호를 추적
	
	private int lower;								//고객의 계좌번호 중 lower 자리 번호를 저장
	private int middle;								//고객의 계좌번호 중 middle 자리 번호를 저장
	private int upper;								//고객의 계좌번호 중 upper 자리 번호를 저장
	private String account;
	
	public Account() {
		if(system_lower < 1000000) {				//lower는 6자리 수로 정하고, 6자리 수를 넘어가면 middle 변수를 증가시킴
			lower = system_lower;					//system_lower는 다음 고객이 가입할 시 lower변수에 저장되어야 할 값이 저장되어 있음 
			middle = system_middle;					//system_lower값이 6자리를 넘어가지 않으면 system_middle의 값이 변경 되지 않으므로 system_middle의 값을 계속 대입 
			upper = system_upper;					//system_upper도 middle과 동일
			system_lower++;							//system_lower에 저장되어 있는 수를 고객에게 부여 했으므로 다음 수를 저장
		}else {                                     //만약 system_lower 값이 6자리를 넘어가면(1000000일 때) system_middle을 증가시켜야 함
			if(system_middle < 200) {				//system_middle은 1xx로 부여함, 200보다 작을 때는 
				lower = 0;							//lower 변수에는 0을 대입하고, system_lower는 0다음 수인 000001을 대입
				system_lower = 1;
				system_middle++;					//lower가 6자리를 넘어가 middle을 하나 증가시켜야 하는 상황이므로 system_middle을 하나 증가시키고
				middle = system_middle;				//증가된 system_middle 값을 middle에 대입해준다.
			}
			else {
				if(system_upper <200) {
					upper++;							//upper 변수도 1xx로 번호를 부여하며, middle이 200이 되면 middle을 100으로 만들고, upper는 하나를 증가시켜, 다음 upper 번호에 할당된 계좌번호를 개설
					lower=0;							//lower는 0이 되고
					middle=100;							//middle은 100이 되고
					system_lower= 1;					//system_lower= 1이 되고
					system_middle= 100;					//system_middle = 100로 만들어 다음 upper 번호의 계좌를 개설
				}
				else {
					System.out.println("더이상 계좌를 개설할 수 없습니다.");
					upper = 200;
					middle = 100;
					lower = 0;
				}
			}
		}
		
		account = upper + "-" + middle + "-" + String.format("%06d", lower);
	}
		
	public static void setAccount(int upper, int middle, int lower) {
		setSystem_upper(upper);
		setSystem_middle(middle);
		setSystem_lower(lower);
	}
	
	public static int getSystem_lower() {
		return system_lower;
	}

	public static void setSystem_lower(int system_lower) {
		Account.system_lower = system_lower;
	}

	public static int getSystem_middle() {
		return system_middle;
	}

	public static void setSystem_middle(int system_middle) {
		Account.system_middle = system_middle;
	}

	public static int getSystem_upper() {
		return system_upper;
	}

	public static void setSystem_upper(int system_upper) {
		Account.system_upper = system_upper;
	}

	public String getStringAccount() {
		return account;
	}

//	public int getLower() {
//		return lower;
//	}
//
//	public int getMiddle() {
//		return middle;
//	}
//
//	public int getUpper() {
//		return upper;
//	}
}
