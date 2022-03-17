package br.com.financas.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.financas.model.Compra;

public class ComprasMain {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		Compra compra1 = new Compra();

		compra1.setDescricao("Celpe");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date diaDaCompra = sdf.parse("06/03/2022");
		compra1.setData(diaDaCompra);

		compra1.setValor(208.50);

		compra1.setParcelas(2);

		Calendar calendar = Calendar.getInstance();
		@SuppressWarnings("deprecation")
		int mes = calendar.getTime().getMonth();
		calendar.set(Calendar.MONTH, mes + 1);

	}

}
