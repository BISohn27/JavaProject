package Member;

public class Account {								//���� ȸ�� ������ ����� ������ ��ȣ�� �ο��Ǿ����� �����ϱ� ���Ͽ� ststic ������ �����Ͽ� ����
	private static int system_lower;				//������ lower ������ �����ؾ��� ��ȣ�� ����
	private static int system_middle=100;			//������ middle ������ �����ؾ��� ��ȣ�� ����
	private static int system_upper=100;			//������ upper ������ �����ؾ��� ��ȣ�� ����
	
	private int lower;								//���� ���¹�ȣ �� lower �ڸ� ��ȣ�� ����
	private int middle;								//���� ���¹�ȣ �� middle �ڸ� ��ȣ�� ����
	private int upper;								//���� ���¹�ȣ �� upper �ڸ� ��ȣ�� ����
	private String account;
	
	public Account() {
		if(system_lower < 1000000) {				//lower�� 6�ڸ� ���� ���ϰ�, 6�ڸ� ���� �Ѿ�� middle ������ ������Ŵ
			lower = system_lower;					//system_lower�� ���� ���� ������ �� lower������ ����Ǿ�� �� ���� ����Ǿ� ���� 
			middle = system_middle;					//system_lower���� 6�ڸ��� �Ѿ�� ������ system_middle�� ���� ���� ���� �����Ƿ� system_middle�� ���� ��� ���� 
			upper = system_upper;					//system_upper�� middle�� ����
			system_lower++;							//system_lower�� ����Ǿ� �ִ� ���� ������ �ο� �����Ƿ� ���� ���� ����
		}else {                                     //���� system_lower ���� 6�ڸ��� �Ѿ��(1000000�� ��) system_middle�� �������Ѿ� ��
			if(system_middle < 200) {				//system_middle�� 1xx�� �ο���, 200���� ���� ���� 
				lower = 0;							//lower �������� 0�� �����ϰ�, system_lower�� 0���� ���� 000001�� ����
				system_lower = 1;
				system_middle++;					//lower�� 6�ڸ��� �Ѿ middle�� �ϳ� �������Ѿ� �ϴ� ��Ȳ�̹Ƿ� system_middle�� �ϳ� ������Ű��
				middle = system_middle;				//������ system_middle ���� middle�� �������ش�.
			}
			else {
				if(system_upper <200) {
					upper++;							//upper ������ 1xx�� ��ȣ�� �ο��ϸ�, middle�� 200�� �Ǹ� middle�� 100���� �����, upper�� �ϳ��� ��������, ���� upper ��ȣ�� �Ҵ�� ���¹�ȣ�� ����
					lower=0;							//lower�� 0�� �ǰ�
					middle=100;							//middle�� 100�� �ǰ�
					system_lower= 1;					//system_lower= 1�� �ǰ�
					system_middle= 100;					//system_middle = 100�� ����� ���� upper ��ȣ�� ���¸� ����
				}
				else {
					System.out.println("���̻� ���¸� ������ �� �����ϴ�.");
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
