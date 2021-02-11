package com.lectorxml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class LectorXML {

	BufferDetalles bufferDetalles = new BufferDetalles();
	
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
				List<String> bufferList = new ArrayList<String>();
				
				int cantOrdenes = Integer.parseInt( list.get( 12 ).getValor() );
				System.out.println( "cantidad: " + cantOrdenes );
				
				List<RecepcionInsert> listInsert = new ArrayList<RecepcionInsert>();
				
				for( int i = 0; i < cantOrdenes; i++) {
					StringBuilder sbInsert = new StringBuilder("insert into INCOMING (FOLIO,NOMBRE_ORDENANTE,ID_TIPO_CUENTA_ORDENANTE,CUENTA_ORDENANTE,RFC_ORDENANTE,NOMBRE_BENEFICIARIO,ID_TIPO_CUENTA_BENEFICIARIO,CUENTA_BENEFICIARIO,RFC_BENEFICIARIO,ID_AREA_EMITE,CONCEPTO_PAGO,MONTO,IVA,REFERENCIA_NUMERICA,REFERENCIA_COBRANZA1,ID_TIPO_PAGO,TOPOLOGIA,PRIORIDAD,RESP_RECEPCION,RESP_BANXICO,ID_TIPO_OPERACION,FECHA_CAPTURA,ID_TIPO_CUENTA_BENEFICIARIO_2,CVE_RASTREO,ID_DEVOLUCION,ID_INSTITUCION_ORD,ID_INSTITUCION_BEN,ID_TIPO_SIAC,FOLIO_PAQUETE,FIRMA,REPARAR,EXPORTAR,STATUS,FOLIO_SERVIDOR,TIPOTRANSFER,ORIGEN,RESPUESTA,FOLIO_ORIGINAL,MONTO_ORIGINAL,INTERESES,FECHA_TRANSFERENCIA_ORIGINAL,FOLIO_PAQUETE_ORIGINAL,FH_OPERACION,MOVIL_ORDENANTE,DV_MOVIL_ORDENANTE,MOVIL_BENEFICIARIO,DV_MOVIL_BENEFICIARIO,FOLIO_ESQ_CODI,COMISION_TRANSFERENCIA_PAGO,COMISION_TRANSFERENCIA_MONTO,NUM_SERIE_COMERCIO,FH_LIMITE_PAGO,CAUSA_CANCELACION,ID_INSTANCIA,FH_CANCELACION)values(");
					
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
					r.setCveRastreo2(" ");
					r.setDvMovilBeneficiario( " " );
					r.setDvMovilOrdenante( 0 );
					r.setExportar( 0 );
					r.setFechaCaptura( "HARD" );
					r.setFechaOperacion( "hard" );
					r.setFechaOriginal( "hard" );
					r.setFhCancelacion( " " );
					r.setFirma( "hardcode" );
					r.setFolio( 0 );
					r.setFolioEsqCodi( " " );
					r.setFolioOriginal( 0 );
					r.setFolioPaquete( 0 );
					r.setFolioServidor( 0 );
					r.setIdAreaEmite( 1 );
					r.setIdDevolucion( 0 );
					r.setIdInstancia( 0 );
					r.setIdInstitucionBen( 6 );
					r.setIdInstitucionOrd( 8 );
					r.setIdTipoCuentaBeneficiario( 40 );
					r.setIdTipoCuentaBeneficiario2( 40 );
					r.setIdTipoCuentaOrdenante( 40 );
					r.setIdTipoOperacion( 1 );
					r.setIdTipoPago( 1 );
					r.setIdTipoSiac( 1 );
					r.setInfAdicional( "hard" );
					r.setIntereses( "0" );
					r.setIva( "0.0" );
					r.setMonto( "hard" );
					r.setMontoOriginal( "hard" );
					r.setMovilBeneficiario( "1234567890" );
					r.setMovilOrdenante( "1234567890" );
					r.setNombreBeneficiario( "hard" );
					r.setNombreBeneficiario2( "hard" );
					r.setNombreOrdenante( "hard" );
					r.setNumSerieComercio( "hard" );
					r.setPrioridad( 0 );
					r.setReferenciaCobranza1( "hard" );
					r.setReferenciaNumerica( 1111111 );
					r.setReparar( false );
					r.setRespBanxico( false );
					r.setRespRecepcion( false );
					r.setRfcBeneficiario( "hard" );
					r.setRfcBeneficiario2( " " );
					r.setRfcOrdenante( "hard" );
					r.setStatus( 0 );
					r.setTopologia( 'V' );
					
					/**
					 * 
					 */
//					if (node.getNodeValue().equals("BUFFER_DETALLES")) {
//						buffer = tempNode.getTextContent();
//					}
					String bufferTmp = list.get( 21 ).getValor();
					System.out.println( "bufferTmp " + bufferTmp );
					bufferDetalles.llenarCampos(rutaArchivo, r);
					/**
					 * 
					 */
					
					sbInsert.append( r.getFolio() );
					sbInsert.append( r.getNombreOrdenante() );
					sbInsert.append( r.getIdTipoCuentaOrdenante() );
					sbInsert.append( r.getCuentaOrdenante() );
					sbInsert.append( r.getRfcOrdenante() );
					sbInsert.append( r.getNombreBeneficiario() );
					sbInsert.append( r.getIdTipoCuentaBeneficiario() );
					sbInsert.append( r.getCuentaBeneficiario() );
					sbInsert.append( r.getRfcBeneficiario() );
					sbInsert.append( r.getIdAreaEmite() );
					sbInsert.append( r.getConceptoPago() );
					sbInsert.append( r.getMonto() );
					sbInsert.append( r.getIva() );
					sbInsert.append( r.getReferenciaNumerica() );
					sbInsert.append( r.getReferenciaCobranza1() );
					sbInsert.append( r.getIdTipoPago() );
					sbInsert.append( r.getTopologia() );
					sbInsert.append( r.getPrioridad() );
					sbInsert.append( r.isRespRecepcion() );
					sbInsert.append( r.isRespBanxico() );
					sbInsert.append( r.getIdTipoOperacion() );
					sbInsert.append( r.getFechaCaptura() );
					sbInsert.append( r.getIdTipoCuentaBeneficiario2() );
					sbInsert.append( r.getCveRastreo() );
					sbInsert.append( r.getIdDevolucion() );
					sbInsert.append( r.getIdInstitucionOrd() );
					sbInsert.append( r.getIdInstitucionBen() );
					sbInsert.append( r.getIdTipoSiac() );
					sbInsert.append( r.getFolioPaquete() );
					sbInsert.append( r.getFirma() );
					sbInsert.append( r.isReparar() );
					sbInsert.append( r.getExportar() );
					sbInsert.append( r.getStatus() );
					sbInsert.append( r.getFolioServidor() );
					sbInsert.append( " " );
					sbInsert.append( " " );
					sbInsert.append( " " );
					sbInsert.append( r.getFolioOriginal() );
					sbInsert.append( r.getMontoOriginal() );
					sbInsert.append( r.getIntereses() );
					sbInsert.append( r.getFechaOriginal() );
					sbInsert.append( r.getFechaOperacion() );
					sbInsert.append( r.getMovilOrdenante() );
					sbInsert.append( r.getDvMovilOrdenante() );
					sbInsert.append( r.getMovilOrdenante() );
					sbInsert.append( r.getDvMovilBeneficiario() );
					sbInsert.append( r.getFolioEsqCodi() );
					sbInsert.append( r.getComisionTransferenciaPago() );
					sbInsert.append( r.getComisionTransferenciaMonto() );
					sbInsert.append( r.getNumSerieComercio() );
					sbInsert.append( "" );
					sbInsert.append( "" );
					sbInsert.append( r.getIdInstancia() );
					sbInsert.append( r.getFhCancelacion() );
					
					System.out.println( sbInsert );
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
