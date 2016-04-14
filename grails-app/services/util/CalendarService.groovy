package util

import grails.transaction.Transactional
import util.TimeZoneService

@Transactional
class CalendarService {
    TimeZoneService timeZoneService

    Calendar newCalendarUTCInstance() {
        Calendar.getInstance(timeZoneService.utc(), Locale.ENGLISH)
    }

    Calendar newCalendarUTCInstance(Date date) {
        Calendar calendar = newCalendarUTCInstance()
        calendar.setTime(date)
        return calendar
    }

    Date daysAgo(int days) {
        Calendar calendar = newCalendarUTCInstance()
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return calendar.getTime()
    }

    /**
     * Result is always positive
     * @param lowerDate
     * should be lower bound date
     * @param upperDate
     * should be upper bound date
     * @return
     */
    int getYearDiff(Date lowerDate, Date upperDate) {
        if (lowerDate.after(upperDate)) {
            return getYearDiff(upperDate, lowerDate)
        } else {
            Calendar dateCalendar = newCalendarUTCInstance()
            dateCalendar.setTime(lowerDate)

            Calendar otherDateCalendar = newCalendarUTCInstance()
            otherDateCalendar.setTime(upperDate)

            int yearDiff = otherDateCalendar.get(Calendar.YEAR) - dateCalendar.get(Calendar.YEAR);
            if (dateCalendar.get(Calendar.DAY_OF_YEAR) > otherDateCalendar.get(Calendar.DAY_OF_YEAR)) {
                yearDiff--;
            }
            return yearDiff
        }
    }

    int getDayDiff(Date lowerDate, Date upperDate) {
        Calendar lowerDateCalendar = newCalendarUTCInstance()
        lowerDateCalendar.setTime(lowerDate)
        lowerDateCalendar.clear(Calendar.HOUR)
        lowerDateCalendar.clear(Calendar.MINUTE)
        lowerDateCalendar.clear(Calendar.SECOND)
        lowerDateCalendar.clear(Calendar.MILLISECOND)

        Calendar upperDateCalendar = newCalendarUTCInstance()
        upperDateCalendar.setTime(upperDate)
        upperDateCalendar.clear(Calendar.HOUR)
        upperDateCalendar.clear(Calendar.MINUTE)
        upperDateCalendar.clear(Calendar.SECOND)
        upperDateCalendar.clear(Calendar.MILLISECOND)

        long diffMilliseconds = Math.abs(upperDateCalendar.getTimeInMillis() - lowerDateCalendar.getTimeInMillis())
        int dayDiff = Math.round(diffMilliseconds / 86400000l)
        return dayDiff
    }

    Date addYear(Date date, int year) {
        return add(date, year, 0, 0)
    }

    Date addMonth(Date date, int month) {
        return add(date, 0, month, 0)
    }

    Date addDays(Date date, int days) {
        return add(date, 0, 0, days)
    }

    Date add(Date date, int year, int month, int days) {
        def calendar = newCalendarUTCInstance(date)
        if (year != 0) calendar.add(Calendar.YEAR, year)
        if (month != 0) calendar.add(Calendar.MONTH, month)
        if (days != 0) calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar.getTime()
    }

    Date shiftFromNow(int calendarField, int amount) {
        return shift(new Date(), calendarField, amount)
    }

    Date shift(Date date, int calendarField, int amount) {
        Calendar calendar = newCalendarUTCInstance(date)
        calendar.clearTime()
        calendar.add(calendarField, amount)
        return calendar.getTime()
    }
}
