package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entitites.Contract;
import model.entitites.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);

		System.out.println("Entre os dados do contrato: ");
		System.out.print("Numero: ");
		int number = scan.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate data = LocalDate.parse(scan.next(), dtf);

		System.out.print("Valor do contrato: ");
		double totalValue = scan.nextDouble();

		Contract contract = new Contract(number, data, totalValue);

		System.out.print("Entre com o numero de parcelas: ");
		int months = scan.nextInt();

		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, months);

		System.out.println("Parcelas: ");
		for (Installment fatura : contract.getInstallments()) {
			System.out.println(fatura);
		}

		scan.close();

	}

}
