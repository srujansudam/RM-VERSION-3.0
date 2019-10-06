package com.cg.ibs.remittancemgmtfinal.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import com.cg.ibs.remittancemgmtfinal.bean.AutoPayment;
import com.cg.ibs.remittancemgmtfinal.bean.ServiceProvider;
import com.cg.ibs.remittancemgmtfinal.dao.AutoPaymentDAO;
import com.cg.ibs.remittancemgmtfinal.dao.AutoPaymentDAOImpl;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public class AutoPaymentServiceImpl implements AutoPaymentService {
	AutoPaymentDAO autoPaymentDao;

	public AutoPaymentServiceImpl() {
		autoPaymentDao = new AutoPaymentDAOImpl();
	}

	@Override
	public Set<ServiceProvider> showIBSServiceProviders() {
		return autoPaymentDao.showServiceProviderList();
	}

	@Override
	public boolean autoDeduction(String uci, AutoPayment autoPayment, String dateOfStart) throws RmExceptions {
		LocalDate today = LocalDate.now();
		boolean validAutoDeduct = false;
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startOfAutoPayment = LocalDate.parse(dateOfStart, dtFormatter);
		if (startOfAutoPayment.isBefore(today)) {
			throw new RmExceptions("Enter a valid date");
		} else {
			if (today.equals(startOfAutoPayment)) {
				if (-1 != autoPaymentDao.getCurrentBalance(uci).compareTo(autoPayment.getAmount())) {
					BigDecimal balance = autoPaymentDao.getCurrentBalance(uci).subtract(autoPayment.getAmount());
					autoPaymentDao.setCurrentBalance(uci, balance);
					startOfAutoPayment.plusMonths(1);
				}
			}
			validAutoDeduct = true;
		}
		return validAutoDeduct;
	}

	@Override
	public boolean updateRequirements(String uci, AutoPayment autoPayment) throws RmExceptions {
		return autoPaymentDao.deleteDetails(uci, autoPayment.getServiceProviderId());
	}

}
