package com.cg.ibs.remittancemgmtfinal.dao;

import java.math.BigInteger;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.CreditCard;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public interface CreditCardDAO {
	public Set<CreditCard> getDetails(String uci);

	public void copyDetails(String uci, CreditCard card) throws RmExceptions;

	public boolean deleteDetails(String uci, BigInteger cardNumber) throws RmExceptions;
}
