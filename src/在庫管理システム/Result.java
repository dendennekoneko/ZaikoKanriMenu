package 在庫管理システム;

import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//ウィンドウのひな型
public class Result extends Windows {
	
	private static final long serialVersionUID = 1L;
	
	Label l_1,l_2;
	
	List<String>  columns = new ArrayList<String>(Arrays.asList("Apple", "Orange", "Melon"));
	private String[] column = {"COUNTRY", "WIN", "LOST"};

	//コンストラクタ(画面構成を定義)
	Result(){
		setTitle("問い合わせ在庫 一覧表示");
		setBounds(400,300,500,370);										//setBoundsで表示位置と大きさ指定
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		JTable table = new JTable(tableModel);
		tableModel.addColumn(Zaiko.result_Column);

	    add(table); 
		
	}
	
	
	//パーツを生成、初期値を設定する
	public void partsInitialize() {												

	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		
	}


}
