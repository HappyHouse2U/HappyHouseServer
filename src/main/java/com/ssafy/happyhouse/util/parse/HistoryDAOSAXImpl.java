package com.ssafy.happyhouse.util.parse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.happyhouse.domain.History;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HistoryDAOSAXImpl{
	List<History> list = new ArrayList<History>();

	public List<History> getApartmentList(String url) {
		list.clear();
		connectApartment(url);
		return list;
	}

	public History search(int index) {
		return null;
	}
	
	private void connectApartment(String url) {
		SAXParserFactory f = null;
		try {
			f = SAXParserFactory.newInstance();
			SAXParser p = f.newSAXParser();
			p.parse(new URL(url).openConnection().getInputStream(), new SAXHandler());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	class SAXHandler extends DefaultHandler {
		StringBuilder b= new StringBuilder();
		History n = null;
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
				if(qName.equals("item")) {
					n = new History();
				}
		}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			b.append(ch, start, length);
		}
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
				if(n != null) {
					if(qName.equals("아파트")) {
						n.setAptName(b.toString());
					}else if(qName.equals("거래금액")) {
						n.setAmount(Integer.parseInt(b.toString().replace(",", "").replace(" ", "")));
					}else if(qName.equals("전용면적")) {
						n.setArea(Double.parseDouble(b.toString()));
					}else if(qName.equals("법정동시군구코드")) {
						n.setAddressId(b.toString());
					}else if(qName.equals("법정동읍면동코드")) {
						n.setAddressId(n.getAddressId() + b.toString());
					}else if(qName.equals("층")) {
						n.setFloor(Integer.parseInt(b.toString()));
					}else if(qName.equals("item")) {
						list.add(n);
					}
				}
				b.setLength(0);
		}
	}
}
