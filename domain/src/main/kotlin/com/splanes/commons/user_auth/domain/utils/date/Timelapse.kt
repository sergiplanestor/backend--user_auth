package com.splanes.commons.user_auth.domain.utils.date

import java.util.concurrent.TimeUnit

class Timelapse private constructor(private val millis: Long) {
	
	companion object {
		fun millis(amount: Long): Timelapse = Timelapse(millis = amount)
		fun seconds(amount: Long): Timelapse = Timelapse(millis = amount * TimeUnit.MILLISECONDS.convert())
		fun minutes(amount: Long): Timelapse = Timelapse(millis = amount)
		fun hours(amount: Long): Timelapse = Timelapse(millis = amount)
		fun days(amount: Long): Timelapse = Timelapse(millis = amount)
		fun months(amount: Long): Timelapse = Timelapse(millis = amount)
		fun years(amount: Long): Timelapse = Timelapse(millis = amount)
	}
}