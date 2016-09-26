package com.hs.eai.orderoverview.utils;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUtils {

	private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

	private static AppUtils appUtils = new AppUtils( );
	
	private AppUtils() {
	}

	/* Static 'instance' method */
	public static AppUtils getInstance() {
		return appUtils;
	}


	public static Integer StringToInteger(String number) {

		Integer numberId = null;

		try {
			numberId = Integer.parseInt(number);
		} catch (NumberFormatException ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		return numberId;
	}

	@SuppressWarnings("finally")
	public static java.sql.Date getDateFromString(String date) {

		java.util.Date today;
		java.sql.Date rv = null;
		try {

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			today = format.parse(date);
			rv = new java.sql.Date(today.getTime());

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		} finally {
			return rv;
		}

	}
	

}
