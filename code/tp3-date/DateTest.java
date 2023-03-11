package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.Date.isLeapYear;
import static fr.istic.vv.Date.isValidDate;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    private Date date;

    @BeforeEach
    protected void setUp() throws Exception {
        //happyPath
        date = new Date(8, 03, 2023);

    }

    @Test
    public void isValidDateHappyPath() {
        assertTrue(isValidDate(8, 03, 2023), "Common date shoud be valid");
    }

    @Test
    public void isValidDateChangeBlockDAy() {
        assertFalse(isValidDate(-3, 03, 2023), "Day should not be negative");
    }

    @Test
    public void isValidDateChangeBlockMonth() {
        assertFalse(isValidDate(3, 0, 2023),"Month should not be 0");
    }

    @Test
    public void isValidDateChangeBlockYear() {
        assertFalse(isValidDate(3, 03, 20), "Year should not be inferior to 1980");
    }

    @Test
    public void isValidDateChangeBlockLeap() {
        assertFalse(isValidDate(29, 2, 2019), "February in common years should not have more than 28 days.");
    }


    @Test
    public void isLeapYearTest1() {
        int annee = 2020;
        assertTrue(isLeapYear(annee), "2020 should be recognized as a leap year");
    }

    @Test
    public void isLeapYearTest2() {
        int annee2 = 2021;
        assertFalse(isLeapYear(annee2),"2021 should not be recognized as a leap year");
    }

    @Test
    public void isLeapYearLogicCoverage() {
        int annee3 = 2000;
        assertTrue(isLeapYear(annee3),"2000 should be recognized as a leap year");
    }

    @Test
    public void isLeapYearLogicCoverage2() {
        int annee4 = 1992;
        assertTrue(isLeapYear(annee4),"1992 should be recognized as a leap year");
    }

    @Test
    public void nextDateBasicChoice() {
        Date expectedDate = new Date(9, 03, 2023);
        assertEquals(expectedDate,date.nextDate(date),"9 mars 2023 doit être le jour après 8 mars 2023.");
    }

    @Test
    public void nextDateLeapDAy() {
        Date expectedDate = new Date(1, 03, 2020);
        Date leapdate = new Date(29, 02, 2020);
        Date testDate = leapdate.nextDate(leapdate);
        assertEquals(expectedDate,testDate,"1 mars 2020 doit être le jour après 29 février 2020.");
    }

    @Test
    public void nextDateEndOfMonth() {
        Date expectedDate = new Date(1, 10, 2022);
        Date test = new Date(30, 9, 2022);
        assertEquals(expectedDate,test.nextDate(test),"1 octobre 2022 doit être le jour après 30 septembre 2022.");
    }

    @Test
    public void nextDateEndOfYear() {
        Date expectedDate = new Date(1, 1, 2024);
        Date test = new Date(31, 12, 2023);
        assertEquals(expectedDate,test.nextDate(test),"1 janvier 2024 doit être le jour après 31 décembre 2023.");
    }

    @Test
    public void previousDateBasicChoice() {
        Date expectedDate2 = new Date(7,03,2023);
        assertEquals(expectedDate2,date.previousDate(), "7 mars 2023 doit être le jour précédant 8 mars 2023.");
    }

    @Test
    public void previousDateLeapYear() {
        Date test = new Date(1, 03, 2020);
        Date expectedDate2 = new Date(29,02,2020);
        assertEquals(expectedDate2,test.previousDate(),"28 fev 2020 doit être le jour précédant 1 mars 2020." );
    }

    @Test
    public void previousDateBeginningOfYear() {
        Date test = new Date(1, 1, 2023);
        Date expectedDate2 = new Date(31,12,2022);
        assertEquals(expectedDate2,test.previousDate(),"31 decembre 2022 doit être le jour précédant 1er janvier 2023.");
    }

    @Test
    public void previousBeginningOfMonth() {
        Date test = new Date(1, 9, 2022);
        Date expectedDate2 = new Date(31,8,2022);
        assertEquals(expectedDate2,test.previousDate(),"31 aout 2022 doit être le jour précédant 1er septembre 2022.");
    }

    @Test
    public void compareToTest() {
    Date other = new Date(14,10,2021);
        assertTrue(date.compareTo(other)>0);
    }

    @Test
    public void compareToTest2() {
        Date other = new Date(14,10,2025);
        assertTrue(date.compareTo(other)<0);
    }

}


