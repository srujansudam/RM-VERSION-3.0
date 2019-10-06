package com.cg.ibs.remittancemgmtfinal.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.AutoPayment;
import com.cg.ibs.remittancemgmtfinal.bean.ServiceProvider;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public interface AutoPaymentDAO {
	public Set<AutoPayment> getDetails(String uci);

	public void copyDetails(String uci, AutoPayment autoPayment);

	public boolean deleteDetails(String uci, BigInteger serviceProviderId) throws RmExceptions;

	public Set<ServiceProvider> showServiceProviderList();

	public BigDecimal getCurrentBalance(String uci);

	public void setCurrentBalance(String uci, BigDecimal currentBalnce);

}
