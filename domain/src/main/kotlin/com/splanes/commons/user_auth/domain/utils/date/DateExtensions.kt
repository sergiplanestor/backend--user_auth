package com.splanes.commons.user_auth.domain.utils.date

import java.util.*
import java.util.concurrent.TimeUnit

object Time {

	val Millis: Unit = Unit.MILLIS
	val Seconds: Unit = Unit.SECONDS
	val Minutes: Unit = Unit.MINUTES
	val Hours: Unit = Unit.HOURS
	val Days: Unit = Unit.DAYS

	val Until: GapCheck = GapCheck.UNTIL
	val Since: GapCheck = GapCheck.SINCE
	val Absolute: GapCheck = GapCheck.ABSOLUTE

	enum class Unit(val converter: TimeUnit) {
		MILLIS(converter = TimeUnit.MILLISECONDS),
		SECONDS(converter = TimeUnit.SECONDS),
		MINUTES(converter = TimeUnit.MINUTES),
		HOURS(converter = TimeUnit.HOURS),
		DAYS(converter = TimeUnit.DAYS);
		companion object
	}

	enum class GapCheck {
		UNTIL,
		SINCE,
		ABSOLUTE
	}

	data class Model(val value: Long, val unit: Unit)
}



fun Time.millis(millis: Long): Time.Model = Time.Model(millis, Millis)
fun Time.seconds(seconds: Long): Time.Model = Time.Model(seconds, Seconds)
fun Time.minutes(minutes: Long): Time.Model = Time.Model(minutes, Minutes)
fun Time.hours(hours: Long): Time.Model = Time.Model(hours, Hours)
fun Time.days(days: Long): Time.Model = Time.Model(days, Days)

fun Time.now(unit: Time.Unit = Time.Millis): Long = Millis.map(System.currentTimeMillis(), unit)

fun Time.Unit.toTimeModel(value: Long): Time.Model = Time.Model(value, this)
fun Time.Unit.map(value: Long, target: Time.Unit): Long = converter.convert(value, target.converter)

fun Time.Model.withUnit(unit: Time.Unit): Time.Model = Time.Model(value = this.unit.map(value, unit), unit = unit)
fun Time.Model.toDate(): Date = Date(unit.map(value, Time.Millis))

fun Date.toTimeModel(): Time.Model = Time.millis(time)
fun Date.gap(other: Date, check: Time.GapCheck):
fun Date.gapSince(other: Date, isAbsolute: Boolean = false)

infix fun Date.after(other: Date): Boolean = after(other)
infix fun Date.before(other: Date): Boolean = before(other)

fun Date.timeGapUntil(other: Date, unit: Time.Unit): Long = -timeGap(other, unit)
fun Date.timeGapSince(other: Date, unit: Time.Unit): Long = timeGap(other, unit)
fun Date.timeGap(other: Date, unit: Time.Unit): Long = Time.Millis from (time - other.time) to unit

operator fun Date.compareTo(other: Any?): Int = when {
	other == null || other !is Date -> error("")
	this === other || this.time == other.time -> 0
	this.after(other) -> -1
	this.before(other) -> 1
	else -> error("")
}

