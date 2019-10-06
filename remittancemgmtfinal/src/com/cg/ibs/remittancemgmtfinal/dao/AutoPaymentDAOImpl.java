package com.cg.ibs.remittancemgmtfinal.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.AutoPayment;
import com.cg.ibs.remittancemgmtfinal.bean.FinalCustomer;
import com.cg.ibs.remittancemgmtfinal.bean.ServiceProvider;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;


public class AutoPaymentDAOImpl implements AutoPaymentDAO {
	private static Set<ServiceProvider> providers = new HashSet<>();
	ServiceProvider provider = new ServiceProvider();
	private static FinalCustomer finalCustomer = new FinalCustomer();
	private static Map<String, FinalCustomer> finalMap = new HashMap<>();
	private static Set<AutoPayment> savedAutoPaymentServices = new HashSet<>();
	Iterator<AutoPayment> it;
	

	@Override
	public Set<AutoPayment> getDetails(String uci) {

		return finalMap.get(uci).getSavedAutoPaymentServices();
	}

	@Override
	public void copyDetails(String uci, AutoPayment autoPayment) {
		savedAutoPaymentServices.add(autoPayment);
		finalCustomer.setSavedAutoPaymentServices(savedAutoPaymentServices);
		finalMap.put(uci, finalCustomer);
	}

	@Override
	public boolean deleteDetails(String uci, BigInteger serviceProviderId) throws RmExceptions {
		boolean result = false;
		int count = 0;
		for (AutoPayment autoPayment : finalMap.get(uci).getSavedAutoPaymentServices()) {
			if (autoPayment.getServiceProviderId().equals(serviceProviderId)) {
				count++;
			}
		}
		if (0 == count) {
			throw new RmExceptions("Auto payment service doesn't exist");
		} else {
			it = finalMap.get(uci).getSavedAutoPaymentServices().iterator();
			while (it.hasNext()) {
				AutoPayment autoPayment = it.next();
				if (autoPayment.getServiceProviderId().equals(serviceProviderId)) {
					savedAutoPaymentServices.remove(autoPayment);
					result = true;
				}
			}
			return result;
		}
	}

	@Override
	public BigDecimal getCurrentBalance(String uci) {
		return finalMap.get(uci).getCurrentBalance();
	}

	@Override
	public void setCurrentBalance(String uci, BigDecimal currentBalnce) {
		finalCustomer.setCurrentBalance(currentBalnce);
		finalMap.put(uci, finalCustomer);
	}

	@Override
	public Set<ServiceProvider> showServiceProviderList() {
	provider.setSpi(new BigInteger("1"));
	provider.setNameOfCompany("Airtel");
	providers.add(provider);

	provider.setSpi(new BigInteger("2"));
	provider.setNameOfCompany("Reliance Jio");
	providers.add(provider);

	provider.setSpi(new BigInteger("3"));
	provider.setNameOfCompany("Tata Sky");
	providers.add(provider);

	provider.setSpi(new BigInteger("4"));
	provider.setNameOfCompany("MTS India");
	providers.add(provider);

	return providers;
	}
}
