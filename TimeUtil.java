package com.mq.scripts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimeUtil extends BaseTestScript{
	public static void main(String args[])
	{
	    TimeUtil testScript = new TimeUtil();
	    Calendar currentTimeNow = Calendar.getInstance();
	    String formattedTime = testScript.getFormattedDateTime(currentTimeNow, "MM/dd/yy hh:mm aa");
	    System.out.println(
	            "Current Time in AM/PM Format is : " + formattedTime.toLowerCase().replace("pm", "p.m.").replace("am", "a.m."));
	    currentTimeNow.add(Calendar.MINUTE, 1);
	    formattedTime = testScript.getFormattedDateTime(currentTimeNow, "MM/dd/yy hh:mm aa");
	    System.out.println(
	            "Time after adding 1 minute in AM/PM Format is : " + formattedTime.toLowerCase().replace("pm", "p.m.").replace("am", "a.m."));
	    
	}
	
	public String getFormattedDateTime(Calendar cal, String Format)
	{
		Date date = cal.getTime();
		SimpleDateFormat formatTime = new SimpleDateFormat(Format);
	    return formatTime.format(date);
	}
	
	public WebElement getEmailSubject(Calendar cal)
	{
		String TimeStamp = getFormattedDateTime(cal, "MM/dd/yy hh:mm aa").toLowerCase().replace("pm", "p.m.").replace("am", "a.m.");
		String hours = TimeStamp.substring(9,10);
		if(hours.equals("0"))
			TimeStamp = TimeStamp.replace(TimeStamp.substring(9,11)+":", TimeStamp.substring(10,11)+":");
		List<WebElement> emailSubjectList= driver.findElements(By.xpath("//td[text()='"+TimeStamp+"']/parent::tr/td[2]"));
		if(emailSubjectList.size()==1)
		{
			System.out.println("Found 1 Email Subject with Time Stamp: " + TimeStamp);
		}
		else if(emailSubjectList.size()==0)
		{
			System.out.println("Email Subject Not Found with Time Stamp: " + TimeStamp);
			System.out.println("Trying by adding 1 minute to Time Stamp: " + TimeStamp);
			cal.add(Calendar.MINUTE, 1);
			TimeStamp = getFormattedDateTime(cal, "MM/dd/yy hh:mm aa").toLowerCase().replace("pm", "p.m.").replace("am", "a.m.");
			hours = TimeStamp.substring(9,10);
			if(hours.equals("0"))
				TimeStamp = TimeStamp.replace(TimeStamp.substring(9,11)+":", TimeStamp.substring(10,11)+":");
			emailSubjectList= driver.findElements(By.xpath("//td[text()='"+TimeStamp+"']/parent::tr/td[2]"));
			if(emailSubjectList.size()==1)
			{
				System.out.println("Found 1 Email Subject after adding 1 minute: " + TimeStamp);
			}
			else if(emailSubjectList.size()==0)
			{
				System.out.println("Email Subject Not Found Even after adding 1 minute: " + TimeStamp);
			}
		}
		if(emailSubjectList.size()>1)
		{
			System.out.println("Found mutiple Emails with TimeStamp :" + TimeStamp +"Hence returning Top Email");
		}
		return emailSubjectList.get(0);
	}
}
