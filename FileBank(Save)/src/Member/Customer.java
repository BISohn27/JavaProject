package Member;

import Bank.FixedDeposit;

//고객 정보를 생성하는 클래스
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
		System.out.println(years +"년 정기예금(원금: "+money+ ", 이율: "+ rate + ") 가입이 완료되었습니다. 만기일은 " + fd.getEndDate() +"입니다." );
		System.out.println(name + "님 정기예금 서비스를 가입해주셔서 감사합니다.");
	}
	
	public String viewFixedDeposit() {
		if(fd==null)
			return "정기 예금에 가입하신 정보가 없습니다.";
		else {
		return "\n가입일: " + fd.getStartDate() + "\n만기일: " + fd.getEndDate()+"\n이율: " + fd.getRate() + "\n예금기간: " + fd.getYears()+ "예치금액: " + fd.getMoney() 
		+  "\n만기 후 금액: " +fd.getDeposit();
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
