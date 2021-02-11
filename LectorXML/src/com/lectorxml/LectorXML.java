package com.lectorxml;

import java.io.File;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class LectorXML {

	private static String buffer;
	private static String nombre_1 = "", tipo_cta_1 = "", cuenta_1 = "", rfc_1 = "";
	private static String nombre_2 = "", tipo_cta_2 = "", cuenta_2 = "", rfc_2 = "";
	private static String concepto = "", iva = "", monto = "";

	public static void main(String[] args) {
		System.out.println("Lector XML");
		Scanner entrada = new Scanner(System.in);
		String rutaArchivo = "";
		System.out.println("Ingresa el nombre del archivo");
		rutaArchivo = entrada.nextLine();

		try {
			File xmlFile = new File(rutaArchivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			if (doc.hasChildNodes()) {
				Node tempNode = doc.getChildNodes().item(0);
				printNote(tempNode.getChildNodes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				System.out.println("\n[INICIO]");
				System.out.println("Node Name = " + tempNode.getNodeName());

				if (tempNode.hasAttributes()) {

					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						System.out.print(node.getNodeName());
						System.out.print(" = ");
						System.out.println(node.getNodeValue());
						if (node.getNodeValue().equals("BUFFER_DETALLES")) {
							buffer = tempNode.getTextContent();
						}
					}

				}

				System.out.println("Valor del nodo = " + tempNode.getTextContent());

				System.out.println("[FIN]");

			}
		}

		llenarCampos(buffer);
	}

	private static void llenarCampos(String buffer) {
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
		System.out.println("Tipo de cuenta 1: " + tipo_cta_1);
		System.out.println("Cuenta 1: " + cuenta_1);
		System.out.println("RFC 1: " + rfc_1);
		System.out.println("Nombre 2: " + nombre_2);
		System.out.println("Tipo de cuenta 2: " + tipo_cta_2);
		System.out.println("Cuenta 2: " + cuenta_2);
		System.out.println("RFC 2: " + rfc_2);
		System.out.println("Concepto: " + concepto);
		System.out.println("IVA: " + iva);
		System.out.println("Monto: " + monto);

	}
}
