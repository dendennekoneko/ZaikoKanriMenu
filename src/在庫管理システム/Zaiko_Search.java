package 在庫管理システム;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;


//在庫検索画面。Windowsクラスの子供
public class Zaiko_Search extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_go;															
	Label l_1,l_2;
	static Label l_error;
	JTextField t_shocd;
	JTextField t_shoname;
	
	//コンストラクタ(画面構成を定義)
	Zaiko_Search(){
		setTitle("在庫問い合わせ");
		setBounds(400,300,500,370);										//setBoundsで表示位置と大きさ指定		
		
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		listenerSet();																	//初期化④
	}

	//パーツを生成、初期値を設定する
	public void partsInitialize() {												
		l_1 = new Label("商品ＣＤ");
		t_shocd = new JTextField();
		//t_shocd.setFont(new Font("SansSerif", Font.ITALIC, 15));
		l_2 = new Label("商品名");
		t_shoname = new JTextField();
		b_go = new Button("実行");
		l_error = new Label("");
	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		getContentPane().add(l_1);
		getContentPane().add(t_shocd);
		getContentPane().add(l_2);
		getContentPane().add(t_shoname);
		getContentPane().add(b_go);
		getContentPane().add(l_error);
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		l_1.setBounds(50,30,50,20);
		t_shocd.setBounds(120,30,50,20);
		l_2.setBounds(50,70,50,20);
		t_shoname.setBounds(120,70,100,20);
		b_go.setBounds(50,120,150,20);
		l_error.setBounds(50,150,250,20);
	}
	
	public void listenerSet() {													//リスナー設定用メソッド
		b_go.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			textScan();
			SQL sq = new SQL();
			sq.getSql();															//SQL結果表示
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}
	
	public void textScan() {
		
		//商品ＣＤテキストフィールドに数値が入れられたチェック
		boolean t_shocd_is_num;
		t_shocd_is_num = isNumber(t_shocd.getText());
		
		//チェックされた真偽値によって、グローバル変数get_shocdに数値を代入
		if(t_shocd_is_num) {
			SQL.get_shocd = Integer.parseInt(t_shocd.getText());
		}else {
			SQL.get_shocd = 0;
		}
		//商品名テキストフィールドに文字列が入力されているかチェック
		SQL.get_shoname = t_shoname.getText();
	}
	
	//商品ＣＤテキストフィールドに数値が入力されたか例外処理でチェック
	public boolean isNumber(String num) {
	    try {
	        Integer.parseInt(num);
	        return true;
	        } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
