package com.lectorxml;

public class BufferDetalles {

	private static String buffer;
	private static String nombre_1 = "", tipo_cta_1 = "", cuenta_1 = "", rfc_1 = "";
	private static String nombre_2 = "", tipo_cta_2 = "", cuenta_2 = "", rfc_2 = "";
	private static String concepto = "", iva = "", monto = "";
	
	private RecepcionInsert llenarCampos(String buffer, RecepcionInsert r) {
		System.out.println("\n\nLlenando los campos");
		// Eliminamos los [ ]
		buffer = buffer.substring(1);
		buffer = buffer.substring(0, buffer.length() - 1);
		String separados[] = buffer.split(" ");
		int sizearray = separados.length;
		int flag = 0;
		int pos1 = 0, pos2 = 0;

		//Buscamos donde esten los dos 40
		for (int j = 0; j < sizearray; j++) {
			if (separados[j].equals("40")) {
				pos1 = j;
				break;
			}
		}

		for (int j = pos1 + 1; j < sizearray; j++) {
			if (separados[j].equals("40")) {
				pos2 = j;
				break;
			}
		}

		//Llenamos todos los datos *_1
		for (int i = 0; i <= pos1 + 2; i++) {
			if (i < pos1) {
				nombre_1 += separados[i] + " ";
			}
			if (i == pos1)
				tipo_cta_1 = separados[i];
			if (i == pos1 + 1)
				cuenta_1 = separados[i];
			if (i == pos1 + 2)
				rfc_1 = separados[i];
		}

		//Llenamos todos los datos *_2
		for (int i = pos1 + 3; i <= pos2 + 2; i++) {
			if (i < pos2) {
				nombre_2 += separados[i] + " ";
			}
			if (i == pos2)
				tipo_cta_2 = separados[i];
			if (i == pos2 + 1)
				cuenta_2 = separados[i];
			if (i == pos2 + 2)
				rfc_2 = separados[i];
		}

		//Llenamos concepto, iva y monto
		for (int i = pos2 + 3; i < sizearray; i++) {
			if (i < sizearray - 2) {
				concepto += separados[i] + " ";
			}
			if (i == sizearray - 2)
				iva = separados[i];
			if (i == sizearray - 1)
				monto = separados[i];
		}
		
		//Eliminamos el ultimo espacio en nombre1, nombre2 y concepto
		nombre_1.substring(0, nombre_1.length()-1);
		nombre_2.substring(0, nombre_2.length()-1);
		concepto.substring(0, concepto.length()-1);

		System.out.println("Nombre 1: " + nombre_1);
		r.setNombreOrdenante( nombre_1 );
		System.out.println("Tipo de cuenta 1: " + tipo_cta_1);
		r.setIdTipoCuentaOrdenante( Integer.parseInt( tipo_cta_1 ) );
		System.out.println("Cuenta 1: " + cuenta_1);
		r.setCuentaOrdenante( cuenta_1 );
		System.out.println("RFC 1: " + rfc_1);
		r.setRfcOrdenante( rfc_1 );
		System.out.println("Nombre 2: " + nombre_2);
		r.setNombreBeneficiario( nombre_2 );
		System.out.println("Tipo de cuenta 2: " + tipo_cta_2);
		r.setIdTipoCuentaBeneficiario( Integer.parseInt( tipo_cta_2 ) );
		System.out.println("Cuenta 2: " + cuenta_2);
		r.setCuentaBeneficiario( cuenta_2 );
		System.out.println("RFC 2: " + rfc_2);
		r.setRfcBeneficiario( rfc_2 );
		System.out.println("Concepto: " + concepto);
		r.setConceptoPago( concepto );
		System.out.println("IVA: " + iva);
		r.setIva( iva );
		System.out.println("Monto: " + monto);
		r.setMonto( monto );

		return r;
	}
	
}
