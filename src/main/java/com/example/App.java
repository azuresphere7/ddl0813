package com.example;

import java.time.LocalDate;

import com.example.controllers.RentalAgreement;
import com.example.utils.Enums.*;

public class App {
    public static void main(String[] args) {
        RentalAgreement rentalAgreement = new RentalAgreement(ToolType.JACKHAMMER, 9, LocalDate.of(2015, 7, 2), 0);
        rentalAgreement.printRentalAgreement();
    }
}
