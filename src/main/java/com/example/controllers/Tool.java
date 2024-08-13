package com.example.controllers;

import com.example.utils.Enums.*;

public class Tool {
  private ToolCode toolCode;
  private ToolType toolType;
  private ToolBrand brand;
  private double dailyCharge;
  private boolean isWeekdayCharge;
  private boolean isWeekendCharge;
  private boolean isHolidayCharge;

  public Tool(ToolCode toolCode, ToolType toolType, ToolBrand brand, double dailyCharge,
      boolean weekdayCharge,
      boolean weekendCharge, boolean holidayCharge) {
    this.toolCode = toolCode;
    this.toolType = toolType;
    this.brand = brand;
    this.dailyCharge = dailyCharge;
    this.isWeekdayCharge = weekdayCharge;
    this.isWeekendCharge = weekendCharge;
    this.isHolidayCharge = holidayCharge;
  }

  public ToolCode getToolCode() {
    return toolCode;
  }

  public ToolType getToolType() {
    return toolType;
  }

  public ToolBrand getBrand() {
    return brand;
  }

  public double getDailyCharge() {
    return dailyCharge;
  }

  public boolean isWeekdayCharge() {
    return isWeekdayCharge;
  }

  public boolean isWeekendCharge() {
    return isWeekendCharge;
  }

  public boolean isHolidayCharge() {
    return isHolidayCharge;
  }
}
