package client.model;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import client.view.BMS_GUI;

public class ThermometerBarChart {
	private static DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
	private static JFreeChart chart;
	private Integer temperature;
	private boolean Farenheit = false;
	private BMS_GUI gui;
	private BarRenderer3D renderer;
	private NumberAxis numberaxis ;
	private CategoryPlot categoryplot;
	
	@SuppressWarnings("deprecation")
	public ThermometerBarChart(BMS_GUI gui) {

		chart = ChartFactory.createBarChart3D(null, "Current Temperature",
				"Centigrade", categoryDataset, PlotOrientation.VERTICAL, true,
				true, false);

		// cutomize plot
		categoryplot = (CategoryPlot) chart.getPlot();
		renderer = new BarRenderer3D();
		renderer.setMaximumBarWidth(0.7);
		renderer.setPaint(ChartColor.DARK_YELLOW);
		renderer.setBase(-10);
		renderer.setBaseItemLabelPaint(Color.orange);
		renderer.setBaseSeriesVisible(false);
		categoryplot.setRenderer(renderer);

		// customize number axis
		numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setUpperMargin(60);
		numberaxis.setRange(-10, 50);

		// append the thermometer panel to main gui
		if (gui != null) {
			this.setGui(gui);
			this.gui.setThermometerPanel(this.getChartPanel());
		}
	}

	public JPanel getChartPanel() {

		return new ChartPanel(chart);

	}

	public DefaultCategoryDataset getCategoryDataset() {
		return categoryDataset;
	}

	public void notify(Integer temp) {
		if (this.isFarenheit()) {
			temp = (temp * 9) / 5 + 32;
			this.setTemperature(temp);
			this.getCategoryDataset().setValue(this.getTemperature(), "",
					"Temp");
		} else {
			this.setTemperature(temp);
			this.getCategoryDataset().setValue(this.getTemperature(), "",
					"Temp");

		}
		
		// equivalent to calling update() of the gui in an observer pattern ( from JFree Chart library) 
		chart.fireChartChanged();
	}

	
	// toggle logic for changing the units from  C to F and vise-versa
	@SuppressWarnings("deprecation")
	public void toggleUnits() {

		if (!this.isFarenheit()) {
			// cutomize plot
			renderer.setPaint(ChartColor.RED);
			renderer.setBase(-10);


			// customize number axis
			numberaxis.setRange(0, 100);
			numberaxis.setAttributedLabel("Fahrenheit");
			this.setFarenheit(true);
			
		} else {
			renderer.setPaint(ChartColor.DARK_YELLOW);
			renderer.setBase(-10);


			// customize number axis
			numberaxis.setRange(-10, 100);
			numberaxis.setAttributedLabel("Centigrade");
			this.setFarenheit(false);

		}
		// equivalent to calling update() of the gui in an observer pattern ( from JFree Chart library) 
		chart.fireChartChanged();
	}
	
	// getters and setters

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public boolean isFarenheit() {
		return Farenheit;
	}

	public void setFarenheit(boolean farenheit) {
		Farenheit = farenheit;
	}

	public BMS_GUI getGui() {
		return gui;
	}

	public void setGui(BMS_GUI gui) {
		this.gui = gui;
	}

	public BarRenderer3D getRenderer() {
		return renderer;
	}

	public void setRenderer(BarRenderer3D renderer) {
		this.renderer = renderer;
	}

	public NumberAxis getNumberaxis() {
		return numberaxis;
	}

	public void setNumberaxis(NumberAxis numberaxis) {
		this.numberaxis = numberaxis;
	}

	public CategoryPlot getCategoryplot() {
		return categoryplot;
	}

	public void setCategoryplot(CategoryPlot categoryplot) {
		this.categoryplot = categoryplot;
	}

}
