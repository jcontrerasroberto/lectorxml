package com.lectorxml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class LectorXML {

	public static void main(String[] args) {
		LectorXML lector = new LectorXML();
		lector.revisaArchivo();
	}
	
	private void revisaArchivo() {
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
				List<Atomo> list = printNote(tempNode.getChildNodes());
				
				int cantOrdenes = Integer.parseInt( list.get( 12 ).getValor() );
				System.out.println( "cantidad: " + cantOrdenes );
				
				List<RecepcionInsert> listInsert = new ArrayList<RecepcionInsert>();
				
				for( int i = 0; i < cantOrdenes; i++) {
					RecepcionInsert r = new RecepcionInsert();
					r.setClavePago("");
					r.setComisionTransferenciaMonto("0");
					r.setComisionTransferenciaPago( 0 );
					r.setConceptoPago( "hard" );
					r.setConceptoPago2( "hard" );
					r.setCuentaBeneficiario( "hard" );
					r.setCuentaBeneficiario2( "hard2" );
					r.setCuentaOrdenante( "hard" );
					r.setCveRastreo( list.get( 18 ).toString() );
				}
				
		    }
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Atomo> printNote(NodeList nodeList) {

		List<Atomo> atomList = new ArrayList<Atomo>();
		
		for (int count = 0; count < nodeList.getLength(); count++) {
			
			Atomo a = new Atomo();
			
			Node tempNode = nodeList.item(count);
			
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				
				System.out.println("\n[INICIO]");
				System.out.println("Node Name = " + tempNode.getNodeName());
				

				if (tempNode.hasAttributes()) {

					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						if( node.getNodeName().equals( "desc" ) ) {
							a.setDesc( node.getNodeValue() );
						} else if ( node.getNodeName().equals( "num" ) ) {
							a.setNum( Integer.parseInt( node.getNodeValue() ) );
						} else if ( node.getNodeName().equals( "tipo" ) ) {
							a.setTipo( node.getNodeValue() );							
						}
						System.out.print( node.getNodeName() );
						System.out.print(" = ");
						System.out.println(node.getNodeValue());
					}

				}
				a.setValor( tempNode.getTextContent().replace("[", "").replace("]", "") );
				System.out.println("Valor del nodo = " + tempNode.getTextContent() );
				System.out.println("[FIN]");
				atomList.add(a);
			}
		}
		
		for(Atomo a:atomList) {
			System.out.println("atomo: " + a.getDesc() + "  " + a.getValor());
		}
		
		return atomList;
	}
}
