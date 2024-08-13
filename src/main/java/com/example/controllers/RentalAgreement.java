package com.example.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import com.example.utils.Utils;
import com.example.utils.Enums.*;

public class RentalAgreement {
  private Tool tool;
  private int rentalDays;
  private LocalDate checkoutDate;
  private LocalDate dueDate;
  private double dailyCharge;
  private int chargeDays;
  private double preDiscountCharge;
  private int discountPercent;
  private double discountAmount;

  public RentalAgreement(ToolType toolType, int rentalDays, LocalDate checkoutDate, int discountPercent) {
    Utils utils = new Utils();

    if (rentalDays < 1) {
      throw new IllegalArgumentException("Rental days must be 1 or greater.");
    }

    if (discountPercent > 100) {
      throw new IllegalArgumentException("Discount percent must be greater than 100%.");
    }

    this.tool = utils.getToolByType(toolType);
    this.rentalDays = rentalDays;
    this.checkoutDate = checkoutDate;
    this.discountPercent = discountPercent;

    this.dueDate = checkoutDate.plusDays(rentalDays + 1);
    this.dailyCharge = tool.getDailyCharge();
    this.chargeDays = calculateChargeDays();
    this.preDiscountCharge = dailyCharge * chargeDays;
    this.discountAmount = preDiscountCharge * discountPercent / 100.0;
  }

  // Calculate the charge days
  private int calculateChargeDays() {
    int chargeDays = 0;

    for (int i = 1; i <= rentalDays + 1; i++) {
      LocalDate date = checkoutDate.plusDays(i);
      if (isChargeable(date)) {
        chargeDays++;
      }
    }

    return chargeDays;
  }

  private boolean isChargeable(LocalDate date) {
    if (tool.isWeekdayCharge() && isWeekday(date) && !isHoliday(date)) {
      return true;
    } else if (tool.isWeekendCharge() && isWeekend(date) && !isHoliday(date)) {
      return true;
    } else if (tool.isHolidayCharge() && isHoliday(date) && !isWeekend(date)) {
      return true;
    } else {
      return false;
    }
  }

  // Check if a date is a weekday
  private boolean isWeekday(LocalDate date) {
    return date.getDayOfWeek().getValue() < 6;
  }

  // Check if a date is a weekend
  private boolean isWeekend(LocalDate date) {
    DayOfWeek day = date.getDayOfWeek();
    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
  }

  // Check if a date is a holiday
  private boolean isHoliday(LocalDate date) {
    int month = date.getMonthValue();
    int dayOfMonth = date.getDayOfMonth();
    DayOfWeek dayOfWeek = date.getDayOfWeek();

    // Independence Day: July 4th, observed if it falls on a weekend
    if (month == Month.JULY.getValue()) {
      if (dayOfMonth == 3 && dayOfWeek == DayOfWeek.FRIDAY)
        return true; // Observed on Friday if July 4th is Saturday
      if (dayOfMonth == 5 && dayOfWeek == DayOfWeek.MONDAY)
        return true; // Observed on Monday if July 4th is Sunday
      if (dayOfMonth == 4)
        return true;
    }

    // Labor Day: First Monday in September
    if (month == Month.SEPTEMBER.getValue() && dayOfWeek == DayOfWeek.MONDAY && dayOfMonth <= 7) {
      return true;
    }

    return false;
  }

  public double roundValue(double value) {
    return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  // Get the final charge value
  public double getFinalCharge() {
    return roundValue(preDiscountCharge - discountAmount);
  }

  // Print rental agreement info
  public void printRentalAgreement() {
    System.out.println("Tool code: " + tool.getToolCode());
    System.out.println("Tool type: " + tool.getToolType());
    System.out.println("Tool brand: " + tool.getBrand());
    System.out.println("Rental days: " + rentalDays);
    System.out.println("Check out date: " + checkoutDate);
    System.out.println("Due date: " + dueDate);
    System.out.println("Daily rental charge: $" + dailyCharge);
    System.out.println("Charge days: " + chargeDays);
    System.out.println("Pre-discount charge: $" + roundValue(preDiscountCharge));
    System.out.println("Discount percent: " + discountPercent + "%");
    System.out.println("Discount amount: $" + roundValue(discountAmount));
    System.out.println("Final charge: $" + getFinalCharge());
  }
}
