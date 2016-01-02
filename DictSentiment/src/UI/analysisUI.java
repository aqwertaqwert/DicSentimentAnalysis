package UI;

import java.awt.Container;

import javax.swing.*;

import dicSentiment.sentiment;
import fengCi.fengCi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class analysisUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton confrombutton = new JButton("analysis");
	private final JLabel lbAnalysis = new JLabel("语料：");
	private final JLabel lbCode = new JLabel("语料格式：");
	private final JLabel lbFC = new JLabel("分词：");
	private final JLabel lbSentiment = new JLabel("情感：");
	private JTextField jtAnalysis = new JTextField("保存路径");
	private JTextField jtFC    = new JTextField("保存路径");
	private JTextField jtSentiment  = new JTextField("保存路径");
	private JTextField jtCode  = new JTextField("文件格式（UTF8/GB2312）");
	BackgroundPanel bgp;
	java.net.URL imgURL = analysisUI.class.getResource("robotImage.jpg");

	
	public analysisUI(){
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	设置背景图片
	public void drawBack(){
		Container ct=this.getContentPane();
		getContentPane().setLayout(null);
		
		//在这里照片可以看到测试结果。	
		ImageIcon image1 = new ImageIcon(imgURL);
		bgp=new BackgroundPanel(image1.getImage());
		bgp.setBounds(0,0,530,288);
		ct.add(bgp);
	
	}
	
	public void init() {
		Container cop = this.getContentPane();
		cop.setLayout(null);
//	设置位置边框	
		lbAnalysis.setBounds(100, 33, 51, 20);
		cop.add(lbAnalysis);			
		lbCode.setBounds(100, 63, 78, 20);
		cop.add(lbCode);
		lbFC.setBounds(100, 116, 100, 20);
		cop.add(lbFC);
		lbSentiment.setBounds(100, 146, 100, 20);
		cop.add(lbSentiment);

		jtAnalysis.setBounds(171, 33, 170, 20);
		cop.add(jtAnalysis);	
		jtCode.setBounds(170, 63, 170, 20);
		cop.add(jtCode);
		jtFC.setBounds(171, 116, 170, 20);
		cop.add(jtFC);
		jtSentiment.setBounds(171, 146, 170, 20);
		cop.add(jtSentiment);


//		设置analysis按钮
		confrombutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {					
					String analysis = jtAnalysis.getText();
					String FC = jtFC.getText();
					String code = jtCode.getText();
					String sentiment = jtSentiment.getText();
					File analysisFile = new File(analysis);
					if (analysisFile.exists()&&(code.equals("UTF8")||code.equals("GB2312"))) {
						//开始分词，输入想分词的文件以及要保存的文件
						JOptionPane.showMessageDialog(null, "开始分析！=.=");
						fengCi jd = new fengCi(analysis,FC,code);
						jd.getFengci();
						//开始sentiment
						sentiment a = new sentiment();
						a.readDoc(FC,sentiment);
						
					}else{
						JOptionPane.showMessageDialog(null, "请确定输入正确！=.=");
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			}
				
		});
		
		
		confrombutton.setBounds(171, 217, 100, 34);
		cop.add(confrombutton);
		//添加背景图片
		drawBack();
	}

	public static void main(String[] args) {
		analysisUI onelogin = new analysisUI();
		onelogin.setTitle("Sentiment Analysis");
		onelogin.setSize(500, 300);
		onelogin.setResizable(false);
		onelogin.setLocation(400, 200);
		onelogin.setVisible(true);
		
	}


}
