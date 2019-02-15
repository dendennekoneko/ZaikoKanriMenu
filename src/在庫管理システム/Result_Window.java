package 在庫管理システム;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

//ウィンドウのひな型
public abstract class Result_Window extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Label l_1,l_2;
	static Label l_error;
	JTextField t_shocd;
	JTextField t_shoname;
	
	//コンストラクタ(画面構成を定義)
	Result_Window(){
		setTitle("問い合わせ在庫 一覧表示");
		setBounds(400,300,500,370);										//setBoundsで表示位置と大きさ指定
	    setBackground(new Color(140,236,235));					//背景色指定
		setVisible(true);															//フレーム有効化(定型文として覚える)
		partsInitialize();																//初期化①
		partsSet();																		//初期化②
		partsLayout();																	//初期化③
		addWindowListener(new WinAda());	
	}

	//パーツを生成、初期値を設定する
	public void partsInitialize() {												
		l_1 = new Label("商品ＣＤ");
		l_2 = new Label("商品名");

	}
	
	public void partsSet() {														//パーツを順番にフレームに配置する
		getContentPane().add(l_1);
		getContentPane().add(l_2);
	}
	
	public void partsLayout() {												//パーツの配置を手動設定
		l_1.setBounds(50,30,50,20);
		l_2.setBounds(50,70,50,20);
	}
	
	
	
	
	//ウィンドウが閉じられた時の処理(クラス名以外、定型文)
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	        System.out.println("windowActivated");
	    }
	}
	
}
