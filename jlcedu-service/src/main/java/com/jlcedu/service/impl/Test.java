package com.jlcedu.service.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.springframework.util.StringUtils;

public class Test {
	public void test() throws IOException {
		JFreeChart jFreeChart = null;
		//创建主题样式     
//		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");     
		//设置标题字体     
//		standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));     
		//设置图例的字体    
//		standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));     
		//设置轴向的字体     
//		standardChartTheme.setLargeFont(new Font("宋书",Font.BOLD,15));
//		ChartFactory.setChartTheme(standardChartTheme);

//		jFreeChart = ChartFactory.createXYLineChart("综合考试成绩年级名次趋势图", "考试日期", "名次", createXYDataset(), PlotOrientation.VERTICAL, true, false, false);
		jFreeChart = ChartFactory.createLineChart(TITLE_ZH_TW, TITLE_X_ZH_TW, TITLE_Y_ZH_TW, createCategoryDataset(), PlotOrientation.VERTICAL, true, false, false);
		jFreeChart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//大title中文不乱码
		jFreeChart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));//图例中文不乱码
//		TextTitle textTitle = new TextTitle("日期: " + new Date());// 创建一个标题
//		textTitle.setFont(new Font("黑体", 0, 10));//设置标题字体
//		textTitle.setPosition(RectangleEdge.BOTTOM);//设置标题向下对齐
//		textTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);//设置标题向右对齐
//		jFreeChart.addSubtitle(textTitle);//添加图表的子标题

		jFreeChart.setBackgroundPaint(Color.WHITE);//设置图表的背景色为白色
		//获得图表区域对象  
		CategoryPlot categoryPlot = null;
//		categoryPlot = (CategoryPlot)jFreeChart.getPlot();
		categoryPlot = jFreeChart.getCategoryPlot();
		categoryPlot.setBackgroundPaint(Color.WHITE);//背景颜色
		categoryPlot.setDomainGridlinesVisible(true);//网格竖线可见
		categoryPlot.setDomainGridlinePaint(Color.BLACK);//网格竖线颜色
		categoryPlot.setRangeGridlinesVisible(true);//网格横线可见
		categoryPlot.setRangeGridlinePaint(Color.BLACK);//网格横线颜色
		categoryPlot.setNoDataMessage("No data");//没有数据时显示的文字说明

		ValueAxis valueAxis = categoryPlot.getRangeAxis();
		valueAxis.setRange(0, 20);//名次坐标范围
		valueAxis.setInverted(true);//名次坐标从大到小
		valueAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));//竖title中文不乱码
		valueAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));//竖axis中文不乱码
		categoryPlot.getDomainAxis().setLabelFont(new Font("黑体", Font.BOLD, 14));//横title中文不乱码
		categoryPlot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.BOLD, 12));//横axis中文不乱码

		//数据轴属性部分
//        NumberAxis rangeAxis = (NumberAxis)valueAxis;
//        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        rangeAxis.setAutoRangeIncludesZero(true);//自动生成
//        rangeAxis.setUpperMargin(0.20);
//        rangeAxis.setLabelAngle(Math.PI/2.0);
//        rangeAxis.setAutoRange(false);

		// 获显示线条对象
//		System.out.println(categoryPlot.getRenderer().getClass().getName());
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
		lineAndShapeRenderer.setBaseItemLabelsVisible(true);//显示坐标值
		lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示坐标值
//		lineAndShapeRenderer.setBaseItemLabelFont(new Font("Dialog", 1, 12));//坐标值字体
		lineAndShapeRenderer.setBaseShapesVisible(true);//显示坐标点
		lineAndShapeRenderer.setBaseShapesFilled(true);//填满坐标点
//		lineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(3F));//设置折线加粗
		lineAndShapeRenderer.setSeriesShape(0, new Ellipse2D.Double(-5D, -5D, 10D, 10D));//坐标点形状和大小
		
//		lineAndShapeRenderer.setSeriesPaint(0, Color.BLACK);//设置折线的颜色
//		lineAndShapeRenderer.setAutoPopulateSeriesFillPaint(true);
//		lineAndShapeRenderer.setDrawOutlines(true);
//		lineAndShapeRenderer.setUseFillPaint(true);
//		lineAndShapeRenderer.setBaseFillPaint(Color.BLACK);
//		lineAndShapeRenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
//		lineAndShapeRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));

		/*jFreeChart = ChartFactory.createTimeSeriesChart("综合考试成绩年级名次走势图", "考试日期", "名次", createTimeSeriesChart(), true, true, true);
		XYPlot xyPlot = (XYPlot) jFreeChart.getPlot();
		DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
		dateAxis.setDateFormatOverride(new SimpleDateFormat("MMM yyyy"));

		dateAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));//水平底部标题
		dateAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));//垂直标题
		ValueAxis rangeAxis = xyPlot.getRangeAxis();//获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		jFreeChart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));*/

		ChartUtilities.saveChartAsJPEG(new File("C:/Users/maqiang/Desktop/exam-t-wangym-mock2-zh_tw.jpg"), jFreeChart, 800, 500);
		ChartFrame chartFrame = new ChartFrame("try1", jFreeChart);
		chartFrame.pack();
		chartFrame.setVisible(true);

		ChartPanel chartPanel = new ChartPanel(jFreeChart, true);
		chartPanel.setVisible(true);
	}
	
	private static XYDataset createTimeSeriesChart() {//这个数据集有点多，但都不难理解
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

		TimeSeries timeSeries = new TimeSeries("年级名次", Month.class);
		timeSeries.add(new Month(11, 2015), 151);
		timeSeries.add(new Month(1, 2016), 161);
		timeSeries.add(new Month(5, 2016), 149);
		timeSeries.add(new Month(7, 2016), 130);
		timeSeries.add(new Month(11, 2016), 123);
		timeSeries.add(new Month(1, 2017), 135);
		timeSeries.add(new Month(5, 2017), 103);
		timeSeriesCollection.addSeries(timeSeries);

		return timeSeriesCollection;
	}

	private CategoryDataset createCategoryDataset() {
		DefaultCategoryDataset categoryDataset = null;
		categoryDataset = new DefaultCategoryDataset();
		
		categoryDataset.addValue(5, "10班", "201511");
		categoryDataset.addValue(7, "10班", "201601");
		categoryDataset.addValue(8, "10班", "201605");
		categoryDataset.addValue(6, "10班", "201607");
		categoryDataset.addValue(4, "10班", "201611");
		categoryDataset.addValue(3, "10班", "Mock1");
		categoryDataset.addValue(2, "10班", "Mock2");

		categoryDataset.addValue(1, "加拿大高中班", "201511");
		categoryDataset.addValue(2, "加拿大高中班", "201601");
		categoryDataset.addValue(3, "加拿大高中班", "201605");
		categoryDataset.addValue(2, "加拿大高中班", "201607");
		categoryDataset.addValue(5, "加拿大高中班", "201611");
		categoryDataset.addValue(4, "加拿大高中班", "Mock1");
		categoryDataset.addValue(1, "加拿大高中班", "Mock2");

		/*categoryDataset.addValue(169, CATEGORY_GENERAL_ZH_TW, "201511");
		categoryDataset.addValue(155, CATEGORY_GENERAL_ZH_TW, "201601");
		categoryDataset.addValue(143, CATEGORY_GENERAL_ZH_TW, "201605");
		categoryDataset.addValue(172, CATEGORY_GENERAL_ZH_TW, "201607");
		categoryDataset.addValue(132, CATEGORY_GENERAL_ZH_TW, "201611");
		categoryDataset.addValue(143, CATEGORY_GENERAL_ZH_TW, "壹模");
		categoryDataset.addValue(126, CATEGORY_GENERAL_ZH_TW, "二模");
		
		categoryDataset.addValue(101, CATEGORY_MATH_ZH_TW, "201511");
		categoryDataset.addValue(129, CATEGORY_MATH_ZH_TW, "201601");
		categoryDataset.addValue(121, CATEGORY_MATH_ZH_TW, "201605");
		categoryDataset.addValue(111, CATEGORY_MATH_ZH_TW, "201607");
		categoryDataset.addValue(98, CATEGORY_MATH_ZH_TW, "201611");
		categoryDataset.addValue(74, CATEGORY_MATH_ZH_TW, "壹模");
		categoryDataset.addValue(68, CATEGORY_MATH_ZH_TW, "二模");

		categoryDataset.addValue(111, CATEGORY_SCIENCE_ZH_TW, "201511");
		categoryDataset.addValue(119, CATEGORY_SCIENCE_ZH_TW, "201601");
		categoryDataset.addValue(131, CATEGORY_SCIENCE_ZH_TW, "201605");
		categoryDataset.addValue(101, CATEGORY_SCIENCE_ZH_TW, "201607");
		categoryDataset.addValue(78, CATEGORY_SCIENCE_ZH_TW, "201611");
		categoryDataset.addValue(114, CATEGORY_SCIENCE_ZH_TW, "壹模");
		categoryDataset.addValue(98, CATEGORY_SCIENCE_ZH_TW, "二模");

		categoryDataset.addValue(191, CATEGORY_CHINESE_ZH_TW, "201511");
		categoryDataset.addValue(189, CATEGORY_CHINESE_ZH_TW, "201601");
		categoryDataset.addValue(176, CATEGORY_CHINESE_ZH_TW, "201605");
		categoryDataset.addValue(180, CATEGORY_CHINESE_ZH_TW, "201607");
		categoryDataset.addValue(169, CATEGORY_CHINESE_ZH_TW, "201611");
		categoryDataset.addValue(170, CATEGORY_CHINESE_ZH_TW, "壹模");
		categoryDataset.addValue(149, CATEGORY_CHINESE_ZH_TW, "二模");

		categoryDataset.addValue(179, CATEGORY_ENGLISH_ZH_TW, "201511");
		categoryDataset.addValue(150, CATEGORY_ENGLISH_ZH_TW, "201601");
		categoryDataset.addValue(149, CATEGORY_ENGLISH_ZH_TW, "201605");
		categoryDataset.addValue(160, CATEGORY_ENGLISH_ZH_TW, "201607");
		categoryDataset.addValue(139, CATEGORY_ENGLISH_ZH_TW, "201611");
		categoryDataset.addValue(120, CATEGORY_ENGLISH_ZH_TW, "壹模");
		categoryDataset.addValue(149, CATEGORY_ENGLISH_ZH_TW, "二模");*/

		return categoryDataset;
	}

	private XYDataset createXYDataset() {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		XYSeries xySeries = new XYSeries(CATEGORY_GENERAL_ZH_CN);
		xySeries.add(179, 201511);
		xySeries.add(169, 201511);
		xySeries.add(155, 201601);
		xySeries.add(143, 201605);
		xySeries.add(172, 201607);
		xySeries.add(132, 201611);
		xySeries.add(143, 201701);
		xySeries.add(126, 201705);
		return xySeriesCollection;
	}
	
	public final static String TITLE_ZH_CN = "数学考试成绩年级名次趋势图";
	public final static String TITLE_ZH_TW = "數學考試成績年級名次趨勢圖";
	public final static String TITLE_EN_US = "Grade Trend Chart";

	public final static String TITLE_X_ZH_CN = "考试日期";
	public final static String TITLE_X_ZH_TW = "考試日期";
	public final static String TITLE_X_EN_US = "Examination";
	
	public final static String TITLE_Y_ZH_CN = "名次";
	public final static String TITLE_Y_ZH_TW = "名次";
	public final static String TITLE_Y_EN_US = "Ranking";

	public final static String CATEGORY_GENERAL_ZH_CN = "总分";
	public final static String CATEGORY_GENERAL_ZH_TW = "總分";
	public final static String CATEGORY_GENERAL_EN_US = "General";
	
	public final static String CATEGORY_MATH_ZH_CN = "数学";
	public final static String CATEGORY_MATH_ZH_TW = "數學";
	public final static String CATEGORY_MATH_EN_US = "Math";
	
	public final static String CATEGORY_CHINESE_ZH_CN = "语文";
	public final static String CATEGORY_CHINESE_ZH_TW = "語文";
	public final static String CATEGORY_CHINESE_EN_US = "Chinese";

	public final static String CATEGORY_ENGLISH_ZH_CN = "英语";
	public final static String CATEGORY_ENGLISH_ZH_TW = "英語";
	public final static String CATEGORY_ENGLISH_EN_US = "English";

	public final static String CATEGORY_SCIENCE_ZH_CN = "理综";
	public final static String CATEGORY_SCIENCE_ZH_TW = "理綜";
	public final static String CATEGORY_SCIENCE_EN_US = "Science";

	public static void main(String[] args) throws IOException {
//		StringBuffer sb = new StringBuffer("http://zh_cn.www.com/fsdfdsfds");
//		int dotIndexOf = sb.indexOf(".");
//		System.out.println(dotIndexOf==-1?null:sb.delete(dotIndexOf, sb.length()).delete(0, sb.indexOf("://")+3).toString());
		
//		new Test().test();
		System.out.println(StringUtils.parseLocaleString("xxx"));
//		System.out.println(Calendar.getInstance().get(Calendar.MONTH));
//		System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH));
	}

}
