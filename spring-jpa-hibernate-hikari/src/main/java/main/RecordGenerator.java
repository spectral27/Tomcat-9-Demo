package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RecordGenerator {

	public String generateContent() throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/words.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String words = null;

		words = bufferedReader.readLine();
		bufferedReader.close();

		String[] wordsArray = words.split(" ");

		StringBuilder sb = new StringBuilder();
		sb.append(wordsArray[new Random().nextInt(wordsArray.length)]);
		sb.append(wordsArray[new Random().nextInt(wordsArray.length)]);

		return sb.toString();
	}
	
	public LocalDateTime generateLocalDateTime() {
		Random r = new Random();

		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = r.nextInt(today.getMonth().getValue()) + 1;
		int dayOfMonth;
		boolean isToday = false;

		if (month == today.getMonth().getValue()) {
			dayOfMonth = r.nextInt(LocalDate.now().getDayOfMonth()) + 1;
			if (dayOfMonth == today.getDayOfMonth()) {
				isToday = true;
			}
		} else {
			switch (month) {
			case 2:
				dayOfMonth = r.nextInt(28) + 1;
				break;
			case 4:
				dayOfMonth = r.nextInt(30) + 1;
				break;
			case 6:
				dayOfMonth = r.nextInt(30) + 1;
				break;
			case 9:
				dayOfMonth = r.nextInt(30) + 1;
				break;
			case 11:
				dayOfMonth = r.nextInt(30) + 1;
				break;
			default:
				dayOfMonth = r.nextInt(31) + 1;
			}
		}

		int hour;
		int minute;

		if (isToday) {
			hour = r.nextInt(LocalTime.now().getHour() + 1);
			minute = r.nextInt(LocalTime.now().getMinute() + 1);
		} else {
			hour = r.nextInt(24);
			minute = r.nextInt(60);
		}

		return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
	}

}
