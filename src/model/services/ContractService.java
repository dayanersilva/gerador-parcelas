package model.services;

import java.time.LocalDate;

import model.entitites.Contract;
import model.entitites.Installment;

public class ContractService {

	private OnlinePaymentService service;

	public ContractService(OnlinePaymentService service) {
		this.service = service;
	}

	public void processContract(Contract contract, Integer months) {

		double basicQuota = contract.getTotalValue() / months;
		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			Double interest = service.interest(basicQuota, i);
			Double fee = service.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;
			contract.getInstallments().add(new Installment(dueDate, quota));
		}

	}

}
