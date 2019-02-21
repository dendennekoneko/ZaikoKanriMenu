package �݌ɊǗ��V�X�e��;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;


//�݌ɓ��׉�ʁBWindows�N���X�̎q��
public class Zaiko_Nyuuka extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_go;															
	Label l_1,l_2,l_3;
	static Label l_error_n,l_result;
	JTextField t_shocd;
	JTextField t_shoname;
	JTextField t_nyusu;
	
	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_Nyuuka(){
		getContentPane().setLayout(null);								//�p�[�c�z�u�ݒ�(�s�v����)
		setTitle("����");
		setBounds(300,250,350,270);										//setBounds�ŕ\���ʒu�Ƒ傫���w��		
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
		l_3 = new Label("���א�");
		t_nyusu = new JTextField();
		b_go = new Button("���s");
		l_error_n = new Label("");
		l_result = new Label("");
	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		getContentPane().add(l_1);
		getContentPane().add(t_shocd);
		getContentPane().add(l_2);
		getContentPane().add(t_shoname);
		getContentPane().add(l_3);
		getContentPane().add(t_nyusu);
		getContentPane().add(b_go);
		getContentPane().add(l_error_n);
		getContentPane().add(l_result);
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		l_1.setBounds(70,30,50,20);
		t_shocd.setBounds(140,30,50,20);
		l_2.setBounds(70,70,50,20);
		t_shoname.setBounds(140,70,100,20);
		l_3.setBounds(70,110,50,20);
		t_nyusu.setBounds(140,110,100,20);
		b_go.setBounds(70,150,150,20);
		l_error_n.setBounds(70,180,250,20);
		l_result.setBounds(70,200,250,20);
	}
	
	public void listenerSet() {													//���X�i�[�ݒ�p���\�b�h
		b_go.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
	        l_error_n.setText("");
	        l_result.setText("");
			textScan();
			suryoScan();
			In_SQL sq_nyuuka = new In_SQL();
	        sq_nyuuka.string_check_ok = false;
	        sq_nyuuka.int_check_ok = false;
			sq_nyuuka.getSql();															//SQL���ʕ\��
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void suryoScan() {
		//�o�א��e�L�X�g�t�B�[���h�ɐ��l�������ꂽ�`�F�b�N
		boolean t_nyusu_is_num;
		t_nyusu_is_num = isNumber(t_nyusu.getText());
		
		//�`�F�b�N���ꂽ�^�U�l�ɂ���āA�O���[�o���ϐ�get_syusucd�ɐ��l����
		if(t_nyusu_is_num) {
			In_SQL.get_nyusu = Integer.parseInt(t_nyusu.getText());
		}else {
			//���i�b�c���󔒂̏ꍇ�i����j
			if(t_nyusu.getText().equals("")) {
				l_error_n.setText("���א����󗓂ɂȂ��Ă��܂�");
				In_SQL.get_nyusu = 0;
			//���i�b�c�����l�ł��󔒂ł������ꍇ�i�ُ�j
			}else {
				In_SQL.get_nyusu = 0;
			}
		}
	}
	
	public void textScan() {
		//���i�b�c�e�L�X�g�t�B�[���h�ɐ��l�������ꂽ�`�F�b�N
		boolean t_shocd_is_num;
		t_shocd_is_num = isNumber(t_shocd.getText());
		
		//�`�F�b�N���ꂽ�^�U�l�ɂ���āA�O���[�o���ϐ�get_shocd�ɐ��l����
		if(t_shocd_is_num) {
			In_SQL.get_shocd = Integer.parseInt(t_shocd.getText());
		}else {
			//���i�b�c���󔒂̏ꍇ�i����j
			if(t_shocd.getText().equals("")) {
				In_SQL.get_shocd = 0;
			//���i�b�c�����l�ł��󔒂ł������ꍇ�i�ُ�j
			}else {
				l_error_n.setText("���݂��Ȃ����i�b�c�����͂���Ă��܂��B");
				In_SQL.get_shocd = 0;
			}
		}
		//���i���͖������ő��
		In_SQL.get_shoname = t_shoname.getText();
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

