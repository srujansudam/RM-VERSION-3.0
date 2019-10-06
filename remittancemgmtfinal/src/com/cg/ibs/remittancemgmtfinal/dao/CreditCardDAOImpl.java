package com.cg.ibs.remittancemgmtfinal.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.CreditCard;
import com.cg.ibs.remittancemgmtfinal.bean.FinalCustomer;
import com.cg.ibs.remittancemgmtfinal.bean.TemporaryCustomer;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public class CreditCardDAOImpl implements CreditCardDAO {
	private static FinalCustomer customer = new FinalCustomer();
	private static Map<String, TemporaryCustomer> tempMap = new HashMap<>();
	private static Set<CreditCard> savedCreditCards = new HashSet<>();
	private static Map<String, FinalCustomer> finalMap = new HashMap<>();
	private static Set<CreditCard> unapprovedCreditCards = new HashSet<>();
	private static TemporaryCustomer tempCustomer = new TemporaryCustomer();
	Iterator<CreditCard> it;

	@Override
	public Set<CreditCard> getDetails(String uci) {
		return finalMap.get(uci).getSavedCreditCards();
	}

	@Override
	public void copyDetails(String uci, CreditCard card) throws RmExceptions {
		if (finalMap.get(uci).getSavedCreditCards().contains(card)) {
			throw new RmExceptions("Credit card already added");
		} else {
			unapprovedCreditCards.add(card);
			customer.setSavedCreditCards(unapprovedCreditCards);
			tempMap.put(uci, tempCustomer);
		}
	}

	@Override
	public boolean deleteDetails(String uci, BigInteger cardNumber) throws RmExceptions {
		boolean result = false;

		int count = 0;
		for (CreditCard card : finalMap.get(uci).getSavedCreditCards()) {
			if (card.getcreditCardNumber().equals(cardNumber)) {
				count++;
			}
		}
		if (0 == count) {
			throw new RmExceptions("Credit card doesn't exist");
		} else {
			it = finalMap.get(uci).getSavedCreditCards().iterator();
			while (it.hasNext()) {
				CreditCard card = it.next();
				if (card.getcreditCardNumber().equals(cardNumber)) {
					savedCreditCards.remove(card);
					result = true;
				}
			}
			return result;
		}
	}

}
