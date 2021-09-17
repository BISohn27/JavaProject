package Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

import Bank.ConnectionDB;
import Bank.CreateAccount;


//�� ������ �����ϴ� Ŭ����
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

	// ���� ���� �� �ε� �� ����ϴ� �����ڷ�, �Ϲ� ���¿� ���� ���� ���´� ������ �޼ҵ�� �ʱ�ȭ���ش�.
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
			System.out.println(years + "�� ���⿹��(����: " + money + ", ����: " + rate + ") ������ �Ϸ�Ǿ����ϴ�. �������� "
					+ fd.get(fd.size() - 1).getEndDate() + "�Դϴ�.");
			System.out.println(name + "�� ���⿹�� ���񽺸� �������ּż� �����մϴ�.");
			insertFixedDepositData();
		} else {
			fd.add(new FixedDeposit(rate, years, money));
			System.out.println(years + "�� ���⿹��(����: " + money + ", ����: " + rate + ") ������ �Ϸ�Ǿ����ϴ�. �������� "
					+ fd.get(fd.size() - 1).getEndDate() + "�Դϴ�.");
			System.out.println(name + "�� ���⿹�� ���񽺸� �������ּż� �����մϴ�.");
			insertFixedDepositData();
		}
	}

	public void viewFixedDeposit() {
		if (fd == null)
			System.out.println("�����Ͻ� ���⿹���� �����ϴ�.");
		for (int i = 0; i < fd.size(); i++) {
			System.out.println("\n" + (i + 1) + "��° ���⿹�� ����\n������: " + fd.get(i).getStartDate() + "\n������: "
					+ fd.get(i).getEndDate() + "\n����: " + fd.get(i).getRate() + "\n���ݱⰣ: " + fd.get(i).getYears()
					+ "\n��ġ�ݾ�: " + fd.get(i).getDeposit() + "\n���� �� �ݾ�: " + fd.get(i).getTotal());
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
			System.out.println("���� ������ ���� ��(�ѵ�: 10��)�� �ʰ��Ͽ����ϴ�.");
			return false;
		}
	}

	public void viewAccount() {
		for (int i = 0; i < account.size(); i++)
			System.out.println((i + 1) + "��° ����" + account.get(i));
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

	// number�� 1���� �����ϴ� ������ �Է� ����.
	public String getAccountNum(int number) {
		return account.get(number - 1);
	}

	public int getNumOfAccount() {
		return account.size();
	}

	// ��ȸ�ϰ� ���� ���� ��ȣ�� �Է¹޾� �ش� ���¿� ��� �ִ� �ݾ��� ��ȯ
	public int getDeposit(String account) {
		return deposit.get(account);
	}

	// �ܺηκ��� ���� ������ �޾� deposit hashtable�� �ش� ���¿� �ݾ��� �Է�
	public void setDeposit(String account, int money) {
		deposit.put(account, money);
	}
}
