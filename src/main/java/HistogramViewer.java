/**
 * @author Samuel Near
 * CS2212 - Intro to Software Engineering
 * @purpose HistogramViewer class that will be used to create and utilize the MainUI's Histogram after a trade has happened
 * The Histogram on the main UI will contain information on the nature of the trade in addition to any relevant data
 */
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HistogramViewer implements TradingObservers {
    /*Observer 1 = Histogram viewer (observer design pattern)*/
    private TradeLog tradeLog;
    /**
     * initializer class that initializes the instance varaible
     * @param tradeLog take in a tradeLog (i.e. the previous tradeLog and use it in this context)
     */
    HistogramViewer(TradeLog tradeLog){
        this.tradeLog = tradeLog;
    }
    /**
     * Update method that calls the createBar (i.e. creates a new bar graph)
     * called from an external class to commence any methods and algorithms in this class
     */
    @Override
    public void update() {
        createBar();
    }

    /**
     * CreateBar method that creates a bar graph to show the data of the trades
     * this class writes to the MainUI with any and all relevant data (i.e. Strategy utilized and quantity)
     */
    private void createBar() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //Object[][] realData = new Object[tradeLog.getTradeLog().size()][7];
        ArrayList<StrategyResult> trades = tradeLog.getTradeLog(); // Container holding actual trades

        for (int row=0; row < trades.size(); row++){
            //increment value in histogram
            try{
                dataset.incrementValue(1, trades.get(row).getBroker().getName(),trades.get(row).getStrategy().getName());
            } catch (Exception ex){ //if not present, add value to list at base value
                dataset.addValue(1, trades.get(row).getBroker().getName(),trades.get(row).getStrategy().getName());
            }
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Strategy");
        plot.setDomainAxis(domainAxis);
        LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
        rangeAxis.setRange(new Range(1.0, 20.0));
        plot.setRangeAxis(rangeAxis);

        JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
                true);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.white);
        MainUI.getInstance().updateStats(chartPanel);
    }
}
