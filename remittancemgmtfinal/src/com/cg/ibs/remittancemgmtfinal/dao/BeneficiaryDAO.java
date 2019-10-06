package com.cg.ibs.remittancemgmtfinal.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.ibs.remittancemgmtfinal.bean.Beneficiary;
import com.cg.ibs.remittancemgmtfinal.exception.RmExceptions;

public interface BeneficiaryDAO {

	public List<Beneficiary> getDetails(String uci);

	public void copyDetails(String uci, Beneficiary beneficiary)throws RmExceptions;

	public boolean updateDetails(String uci, Beneficiary beneficiary) throws RmExceptions;
	
	public boolean deleteDetails(String uci, BigInteger accountNumber) throws RmExceptions;
	
	public Beneficiary getBeneficiary(String uci, BigInteger accountNumber) throws RmExceptions;

}
