package 在庫管理システム;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

//ウィンドウのひな型
public abstract class Windows extends JFrame {
	
	private static final long serialVersionUID = 1L;

	//コンストラクタ(画面構成を定義)
	Windows(){
		setVisible(true);															//フレーム有効化(定型文として覚える)　※
	}
	
	//パーツを生成、初期値を設定する
	public abstract void partsInitialize();
	
	//パーツを順番にフレームに配置する
	public abstract void partsSet();
	
	//パーツの配置を手動設定
	public abstract void partsLayout();

	//ウィンドウが閉じられた時の処理(クラス名以外、定型文)
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	        System.out.println("windowActivated");
	    }
	}
}
