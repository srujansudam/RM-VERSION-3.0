package com.cg.ibs.remittancemgmtfinal.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cg.ibs.remittancemgmtfinal.bean.Beneficiary;
import com.cg.ibs.remittancemgmtfinal.bean.FinalCustomer;
import com.cg.ibs.remittancemgmtfinal.bean.TemporaryCustomer;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {
	private static Map<String, TemporaryCustomer> tempMap = new HashMap<>();
	private static Map<String, FinalCustomer> finalMap = new HashMap<>();
	private static List<Beneficiary> savedBeneficiaries = new ArrayList<>();
	private static List<Beneficiary> unapprovedBeneficiaries = new ArrayList<>();
	private static TemporaryCustomer tempCustomer = new TemporaryCustomer();
	Iterator<Beneficiary> it;

	@Override
	public List<Beneficiary> getDetails(String uci) {
		return finalMap.get(uci).getSavedBeneficiaries();
	}

	@Override
	public void copyDetails(String uci, Beneficiary beneficiary) throws RmExceptions {
		if (finalMap.get(uci).getSavedBeneficiaries().contains(beneficiary)) {
			throw new RmExceptions("Beneficiary already added");
		} else {
			unapprovedBeneficiaries.add(beneficiary);
			tempCustomer.setUnapprovedBeneficiaries(unapprovedBeneficiaries);
			tempMap.put(uci, tempCustomer);
		}

	}

	public Beneficiary getBeneficiary(String uci, BigInteger accountNumber) {
		Beneficiary beneficiary1 = null;
		it = finalMap.get(uci).getSavedBeneficiaries().iterator();
		while (it.hasNext()) {
			Beneficiary beneficiary = it.next();
			if (beneficiary.getAccountNumber().equals(accountNumber)) {
				beneficiary1 = beneficiary;

			}
		}
		return beneficiary1;
	}

	@Override
	public boolean updateDetails(String uci, Beneficiary beneficiary1) throws RmExceptions {

		boolean result = false;

		if (!(finalMap.get(uci).getSavedBeneficiaries().contains(beneficiary1))) {
			throw new RmExceptions("Beneficiary doesn't exist");

		}
		it = finalMap.get(uci).getSavedBeneficiaries().iterator();
		while (it.hasNext()) {
			Beneficiary beneficiary = it.next();
			if (beneficiary.getAccountNumber().equals(beneficiary1.getAccountNumber())) {
				savedBeneficiaries.remove(beneficiary);
				result = true;
			}
		}
		unapprovedBeneficiaries.add(beneficiary1);
		tempCustomer.setUnapprovedBeneficiaries(unapprovedBeneficiaries);
		tempMap.put(uci, tempCustomer);

		return result;

	}

	@Override
	public boolean deleteDetails(String uci, BigInteger accountNumber) throws RmExceptions {
		boolean result = false;
		int count = 0;
		for (Beneficiary beneficiary : finalMap.get(uci).getSavedBeneficiaries()) {
			if (beneficiary.getAccountNumber().equals(accountNumber)) {
				count++;
			}
		}
		if (0 == count) {
			throw new RmExceptions("Beneficiary doesn't exist");
		} else {
			it = finalMap.get(uci).getSavedBeneficiaries().iterator();
			while (it.hasNext()) {
				Beneficiary beneficiary = it.next();
				if (beneficiary.getAccountNumber().equals(accountNumber)) {
					savedBeneficiaries.remove(beneficiary);
					result = true;
				}
			}
		}
		return result;
	}

}
