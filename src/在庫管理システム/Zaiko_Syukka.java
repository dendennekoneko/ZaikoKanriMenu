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
	Label l_1,l_2;
	static Label l_error;
	JTextField t_shocd;
	JTextField t_shoname;
	
	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_Syukka(){
		setTitle("�݌ɏo��");
		setBounds(400,300,500,370);										//setBounds�ŕ\���ʒu�Ƒ傫���w��		
		
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
		b_go = new Button("���s");
		l_error = new Label("");
	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		getContentPane().add(l_1);
		getContentPane().add(t_shocd);
		getContentPane().add(l_2);
		getContentPane().add(t_shoname);
		getContentPane().add(b_go);
		getContentPane().add(l_error);
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		l_1.setBounds(50,30,50,20);
		t_shocd.setBounds(120,30,50,20);
		l_2.setBounds(50,70,50,20);
		t_shoname.setBounds(120,70,100,20);
		b_go.setBounds(50,120,150,20);
		l_error.setBounds(50,150,250,20);
	}
	
	public void listenerSet() {													//���X�i�[�ݒ�p���\�b�h
		b_go.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			textScan();
			InOut_SQL sq_syukka = new InOut_SQL();
	        l_error.setText("");
	        sq_syukka.string_check_ok = false;
	        sq_syukka.int_check_ok = false;
			sq_syukka.getSql();															//SQL���ʕ\��
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void textScan() {
		//���i�b�c�e�L�X�g�t�B�[���h�ɐ��l�������ꂽ�`�F�b�N
		boolean t_shocd_is_num;
		t_shocd_is_num = isNumber(t_shocd.getText());
		
		//�`�F�b�N���ꂽ�^�U�l�ɂ���āA�O���[�o���ϐ�get_shocd�ɐ��l����
		if(t_shocd_is_num) {
			Search_SQL.get_shocd = Integer.parseInt(t_shocd.getText());
		}else {
			//���i�b�c���󔒂̏ꍇ�i����j
			if(t_shocd.getText().equals("")) {
				Search_SQL.get_shocd = 0;
			//���i�b�c�����l�ł��󔒂ł������ꍇ�i�ُ�j
			}else {
				l_error.setText("���݂��Ȃ����i�b�c�����͂���Ă��܂��B");
				Search_SQL.get_shocd = 0;
			}
		}
		
		//���i���͖������ő��
		Search_SQL.get_shoname = t_shoname.getText();
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
