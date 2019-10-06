package com.cg.ibs.remittancemgmtfinal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.Beneficiary;
import com.cg.ibs.remittancemgmtfinal.bean.CreditCard;
import com.cg.ibs.remittancemgmtfinal.bean.FinalCustomer;
import com.cg.ibs.remittancemgmtfinal.bean.TemporaryCustomer;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public class BankRepresentativeDAOImpl implements BankRepresentativeDAO {
	private static Map<String, FinalCustomer> finalMap = new HashMap<>();
	private static Map<String, TemporaryCustomer> tempMap = new HashMap<>();
	private static FinalCustomer finalCustomer = new FinalCustomer();
	private static Set<CreditCard> savedCreditCards = new HashSet<>();
	private static List<Beneficiary> savedBeneficiaries = new ArrayList<>();
	private static Set<CreditCard> unapprovedCreditCards = new HashSet<>();
	private static List<Beneficiary> unapprovedBeneficiaries = new ArrayList<>();

	public Set<String> getRequests()
	{
		return  tempMap.keySet();
	}
	
	public List<Beneficiary> getBeneficiaryDetails(String uci) throws RmExceptions {
		if(!(finalMap.containsKey(uci)))
		{
		throw new RmExceptions("customer doesn't exist");
		}
		return tempMap.get(uci).getUnapprovedBeneficiaries();
	}

	public Set<CreditCard> getCreditCardDetails(String uci) throws RmExceptions {
		if(!(finalMap.containsKey(uci)))
		{
		throw new RmExceptions("customer doesn't exist");
		}
		return tempMap.get(uci).getUnapprovedCreditCards();
	}

	@Override
	public void copyCreditCardDetails(String uci, CreditCard card) {
		savedCreditCards.add(card);
		finalCustomer.setSavedCreditCards(savedCreditCards);
		finalMap.put(uci, finalCustomer);

	}

	public void deleteTempCreditCardDetails(String uci, CreditCard card) {

		if (tempMap.get(uci).getUnapprovedCreditCards().contains(card)) {
			unapprovedCreditCards.remove(card);
		}
	}

	public void deleteTempBeneficiaryDetails(String uci, Beneficiary beneficiary) {
		if (tempMap.get(uci).getUnapprovedBeneficiaries().contains(beneficiary)) {
			unapprovedBeneficiaries.remove(beneficiary);
		}

	}

	@Override
	public void copyBeneficiaryDetails(String uci, Beneficiary beneficiary) {
		savedBeneficiaries.add(beneficiary);
		finalCustomer.setSavedBeneficiaries(savedBeneficiaries);
		finalMap.put(uci, finalCustomer);
	}

}
