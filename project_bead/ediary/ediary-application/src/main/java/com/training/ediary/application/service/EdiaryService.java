package com.training.ediary.application.service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.ediary.domain.Mark;
import com.training.ediary.domain.SchoolYear;

@Service
public class EdiaryService {
	
	public boolean isCurrentSemester(SchoolYear schoolYear)
	{
		int currentYear = getCurrentYear();
		int currentMonth = getCurrentMonth();
		if((currentMonth <13 && currentMonth > 8 && currentYear == schoolYear.getStartSchoolYear()) || 
				(currentMonth >0 && currentMonth < 7 && currentYear == schoolYear.getEndSchoolYear()))
		{
			return true;
		}
		return false;
	}
	
	public int getCurrentMonth() {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}
	
	public int getCurrentYear() {
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
	public boolean outDate(LocalDateTime date, int dayNumber) {
		
		int dayMax = dayNumber * 86400000;
		long currentTime = System.currentTimeMillis();
		long timeDif = Math.abs(currentTime - date.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
		if(timeDif > dayMax) 
		{
			return false;
		}
		return true;
	}
	
	public Time convertTime(Date date) {
		Time time = new Time(date.getTime());
		return time;
	}
}
