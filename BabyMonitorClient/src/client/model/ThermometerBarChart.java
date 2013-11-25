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

public class ThermometerBarChart {
	private static DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
	private static ThermometerBarChart tbc = null;
	private static JFreeChart chart;
	private Integer temperature;
	private boolean Farenheit = false;

	@SuppressWarnings("deprecation")
	private ThermometerBarChart() {

		chart = ChartFactory.createBarChart3D(null, "Current Temperature",
				"Centigrade", categoryDataset, PlotOrientation.VERTICAL, true,
				true, false);

		// cutomize plot
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setMaximumBarWidth(0.7);
		renderer.setPaint(ChartColor.DARK_YELLOW);
		renderer.setBase(-10);
		renderer.setBaseItemLabelPaint(Color.orange);
		renderer.setBaseSeriesVisible(false);
		categoryplot.setRenderer(renderer);

		// customize number axis
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setUpperMargin(60);
		numberaxis.setRange(-10, 50);
	}

	public static ThermometerBarChart getInstance() {
		if (tbc == null)
			tbc = new ThermometerBarChart();

		return tbc;

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
		chart.fireChartChanged();
	}

	public void toggleUnits() {

		if (!this.isFarenheit()) {
			// cutomize plot

			CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
			BarRenderer3D renderer = new BarRenderer3D();
			renderer.setMaximumBarWidth(0.7);
			renderer.setPaint(ChartColor.RED);
			renderer.setBase(-10);
			renderer.setBaseItemLabelPaint(Color.orange);
			renderer.setBaseSeriesVisible(false);
			categoryplot.setRenderer(renderer);

			// customize number axis
			NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
			numberaxis.setUpperMargin(60);
			numberaxis.setRange(0, 100);
			numberaxis.setAttributedLabel("Fahrenheit");
			this.setFarenheit(true);
		} else {
			CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
			BarRenderer3D renderer = new BarRenderer3D();
			renderer.setMaximumBarWidth(0.7);
			renderer.setPaint(ChartColor.DARK_YELLOW);
			renderer.setBase(-10);
			renderer.setBaseItemLabelPaint(Color.orange);
			renderer.setBaseSeriesVisible(false);
			categoryplot.setRenderer(renderer);

			// customize number axis
			NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
			numberaxis.setUpperMargin(60);
			numberaxis.setRange(-10, 100);
			numberaxis.setAttributedLabel("Centigrade");
			this.setFarenheit(false);

		}

		chart.fireChartChanged();
	}

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

}
