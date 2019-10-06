package com.cg.ibs.remittancemgmtfinal.service;

import java.util.List;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.Beneficiary;
import com.cg.ibs.remittancemgmtfinal.bean.CreditCard;
import com.cg.ibs.remittancemgmtfinal.dao.BankRepresentativeDAO;
import com.cg.ibs.remittancemgmtfinal.dao.BankRepresentativeDAOImpl;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;


public class BankRepresentativeServiceImpl implements BankRepresentativeService{
	BankRepresentativeDAO bankRepresentativeDAO = new BankRepresentativeDAOImpl();
	@Override
	public Set<String> showRequests() {
		return bankRepresentativeDAO.getRequests();
	}
	
	@Override
	public Set<CreditCard> showUnapprovedCreditCards(String uci) throws RmExceptions {
		return bankRepresentativeDAO.getCreditCardDetails(uci);
	}

	@Override
	public List<Beneficiary> showUnapprovedBeneficiaries(String uci) throws RmExceptions {
		return bankRepresentativeDAO.getBeneficiaryDetails(uci);
	}

	@Override
	public void saveCreditCardDetails(String uci, CreditCard card) {
		bankRepresentativeDAO.copyCreditCardDetails(uci, card);
		
	}

	@Override
	public void saveBeneficiaryDetails(String uci, Beneficiary beneficiary) {
		bankRepresentativeDAO.copyBeneficiaryDetails(uci, beneficiary);
	}

	@Override
	public void removeTempCreditCardDetails(String uci, CreditCard card) {
		bankRepresentativeDAO.deleteTempCreditCardDetails(uci, card);
	}

	@Override
	public void removeTempBeneficiaryDetails(String uci, Beneficiary beneficiary) {
		bankRepresentativeDAO.deleteTempBeneficiaryDetails(uci, beneficiary);
	}
	
	

}
