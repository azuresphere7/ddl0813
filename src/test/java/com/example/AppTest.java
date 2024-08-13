package com.example;

import junit.framework.TestCase;
import java.time.LocalDate;

import com.example.controllers.RentalAgreement;
import com.example.utils.Enums.*;

public class AppTest extends TestCase {
  public void testToolFinalCharge_Test1() {
    try {
      @SuppressWarnings("unused")
      RentalAgreement rentalAgreement1 = new RentalAgreement(ToolType.JACKHAMMER, 5, LocalDate.of(2015, 9, 3),
          101);
      fail("Expected IllegalArgumentException for 100% discount");
    } catch (IllegalArgumentException e) {
      assertEquals("Discount percent must be greater than 100%.", e.getMessage());
    }
  }

  public void testToolFinalCharge_Test2() {
    RentalAgreement rentalAgreement2 = new RentalAgreement(ToolType.LADDER, 3, LocalDate.of(2020, 7, 2), 10);
    assertEquals(3.58, rentalAgreement2.getFinalCharge());
  }

  public void testToolFinalCharge_Test3() {
    RentalAgreement rentalAgreement3 = new RentalAgreement(ToolType.CHAINSAW, 5, LocalDate.of(2015, 7, 2), 25);
    assertEquals(4.47, rentalAgreement3.getFinalCharge());
  }

  public void testToolFinalCharge_Test4() {
    RentalAgreement rentalAgreement4 = new RentalAgreement(ToolType.JACKHAMMER, 6, LocalDate.of(2015, 9, 3), 0);
    assertEquals(11.96, rentalAgreement4.getFinalCharge());
  }

  public void testToolFinalCharge_Test5() {
    RentalAgreement rentalAgreement5 = new RentalAgreement(ToolType.JACKHAMMER, 9, LocalDate.of(2015, 7, 2), 0);
    assertEquals(14.95, rentalAgreement5.getFinalCharge());
  }

  public void testToolFinalCharge_Test6() {
    RentalAgreement rentalAgreement6 = new RentalAgreement(ToolType.JACKHAMMER, 4, LocalDate.of(2020, 7, 2), 50);
    assertEquals(2.99, rentalAgreement6.getFinalCharge());
  }
}
