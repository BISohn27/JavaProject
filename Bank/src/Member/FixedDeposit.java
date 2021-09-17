package Member;

import java.util.Calendar;

public class FixedDeposit {
	private float rate;
	private int years;
	private int deposit;
	private int total;
	private String startDate;
	private String endDate;
	
	public FixedDeposit(float rate,int years, int money) {
		Calendar Date = Calendar.getInstance();
		startDate = Date.get(Calendar.YEAR) + "년 " + (Date.get(Calendar.MONTH)+1) + "월 " + Date.get(Calendar.DAY_OF_MONTH) + "일";
		
		Date.add(Calendar.MONTH,12*years);
		endDate = Date.get(Calendar.YEAR) + "년 " + (Date.get(Calendar.MONTH)+1) + "월 " + Date.get(Calendar.DAY_OF_MONTH) + "일";
		
		this.rate = rate;
		this.years = years;
		this.deposit = money;
		this.total = (int)(money*(1+years*rate/100));
	}
	
	public FixedDeposit(float rate, int years, String startDate, String endDate,int deposit, int total) {
		this.rate = rate;
		this.years = years;
		this.deposit = deposit;
		this.total = total;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int returnDeposit() {
		Calendar cal = Calendar.getInstance();
		String now = cal.get(Calendar.YEAR) + "년 " + (cal.get(Calendar.MONTH)+1) + "월 " + cal.get(Calendar.DAY_OF_MONTH) + "일";
		if(endDate.equals(now))
			return total;
		else
			return 0;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}

	public double getRate() {
		return rate;
	}

	public int getYears() {
		return years;
	}

	public int getDeposit() {
		return deposit;
	}
	
	public int getTotal() {
		return total;
	}
}
