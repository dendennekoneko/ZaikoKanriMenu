package 在庫管理システム;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;


//在庫出荷画面。Windowsクラスの子供
public class Zaiko_Syukka extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_go;															
	Label l_1,l_2,l_3;
	static Label l_error,l_result;
	JTextField t_shocd;
	JTextField t_shoname;
	JTextField t_syusu;
	
	//コンストラクタ(画面構成を定義)
	Zaiko_Syukka(){
		getContentPane().setLayout(null);								//パーツ配置設定(不要かも)
		setTitle("在庫出荷");
		setBounds(300,250,350,350);										//setBoundsで表示位置と大きさ指定		
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		listenerSet();																	//初期化④
	}

	//パーツを生成、初期値を設定する
	public void partsInitialize() {												
		l_1 = new Label("商品ＣＤ");
		t_shocd = new JTextField();
		l_2 = new Label("商品名");
		t_shoname = new JTextField();
		l_3 = new Label("出荷数");
		t_syusu = new JTextField();
		b_go = new Button("実行");
		l_error = new Label("");
		l_result = new Label("");
	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		getContentPane().add(l_1);
		getContentPane().add(t_shocd);
		getContentPane().add(l_2);
		getContentPane().add(t_shoname);
		getContentPane().add(l_3);
		getContentPane().add(t_syusu);
		getContentPane().add(b_go);
		getContentPane().add(l_error);
		getContentPane().add(l_result);
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		l_1.setBounds(70,30,50,20);
		t_shocd.setBounds(140,30,50,20);
		l_2.setBounds(70,70,50,20);
		t_shoname.setBounds(140,70,100,20);
		l_3.setBounds(70,110,50,20);
		t_syusu.setBounds(140,110,100,20);
		b_go.setBounds(70,150,150,20);
		l_error.setBounds(50,220,250,20);
		l_result.setBounds(50,240,250,20);
	}
	
	public void listenerSet() {													//リスナー設定用メソッド
		b_go.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
	        l_error.setText("");
	        l_result.setText("");
			textScan();
			suryoScan();
			Out_SQL sq_syukka = new Out_SQL();
	        sq_syukka.string_check_ok = false;
	        sq_syukka.int_check_ok = false;
			sq_syukka.getSql();															//SQL結果表示
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void suryoScan() {
		//出荷数テキストフィールドに数値が入れられたチェック
		boolean t_syusu_is_num;
		t_syusu_is_num = isNumber(t_syusu.getText());
		
		//チェックされた真偽値によって、グローバル変数get_syusucdに数値を代入
		if(t_syusu_is_num) {
			Out_SQL.get_syusu = Integer.parseInt(t_syusu.getText());
		}else {
			//商品ＣＤが空白の場合（正常）
			if(t_syusu.getText().equals("")) {
				l_error.setText("出荷数が空欄になっています");
				Out_SQL.get_syusu = 0;
			//商品ＣＤが数値でも空白でも無い場合（異常）
			}else {
				l_error.setText("数量を正しく入力してください");
				Out_SQL.get_syusu = 0;
			}
		}
	}
	
	public void textScan() {
		//商品ＣＤテキストフィールドに数値が入れられたチェック
		boolean t_shocd_is_num;
		t_shocd_is_num = isNumber(t_shocd.getText());
		
		//チェックされた真偽値によって、グローバル変数get_shocdに数値を代入
		if(t_shocd_is_num) {
			Out_SQL.get_shocd = Integer.parseInt(t_shocd.getText());
		}else {
			//商品ＣＤが空白の場合（正常）
			if(t_shocd.getText().equals("")) {
				Out_SQL.get_shocd = 0;
			//商品ＣＤが数値でも空白でも無い場合（異常）
			}else {
				l_error.setText("存在しない商品ＣＤが入力されています。");
				Out_SQL.get_shocd = 0;
			}
		}
		//商品名は無条件で代入
		Out_SQL.get_shoname = t_shoname.getText();
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
