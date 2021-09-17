package Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

import Bank.ConnectionDB;
import Bank.CreateAccount;


//고객 정보를 생성하는 클래스
public class Customer {
	private String id;
	private String password;
	private String name;
	private String date;
	private float rate;
	private ArrayList<FixedDeposit> fd;
	private ArrayList<String> account;
	private Hashtable<String, Integer> deposit;

	public Customer() {
	}

	public Customer(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.rate = 2.0f;
		account = new ArrayList<>();
		account.add(new CreateAccount().getStringAccount());
		deposit = new Hashtable<>();
		deposit.put(account.get(0), 0);
	}

	public Customer(String id, String password, String name, String account) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.rate = 2.0f;
		this.account = new ArrayList<>();
		this.account.add(account);
		deposit = new Hashtable<>();
		deposit.put(this.account.get(0), 0);
	}

	// 어플 시작 후 로드 시 사용하는 생성자로, 일반 계좌와 정기 예금 계좌는 별도의 메소드로 초기화해준다.
	public Customer(String id, String password, String name, String date, float rate) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.date = date;
		this.rate = rate;
	}

	public void createFixedDeposit(int years, int money) {
		if (fd == null) {
			fd = new ArrayList<>();
			fd.add(new FixedDeposit(rate, years, money));
			System.out.println(years + "년 정기예금(원금: " + money + ", 이율: " + rate + ") 가입이 완료되었습니다. 만기일은 "
					+ fd.get(fd.size() - 1).getEndDate() + "입니다.");
			System.out.println(name + "님 정기예금 서비스를 가입해주셔서 감사합니다.");
			insertFixedDepositData();
		} else {
			fd.add(new FixedDeposit(rate, years, money));
			System.out.println(years + "년 정기예금(원금: " + money + ", 이율: " + rate + ") 가입이 완료되었습니다. 만기일은 "
					+ fd.get(fd.size() - 1).getEndDate() + "입니다.");
			System.out.println(name + "님 정기예금 서비스를 가입해주셔서 감사합니다.");
			insertFixedDepositData();
		}
	}

	public void viewFixedDeposit() {
		if (fd == null)
			System.out.println("가입하신 정기예금이 없습니다.");
		for (int i = 0; i < fd.size(); i++) {
			System.out.println("\n" + (i + 1) + "번째 정기예금 계좌\n가입일: " + fd.get(i).getStartDate() + "\n만기일: "
					+ fd.get(i).getEndDate() + "\n이율: " + fd.get(i).getRate() + "\n예금기간: " + fd.get(i).getYears()
					+ "\n예치금액: " + fd.get(i).getDeposit() + "\n만기 후 금액: " + fd.get(i).getTotal());
			System.out.println();
		}
	}

	public boolean addAccount(Queue<String> q) {
		if (account.size() <= 10) {
			if (q.isEmpty()) {
				account.add(new CreateAccount().getStringAccount());
				deposit.put(account.get(account.size() - 1), 0);
			} else {
				account.add(q.poll());
				deposit.put(account.get(account.size() - 1), 0);
			}
			return true;
		} else {
			System.out.println("개설 가능한 계좌 수(한도: 10개)를 초과하였습니다.");
			return false;
		}
	}

	public void viewAccount() {
		for (int i = 0; i < account.size(); i++)
			System.out.println((i + 1) + "번째 계좌" + account.get(i));
		System.out.println();
	}

	public int returnFixedDeposit() {
		int total = 0;
		if (fd == null) {
			return -1;
		} else {
			for (int i = 0; i < fd.size(); i++) {
				if (fd.get(i).returnDeposit() != 0) {
					total += fd.get(i).returnDeposit();
					fd.remove(i);
				}
			}
			return total;
		}
	}

	public void insertFixedDepositData() {
		FixedDeposit f = fd.get(fd.size()-1);
		Connection conn = ConnectionDB.getInstance().getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO FIXEDDEPOSIT VALUES('"+id+"',"+f.getRate()+","+f.getYears()+",'"+f.getStartDate()+"','"+f.getEndDate()+"',"+f.getDeposit()+","+f.getTotal()+")";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public StringBuilder getFixedDepositData(int number) {
		StringBuilder sb = new StringBuilder();
		FixedDeposit f = fd.get(number - 1);

		sb.append(f.getRate());
		sb.append("|");
		sb.append(f.getYears());
		sb.append("|");
		sb.append(f.getStartDate());
		sb.append("|");
		sb.append(f.getEndDate());
		sb.append("|");
		sb.append(f.getDeposit());
		sb.append("|");
		sb.append(f.getTotal());

		return sb;
	}

	public void setAccountData(ArrayList<String> account, int[] balance, ArrayList<FixedDeposit> fd) {
		this.fd = fd;
		this.account = account;
		deposit = new Hashtable<>();
		for (int i = 0; i < this.account.size(); i++) {
			deposit.put(this.account.get(i), balance[i]);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
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

	// number는 1부터 시작하는 것으로 입력 받음.
	public String getAccountNum(int number) {
		return account.get(number - 1);
	}

	public int getNumOfAccount() {
		return account.size();
	}

	// 조회하고 싶은 계좌 번호를 입력받아 해당 계좌에 들어 있는 금액을 반환
	public int getDeposit(String account) {
		return deposit.get(account);
	}

	// 외부로부터 계좌 정보를 받아 deposit hashtable의 해당 계좌에 금액을 입력
	public void setDeposit(String account, int money) {
		deposit.put(account, money);
	}
}
