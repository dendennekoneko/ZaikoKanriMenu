package �݌ɊǗ��V�X�e��;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;


//�݌ɏo�׉�ʁBWindows�N���X�̎q��
public class Zaiko_Syukka extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_go;															
	Label l_1,l_2,l_3;
	static Label l_error,l_result;
	JTextField t_shocd;
	JTextField t_shoname;
	JTextField t_syusu;
	
	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_Syukka(){
		getContentPane().setLayout(null);								//�p�[�c�z�u�ݒ�(�s�v����)
		setTitle("�݌ɏo��");
		setBounds(300,250,350,350);										//setBounds�ŕ\���ʒu�Ƒ傫���w��		
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		listenerSet();																	//�������C
	}

	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												
		l_1 = new Label("���i�b�c");
		t_shocd = new JTextField();
		l_2 = new Label("���i��");
		t_shoname = new JTextField();
		l_3 = new Label("�o�א�");
		t_syusu = new JTextField();
		b_go = new Button("���s");
		l_error = new Label("");
		l_result = new Label("");
	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
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
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
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
	
	public void listenerSet() {													//���X�i�[�ݒ�p���\�b�h
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
			sq_syukka.getSql();															//SQL���ʕ\��
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void suryoScan() {
		//�o�א��e�L�X�g�t�B�[���h�ɐ��l�������ꂽ�`�F�b�N
		boolean t_syusu_is_num;
		t_syusu_is_num = isNumber(t_syusu.getText());
		
		//�`�F�b�N���ꂽ�^�U�l�ɂ���āA�O���[�o���ϐ�get_syusucd�ɐ��l����
		if(t_syusu_is_num) {
			Out_SQL.get_syusu = Integer.parseInt(t_syusu.getText());
		}else {
			//���i�b�c���󔒂̏ꍇ�i����j
			if(t_syusu.getText().equals("")) {
				l_error.setText("�o�א����󗓂ɂȂ��Ă��܂�");
				Out_SQL.get_syusu = 0;
			//���i�b�c�����l�ł��󔒂ł������ꍇ�i�ُ�j
			}else {
				l_error.setText("���ʂ𐳂������͂��Ă�������");
				Out_SQL.get_syusu = 0;
			}
		}
	}
	
	public void textScan() {
		//���i�b�c�e�L�X�g�t�B�[���h�ɐ��l�������ꂽ�`�F�b�N
		boolean t_shocd_is_num;
		t_shocd_is_num = isNumber(t_shocd.getText());
		
		//�`�F�b�N���ꂽ�^�U�l�ɂ���āA�O���[�o���ϐ�get_shocd�ɐ��l����
		if(t_shocd_is_num) {
			Out_SQL.get_shocd = Integer.parseInt(t_shocd.getText());
		}else {
			//���i�b�c���󔒂̏ꍇ�i����j
			if(t_shocd.getText().equals("")) {
				Out_SQL.get_shocd = 0;
			//���i�b�c�����l�ł��󔒂ł������ꍇ�i�ُ�j
			}else {
				l_error.setText("���݂��Ȃ����i�b�c�����͂���Ă��܂��B");
				Out_SQL.get_shocd = 0;
			}
		}
		//���i���͖������ő��
		Out_SQL.get_shoname = t_shoname.getText();
	}
	
	//���i�b�c�e�L�X�g�t�B�[���h�ɐ��l�����͂��ꂽ����O�����Ń`�F�b�N
	public boolean isNumber(String num) {
	    try {
	        Integer.parseInt(num);
	        return true;
	        } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
