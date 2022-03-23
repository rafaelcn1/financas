package br.com.financas.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.financas.dao.CompraDAO;
import br.com.financas.model.Compra;

public class ComprasMain {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		Compra compra1 = new Compra();

		compra1.setDescricao("Celpe");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date diaDaCompra = sdf.parse("29/01/2022");
		compra1.setData(diaDaCompra);

		compra1.setValor(100.00);

		compra1.setParcelas(2);

		Calendar calendar = Calendar.getInstance();
		
		System.out.println(diaDaCompra);
		calendar.setTime(diaDaCompra);
		System.out.println(calendar.getTime());
		System.out.println(calendar.getTime().getDate());

		@SuppressWarnings("deprecation")
		int mes = calendar.getTime().getMonth();
		// calendar.set(Calendar.MONTH, mes + 1);
		if (calendar.getTime().getDate() == 31 || calendar.getTime().getDate() == 30
				|| calendar.getTime().getDate() == 29 && calendar.getTime().getMonth() == 0) {
			calendar.set(calendar.getTime().getYear(), Calendar.MARCH, 1);
			System.out.println(calendar.getTime());
		} else {
			System.out.println("Não entrou!");
		}
		System.out.println(calendar.getTime());
		
		
		

	}

}
