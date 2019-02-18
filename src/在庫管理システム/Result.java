package 在庫管理システム;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//ウィンドウのひな型
public class Result extends Result_Window {
	
	private static final long serialVersionUID = 1L;
	
	Label l_1,l_2;
	
	List<String>  columns = new ArrayList<String>(Arrays.asList("Apple", "Orange", "Melon"));
	private String[] column = {"COUNTRY", "WIN", "LOST"};

	
	//コンストラクタ(画面構成を定義)
	Result(){
		setTitle("問い合わせ在庫 一覧表示");
		setBounds(400,300,500,370);										//setBoundsで表示位置と大きさ指定
	    setBackground(new Color(140,236,235));					//背景色指定
		setVisible(true);															//フレーム有効化(定型文として覚える)
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		addWindowListener(new WinAda());	
		
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		JTable table = new JTable(tableModel);
		tableModel.addColumn(Zaiko.result_Column);

	    add(table); 
		
	}
	
	
	//パーツを生成、初期値を設定する
	public void partsInitialize() {												

	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		getContentPane().add(l_1);
		getContentPane().add(l_2);
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		l_1.setBounds(50,30,50,20);
		l_2.setBounds(50,70,50,20);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
}
