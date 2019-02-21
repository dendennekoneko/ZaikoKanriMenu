package �݌ɊǗ��V�X�e��;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;


//�݌Ɍ�����ʁBWindows�N���X�̎q��
public class Zaiko_Search extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_go;															
	Label l_1,l_2;
	static Label l_error_syu;
	JTextField t_shocd;
	JTextField t_shoname;
	
	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_Search(){
		getContentPane().setLayout(null);								//�p�[�c�z�u�ݒ�(�s�v����)
		setTitle("�݌ɖ₢���킹");
		setBounds(300,250,350,230);										//setBounds�ŕ\���ʒu�Ƒ傫���w��		
		setLocationRelativeTo(null);											//��ʒ����ɕ\��
		
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		listenerSet();																	//�������C
		setVisible(true);
	}

	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												
		l_1 = new Label("���i�b�c");
		t_shocd = new JTextField();
		l_2 = new Label("���i��");
		t_shoname = new JTextField();
		b_go = new Button("���s");
		l_error_syu = new Label("");
	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		getContentPane().add(l_1);
		getContentPane().add(t_shocd);
		getContentPane().add(l_2);
		getContentPane().add(t_shoname);
		getContentPane().add(b_go);
		getContentPane().add(l_error_syu);
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		l_1.setBounds(70,30,50,20);
		t_shocd.setBounds(140,30,50,20);
		l_2.setBounds(70,70,50,20);
		t_shoname.setBounds(140,70,100,20);
		b_go.setBounds(70,110,150,20);
		l_error_syu.setBounds(70,140,250,20);
	}
	
	public void listenerSet() {													//���X�i�[�ݒ�p���\�b�h
		b_go.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
	        l_error_syu.setText("");
			textScan();
			Search_SQL sq = new Search_SQL();
	        sq.string_check_ok = false;
	        sq.int_check_ok = false;
			sq.getSql();															//SQL���ʕ\��
		} catch (SQLException e1) {
			// TODO �����������ꂽ catch �u���b�N
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
				l_error_syu.setText("���݂��Ȃ����i�b�c�����͂���Ă��܂��B");
				Search_SQL.get_shocd = 0;
			}
		}
		//���i���e�L�X�g�t�B�[���h�ɕ����񂪓��͂���Ă��邩�`�F�b�N
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
