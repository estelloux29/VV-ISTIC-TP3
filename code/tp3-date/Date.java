package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    private static final int[] daysInMonth = {
            0,  // dummy value for 0th index
            31, // January
            28, // February (non-leap year)
            31, // March
            30, // April
            31, // May
            30, // June
            31, // July
            31, // August
            30, // September
            31, // October
            30, // November
            31  // December
    };

    // counts() méthode qui donne le nb de jours à partir du mois et de l'année
    // leap()  predicate

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static int countDays(int month, int year) {
        int nbdays;
        if (month == 2) {
            if (isLeapYear(year)) {
                nbdays = 29;
            } else nbdays = 28;
        } else if ((month == 1) | (month == 3) | (month == 5) | (month == 7) | (month == 8) | (month == 10) | (month == 12)) {
            nbdays = 31;
        } else nbdays = 30;
        return nbdays;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1980 || month < 1 || month > 12 || day < 1) {
            return false; // year, month or day out of range
        }
        int maxDay = 31; // assume maximum number of days
        switch (month) { // adjust maximum number of days for some months
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            case 2:
                if (isLeapYear(year)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
                break;
        }
        return day <= maxDay;
    }


    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) | (year % 400 == 0));
    }

//

    public Date nextDate(Date date) {
        int day = this.day;
        int month = this.month;
        int year = this.year;
        Date res;

        if (day < date.countDays(month, year)) {
            res = new Date(day + 1, month, year);
        } else {
            int daysInMonth = date.countDays(month, year);
            int newDay = day - daysInMonth + 1;
            int newMonth = (month % 12) + 1;
            int newYear = (month == 12) ? year + 1 : year;
            res = new Date(newDay, newMonth, newYear);
        }
        return res;
    }




    public Date previousDate() {
        int newDay = day;
        int newMonth = month;
        int newYear = year;

        // handle beginning of year
        if (newMonth == 1 && newDay == 1) {
            newDay = 31;
            newMonth = 12;
            newYear--;
        } else if (newDay == 1) {
            newMonth--;
            newDay = daysInMonth[newMonth];

            // handle February in leap years
            if (newMonth == 2 && isLeapYear(newYear)) {
                newDay++;
            }
        } else {
            newDay--;
        }

        return new Date(newDay, newMonth, newYear);
    }

    @Override
    public int compareTo(Date other) {
        if (other == null) {
            throw new NullPointerException();
        }

        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}