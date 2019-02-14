package lp1;

public class MyDate {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		MyDate d1 = new MyDate(2015, 2, 28);
		System.out.println(d1);             // Saturday 28 Feb 2015
		System.out.println(d1.nextDay() + " Next day");   // Sunday 1 Mar 2015
		System.out.println(d1.nextDay() + " Next Day");     // Monday 2 Mar 2015
		System.out.println(d1.nextMonth() + " Next Month"); // Thursday 2 Apr 2015
		System.out.println(d1.nextYear() + " Next Year");  // Saturday 2 Apr 2016

		System.out.println("");
		System.out.println("Next Date");
		MyDate d2 = new MyDate(2016, 1, 2);
		System.out.println(d2);                 // Saturday 2 Jan 2016
		System.out.println(d2.previousDay() + " Prev Day");   // Friday 1 Jan 2016
		System.out.println(d2.previousDay() + " Prev Day");   // Wednesday 30 Dec 2015
		System.out.println(d2.previousMonth() + " Prev Month"); // Thursday 30 Jan 2014
		System.out.println(d2.previousYear() + " Prev Year");  // Wednesday 30 Jan 2013

		System.out.println("");
		System.out.println("Next Date");
		MyDate d3 = new MyDate(2016, 2, 29);
		System.out.println(d3);
		System.out.println(d3.previousYear() + " Prev year");  // Saturday 28 Feb 2015

		MyDate d4 = new MyDate(2099, 11, 31); // Invalid year, month, or day!
		MyDate d5 = new MyDate(2017, 2, 29);  // Invalid year, month, or day!
		
		//Next Day method loop
		MyDate d6 = new MyDate(2015, 12, 28);
		MyDate d7 = new MyDate(2016, 3, 2);
		
		
		while(!(d6.equals(d7)))
		{
			System.out.println(d6.nextDay());
		}

	}
	
	private int year;
	private int month;
	private int day;
	
	
	private static String[] strMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private static String[] strDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private static int[] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	//Override equals method
	boolean equals(MyDate date)
	{
		if(this.year == date.year && this.month == date.month && this.day == date.day)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// static methods
	public static boolean isLeapYear(int year)
	{
		double check;
		check = year%4;
		double check2 = year % 100;
		
		if(check == 0 && check2 != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getDayOfWeek(int year, int month, int day)
	{
		int specialMonth;
		int specialYear;
		
		specialYear = year;
		if(month == 1 || month == 2)
		{
			specialYear = year - 1;
		}
		specialMonth = month - 2;
		if(specialMonth <= 0)
		{
			specialMonth = specialMonth + 12;
		}
		
		
		int century = year / 100;
		String shortYear = Integer.toString(specialYear);
		String shortYear2 = shortYear.substring(shortYear.length() -2);
		int shortYearInt = Integer.parseInt(shortYear2);
		double dayOfWeek = (day + Math.floor((2.6 * specialMonth) - 0.2) + shortYearInt + Math.floor(shortYearInt/4) + Math.floor(century/4) - (2 * century))% 7;
		int dayInt = (int)dayOfWeek;
		
		String dayStr = "Error getting Day of week";
		if(dayInt < 0)
		{
			dayInt = dayInt + 7;
		}
		for(int i = 0; i <= strDays.length; i++)
		{
			if(dayInt == i)
			{
				dayStr = strDays[i];
				break;
			}
		}
		return dayStr;
	}
	
	//Constructors
	MyDate(int year, int month, int day)
	{
		try {
			setDate(year, month, day);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//public methods
	public boolean isValidDate(int year, int month, int day)
	{
		boolean valid = false;
		
		if((year >=1 && year <= 9999) && (month >= 1 && month <= 12) && (day >= 1 && day <= 31))
		{
			int monthDayCount = daysInMonths[month - 1];
			
			if(day <= monthDayCount)
			{
				valid = true;
			}
			else if (month == 2 && isLeapYear(year))
			{
				if(day <= 29)
				{
					this.day = day;
					valid = true;
				}
			}
			
		}
		
		return valid;
	}
	
	public void setDate(int year, int month, int day)
	{
		boolean valid = isValidDate(year, month, day);
		
		if(!valid)
		{
			throw new IllegalArgumentException("Ivalid year, Month, or Date!");
		}
		else
		{
			this.day = day;
			this.month = month;
			this.year = year;
		}
		
	}

	public void setYear(int year)
	{
		if(year >= 1 && year <= 9999)
		{
			this.year = year;
		}
		else
		{
			throw new IllegalArgumentException("Out of range");
		}

	}
	
	public void setMonth(int month) throws Exception
	{
		if(month >= 1 && month <= 12)
		{
			this.month = month;
		}		
		else
		{
			throw new IllegalArgumentException("Out of range");
		}

	}
	
	public void setDay(int day) throws Exception
	{
		int MaxDays = daysInMonths[this.month];
		if(isLeapYear(this.year) && month == 2)
		{
			MaxDays = 29;
		}
		
		if(day <= MaxDays && day >= 1)
		{
			this.day = day;
		}
		
		else
		{
			throw new IllegalArgumentException("Out of range");
		}

	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public int getMonth()
	{
		return this.month;
	}

	public int getDay()
	{
		return this.day;
	}
	
	public String toString()
	{	
		String Date;
		
		Date = getDayOfWeek(this.year, this.month, this.day) + " " + this.day + " " + strMonths[this.month-1] + " " + this.year;
		return Date;
	}
	
	public MyDate nextDay()
	{
		
		int maxDay = daysInMonths[this.month - 1];
		if(this.month == 2 && isLeapYear(this.year))
		{
			maxDay = 29;
		}
		if(this.month == 12 && this.day == 31)
		{
			this.month = 1;
			this.day = 1;
			
			this.year++;
		}

		else if(this.day == maxDay)
		{
			this.day = 1;
			
			this.month++;
		}
		else
		{
			this.day++;
		}
		
		return this;
	}
	
	public MyDate nextMonth()
	{
		boolean set = false;
		if(this.month == 12)
		{		
			if(this.day == daysInMonths[this.month - 1])
			{
				if(this.day > daysInMonths[this.month])
				{
					this.day = daysInMonths[this.month];
				}	
			}
			
			this.month = 1;
			this.year++;
			set = true; 
			
		}
		
		if(this.day == daysInMonths[this.month - 1] && set == false)
		{
			if(this.day > daysInMonths[this.month])
			{
				this.day = daysInMonths[this.month];
			}
			this.month++;
			set = true;
		}
		
		if(set == false)
		{
			this.month++;
		}
		
		return this;
		
	}
	
	public MyDate nextYear()
	{
		if(this.year <= 9998)
		{
			this.year++;
		}
		
		return this;
	}
	
	public MyDate previousDay()
	{
		if(this.day == 1)
		{
			if(this.month == 1)
			{
				this.month = 12;
				this.year--;
				
			}
			else
			{
				this.month--;
			}
			this.day = daysInMonths[this.month -2];
		}
		else
		{
			this.day--;
		}
		
		return this;
	}
	
	public MyDate previousMonth()
	{
		if(this.day > daysInMonths[this.month - 2])
		{
			if(this.month == 1)
			{
				this.month = 12;
				this.day = daysInMonths[this.month - 2];
				this.year--;
			}
		}
		else if (this.month == 12)
		{
			this.month = 1;
			this.year--;
		}
		else
		{
			this.month--;
		}
		
		return this;
	}
	
	public MyDate previousYear()
	{
		this.year--;
		if(this.month == 2 && this.day == 29)
		{
			this.day = 28;
		}
		return this;
	}
	
	
	
}
