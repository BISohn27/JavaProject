package Member;

import Bank.FixedDeposit;

//�� ������ �����ϴ� Ŭ����
public class Customer {
	private String id;
	private String password;
	private String name;
	private String account;
	private String date;
	private int balance;
	private float rate;
	private FixedDeposit fd = null;
	
	public Customer() {}

	public Customer(String id, String password,String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.balance = 0;
		this.rate = 2.0f;
		account = new Account().getStringAccount();
	}
	
	private Customer(String id, String password, String name, String account) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.balance = 0;
		this.rate = 2.0f;
		this.account = account;
	}
	
	private Customer(String id, String password, String name, String account, String date, int balance, float rate) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.account = account;
		this.date = date;
		this.balance = balance;
		this.rate = rate;
	}
	
	public static Customer createQueueCustomer(String id, String password, String name, String account) {
		return (new Customer(id,password,name,account));
	}
	
	public static Customer createCustomer(String str1, String str2, String str3, String str4, String str5, int i, float f) {
		return (new Customer(str1, str2, str3, str4, str5, i, f));
	}
	
	public void createFixedDeposit(int years, int money) {
		fd = new FixedDeposit(rate, years, money);
		System.out.println(years +"�� ���⿹��(����: "+money+ ", ����: "+ rate + ") ������ �Ϸ�Ǿ����ϴ�. �������� " + fd.getEndDate() +"�Դϴ�." );
		System.out.println(name + "�� ���⿹�� ���񽺸� �������ּż� �����մϴ�.");
	}
	
	public String viewFixedDeposit() {
		if(fd==null)
			return "���� ���ݿ� �����Ͻ� ������ �����ϴ�.";
		else {
		return "\n������: " + fd.getStartDate() + "\n������: " + fd.getEndDate()+"\n����: " + fd.getRate() + "\n���ݱⰣ: " + fd.getYears()+ "��ġ�ݾ�: " + fd.getMoney() 
		+  "\n���� �� �ݾ�: " +fd.getDeposit();
		}
	}
	
	public FixedDeposit getFixedDeposit() {
		return fd;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public float getRate() {
		return rate;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getAccount() {
		return account;
	}
}
