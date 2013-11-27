package client.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import client.model.NoiseAlarm;
import client.model.ThermometerBarChart;
import client.startUp.ClientMain;

public class TestModel {
	ClientMain cm;
	NoiseAlarm na;
	ThermometerBarChart tbc;

	@Before
	public void setup() {
		na= new NoiseAlarm(null);
		tbc= new ThermometerBarChart(null);
		cm = new ClientMain(na,tbc);
	}

	@Test
	public void testTemperatureXmlParsing() {
		String xmlData = "<temp>0</temp>";
		tbc.setFarenheit(false);
		cm.parseXmlMsg(xmlData);
		assertEquals(0, (int) tbc
				.getTemperature());
	}
	
	@Test (expected=java.lang.NumberFormatException.class)
	public void testBigTemperatureXmlParsing() {
		String xmlData = "<temp>999999999999</temp>";
		tbc.setFarenheit(false);
		cm.parseXmlMsg(xmlData);
	}
	
	@Test
	public void testTemperatureXmlParsingFarenheit() {
		String xmlData = "<temp>0</temp>";
		
		tbc.setFarenheit(true);
		cm.parseXmlMsg(xmlData);
		assertEquals(32, (int) tbc
				.getTemperature());
	}

	@Test
	public void testAlarmXmlParsing() {
		String xmlData = "<alarm>true</alarm>";
		cm.parseXmlMsg(xmlData);
		assertEquals(true, na.isStatus());
	}
	
}