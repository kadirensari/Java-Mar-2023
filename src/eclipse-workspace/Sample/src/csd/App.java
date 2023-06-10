/*----------------------------------------------------------------------------------------------------------------------	 
	Sınıf Çalışması: Parametresi ile aldığı int türden gün, ay ve yıl bilgilerine ilişkin tarihin haftanın hangi gününe 
	geldiği bilgisini döndüren getDayOfWeek isimli metodu DateUtil sınıfı içerisinde yazınız ve test ediniz
	Açıklamalar:
		- Metot geçersiz bir tarih için -1 değerine geri dönecektir
		- Haftanın hangi gününe geldiği bilgisi şu şekilde hesaplanabilir:
		01.01.1900 tarihi ile ilgili tarih arasındaki toplam gün sayısının 7'ye modu alındığında elde edilen değer için
		0(sıfır) Pazar, 1 Pazartesi, ..., 6 Cumartesi anlamındadır
		- İleride daha iyisi yazılacaktır
-----------------------------------------------------------------------------------------------------------------------*/
package csd;

class App {
	public static void main(String [] args)
	{
		GetDayOfYearTest.run();
	}
}

class GetDayOfYearTest {
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);
		
		while (true) {
			System.out.print("Gün ay ve yıl bilgilerini giriniz:");
			int day = kb.nextInt();
			int month = kb.nextInt();
			int year = kb.nextInt();
			int dayOfYear = DateUtil.getDayOfYear(day, month, year);
			
			if (dayOfYear != -1)
				System.out.printf("%02d/%02d/%04d tarihi yılın %d. günüdür%n", day, month, year, dayOfYear);
			else
				System.out.println("Geçersiz tarih!...");
			
			if (day == 0 && month == 0 && year == 0)
				break;
		}
		
		System.out.println("Tekrar yapıyor musunuz?");
	}
}

class DateUtil {
	public static int getDayOfYear(int day, int month, int year)
	{
		if (!isValidDate(day, month, year))
			return -1;
		
		int dayOfYear = day;
		
		switch (month - 1) {
		case 11:
			dayOfYear += 30;
		case 10:
			dayOfYear += 31;
		case 9:
			dayOfYear += 30;
		case 8:
			dayOfYear += 31;
		case 7:
			dayOfYear += 31;
		case 6:
			dayOfYear += 30;
		case 5:
			dayOfYear += 31;
		case 4:
			dayOfYear += 30;
		case 3:
			dayOfYear += 31;
		case 2:
			dayOfYear += 28;
			if (isLeapYear(year))
				++dayOfYear;
		case 1:
			dayOfYear += 31;
		}
		
		return dayOfYear;
	}
	
	public static boolean isValidDate(int day, int month, int year)
	{
		return (1 <= day && day <= 31) && (1 <= month && month <= 12)
				&& (year >= 1900) && (day <= getDays(month, year));
	}
	
	public static int getDays(int month, int year)
	{
		int days = 31;
		
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			days = 28;
			if (isLeapYear(year))
				++days;
		}
		
		return days;
	}

	
	public static boolean isLeapYear(int year)
	{
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
}
