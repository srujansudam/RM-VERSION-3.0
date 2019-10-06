package com.cg.ibs.remittancemgmtfinal.service;

import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.AutoPayment;
import com.cg.ibs.remittancemgmtfinal.bean.ServiceProvider;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public interface AutoPaymentService {
	public Set<ServiceProvider> showIBSServiceProviders();
	
	public boolean autoDeduction(String uci, AutoPayment autoPayment, String dateOfStart) throws RmExceptions;
	
	public boolean updateRequirements(String uci, AutoPayment autoPayment) throws RmExceptions;
}
