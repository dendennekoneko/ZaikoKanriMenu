package 在庫管理システム;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//在庫一覧画面。　Windowsクラスの子供
public class Zaiko_List extends Windows {
	
	private static final long serialVersionUID = 1L;
	
	private String[] column = {"商品ＣＤ", "商品名", "在庫数"};
	String[][] table_Column = new String[SQL.result_Column.size()][3];

	//コンストラクタ(画面構成を定義)
	Zaiko_List(){
		setTitle("問い合わせ在庫 一覧表示");
		setBounds(400,300,500,370);										//setBoundsで表示位置と大きさ指定
		
		DefaultTableModel tableModel = new DefaultTableModel(column,0);
		JTable table = new JTable(tableModel);
		for(int i = 0 ; i < SQL.result_Column.size(); i++) {
			for(int j = 0;j < 3;j++) {
				table_Column[i][j] = SQL.result_Column.get(i).get(j); 
			}
		}
		
		for(int i = 0 ; i < SQL.result_Column.size() ; i++){
		      tableModel.addRow(table_Column[i]);
		}
		
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(300,150));
		
	    JPanel p = new JPanel();
	    p.add(sp);
	    
	    getContentPane().add(p, BorderLayout.CENTER);
	}
}
