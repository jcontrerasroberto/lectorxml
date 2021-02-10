package com.lectorxml;

import java.io.File;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class LectorXML {

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
					}

				}
				
				System.out.println("Valor del nodo = " + tempNode.getTextContent());

				System.out.println("[FIN]");

			}
		}
	}
}
