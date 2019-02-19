package 在庫管理システム;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//在庫メニュー画面。Windowsクラスの子供
public class Zaiko_Menu extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_Search,b_Syukka,b_Nyuuka;															
	
	//コンストラクタ(画面構成を定義)
	Zaiko_Menu(){
		setTitle("在庫メニュー");
		setBounds(400,300,300,230);										//setBoundsで表示位置と大きさ指定		
		getContentPane().setLayout(null);									//パーツ配置設定
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		listenerSet();																	//初期化④
	}

	//パーツを生成、初期値を設定する
	public void partsInitialize() {												
		b_Search = new Button("在庫検索");
		b_Syukka = new Button("出荷");
		b_Nyuuka = new Button("入荷");
	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		getContentPane().add(b_Search);
		getContentPane().add(b_Syukka);
		getContentPane().add(b_Nyuuka);
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		b_Search.setBounds(65,30,150,20);
		b_Syukka.setBounds(65,70,150,20);
		b_Nyuuka.setBounds(65,110,150,20);
	}
	
	public void listenerSet() {													//リスナー設定用メソッド
		b_Search.addActionListener(this);
		b_Syukka.addActionListener(this);
		b_Nyuuka.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {					//ボタンが押された時の処理(ActionListenerのオバライ)
		if(e.getSource()==b_Search) {
			new Zaiko_Search();
		}else if(e.getSource()==b_Syukka) {
			new Zaiko_Syukka();
		}else if(e.getSource()==b_Nyuuka) {
			new Zaiko_Nyuuka();
		}
	}
}
