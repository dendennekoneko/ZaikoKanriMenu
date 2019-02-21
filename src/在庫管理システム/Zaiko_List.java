package 在庫管理システム;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

//在庫一覧画面。　Windowsクラスの子供
public class Zaiko_List extends Windows implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane sp;
	JPanel p;
	JLabel l_repaint;
	
	//再読み込みボタン
	Button b_repaint;																		
	
	//一覧表が現在立ち上がっているかどうか。Search_SQL内で必要
	public static boolean paint_now = false;
	
	//JTableで使うカラム名
	private String[] column = {"商品ＣＤ", "商品名", "在庫数"};
	//JTableで使うレコード格納用二次元配列
	String[][] table_Column = new String[Search_SQL.result_Column.size()][3];

	//コンストラクタ(画面構成を定義)
	Zaiko_List(){
		
		//setLayout(null);								//パーツ配置設定(不要かも)
		setTitle("在庫一覧");
		setBounds(400,300,500,300);										//setBoundsで表示位置と大きさ指定
		setLocationRelativeTo(null);											//画面中央に表示
		
		//再描画ボタン用************************************************
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		listenerSet();																	//初期化④
		//**********************************************************
		
		//Windowsクラスからオーバーライド
		addWindowListener(new WinAda());
		
		//描画
		paint_window();
		
		//一覧表が立ち上がっている状態
	    paint_now = true;
	}
	
	//各種コンポーネント描画
	public void paint_window() {
		
		//再描画通知ラベルを生成
		l_repaint = new JLabel("");
		getContentPane().add(l_repaint);
		l_repaint.setBounds(90,220,250,20);
		
		//TableModelに実際の表配列を格納することになる
		tableModel = new DefaultTableModel(column,0);
		//JTable型tableはただの型のようなもの
		table = new JTable(tableModel);
		

		
		//カラム幅の調整
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(20);
		TableColumn col1 = table.getColumnModel().getColumn(1);
		col1.setPreferredWidth(50);
		TableColumn col2 = table.getColumnModel().getColumn(2);
		col2.setPreferredWidth(20);
		
		//中央ぞろえ
	    DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
	    tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);   
	    
	    TableColumn col_center = table.getColumnModel().getColumn(0);
	    col_center.setCellRenderer(tableCellRenderer);
	    TableColumn col_center2 = table.getColumnModel().getColumn(2);
	    col_center2.setCellRenderer(tableCellRenderer);

		//SQL結果配列取得→table_Columnへ格納→tableModelに表を追加まで
		getTable();
		
		//スクロールバーが必要なら
	    sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(350,150));
	    
	    //見栄えのおおもと
	    p = new JPanel();

	    p.add(sp);
	    getContentPane().add(p, BorderLayout.CENTER);
	    
	    //実際に表示
		setVisible(true);	
	}
	
	//Search_SQLのSQL結果をJTableに代入する
	public void getTable() {
		//JTable描画用の二次元配列table_ColumnにSQL結果配列を代入
		for(int i = 0 ; i < Search_SQL.result_Column.size(); i++) {
			for(int j = 0;j < 3;j++) {
				table_Column[i][j] = Search_SQL.result_Column.get(i).get(j); 
			}
		}
		
		//二次元配列table_ColumnをtableModelに追加(一行ずつ可能)
		for(int i = 0 ; i < Search_SQL.result_Column.size() ; i++){
		      tableModel.addRow(table_Column[i]);
		}
	}
	
//再読み込みボタン生成**************************************
	//パーツを生成、初期値を設定する
	public void partsInitialize() {												
		b_repaint = new Button("再読み込み");
	}
	
	//パーツを順番にフレームに配置する
	public void partsSet() {														
		getContentPane().add(b_repaint);
	}
	
	//パーツの配置を手動設定
	public void partsLayout() {												
		b_repaint.setBounds(90,170,150,20);
	}

	//リスナー設定用メソッド
	public void listenerSet() {												
		b_repaint.addActionListener(this);
	}
//*****************************************************
	
	//再読み込みボタン押された時の処理
	public void actionPerformed(ActionEvent arg0) {
		try {
			//JTableの二次元配列(TableColumn)を初期化する
			tableModel.setRowCount(0);
			
			//最新の在庫数を取得する(SQL文はstaticで直前の検索条件を保持)
			new Search_SQL().listGo();

			//テーブル再生成
			getTable();
			
			l_repaint.setText("最新の在庫数を読み込みました");
			
			//再描画
			repaint();
			
			//在庫一覧表のJTable配列に代入できたため、二次元配列をクリアする
			Search_SQL.result_Column.clear();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//ウィンドウが閉じられた時の処理、オーバーライド←そもそも親クラスにこのメソッド必要ない・・・
	class WinAda extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	//一覧表ウィンドウが立ち上がっていない状態
	        paint_now = false;
	    }
	}
}
