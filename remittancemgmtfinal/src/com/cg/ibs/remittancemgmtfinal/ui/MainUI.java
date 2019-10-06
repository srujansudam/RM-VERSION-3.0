package com.cg.ibs.remittancemgmtfinal.ui;

import java.util.Iterator;
import java.util.Scanner;

import com.cg.ibs.remittancemgmtfinal.bean.Beneficiary;
import com.cg.ibs.remittancemgmtfinal.bean.CreditCard;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;
import com.cg.ibs.remittancemgmtfinal.service.BankRepresentativeService;
import com.cg.ibs.remittancemgmtfinal.service.BankRepresentativeServiceImpl;

public class MainUI {
	private static Scanner scanner;

	public static void main(String[] args) {
		Iterator<CreditCard> itCredit;
		Iterator<Beneficiary> itBeneficiary;
		scanner = new Scanner(System.in);
		BankRepresentativeService bankRepresentativeService = new BankRepresentativeServiceImpl();
		int choice;
		do {
			System.out.println("Press 1 to view request list \nPress 2 to exit");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println(bankRepresentativeService.showRequests());
				String uci;
				System.out.println("Please enter the customer id to view individual requests \nEnter 0 to exit");
				uci = scanner.next();
				while (!(uci.equals("0"))) {
					System.out.println("Id entered by you is : " + uci);
					int choice1;
					do {
						System.out.println(
								"Enter 1 to view Creditcard requests \nEnter 2 to view Beneficiary Requests \nEnter 3 to go to the previous list ");
						choice1 = scanner.nextInt();
						switch (choice1) {
						case 1:
							try {
								itCredit = bankRepresentativeService.showUnapprovedCreditCards(uci).iterator();
								if (!(itCredit.hasNext())) {
									System.out.println("No more credit card requests");
									break;
								}
								while (itCredit.hasNext()) {
									CreditCard creditCard = itCredit.next();
									System.out.println(creditCard);
									int choice2;
									do {
										System.out.println(
												"Enter 1 to approve. \nEnter 2 to disapprove \n Enter 3 to Exit the section");
										choice2 = scanner.nextInt();
										switch (choice2) {
										case 1:
											bankRepresentativeService.saveCreditCardDetails(uci, creditCard);
											System.out.println("Card approved");
											break;
										case 2:
											bankRepresentativeService.removeTempCreditCardDetails(uci, creditCard);
											System.out.println("Card disapproved");
											break;
										default:
											System.out.println("Enter a valid choice of decision of credit card");
											choice2 = 0;
										}
									} while (0 == choice2);
								}
							} catch (RmExceptions e) {
								e.printStackTrace();
							}
							break;
						case 2:
							try {
								itBeneficiary = bankRepresentativeService.showUnapprovedBeneficiaries(uci).iterator();
								if (!(itBeneficiary.hasNext())) {
									System.out.println("No more beneficiary requests");
									break;
								}
								while (itBeneficiary.hasNext()) {
									Beneficiary beneficiary = itBeneficiary.next();
									System.out.println(beneficiary);
									int choice2;
									do {
										System.out.println("Enter 1 to approve. \nEnter 2 to disapprove ");
										choice2 = scanner.nextInt();
										switch (choice2) {
										case 1:
											bankRepresentativeService.saveBeneficiaryDetails(uci, beneficiary);
											System.out.println("Beneficiary approved");
											break;
										case 2:
											bankRepresentativeService.removeTempBeneficiaryDetails(uci, beneficiary);
											System.out.println("Beneficiary disapproved");
											break;
										default:
											System.out.println("Enter a valid choice of decision of beneficiary");
											choice2 = 0;
										}
									} while (0 == choice2);
								}
							} catch (RmExceptions e) {
								e.printStackTrace();
							}
							break;
						case 3:
							choice1 = 0;
							uci = "0";
							break;
						default:
							System.out.println("Enter a valid choice of action");
						}

					} while (0 != choice1);
					System.out.println("Please enter the customer id to view individual requests \nEnter 0 to exit");
					uci = scanner.next();
				}
				break;
			case 2:
				System.out.println("Thankyou for your visit");
				choice = 0;
				break;

			default:
				System.out.println("Please enter a valid choice");
			}
		} while (0 != choice);
		scanner.close();
	}
}
