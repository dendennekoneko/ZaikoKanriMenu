package �݌ɊǗ��V�X�e��;

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

//�݌Ɉꗗ��ʁB�@Windows�N���X�̎q��
public class Zaiko_List extends Windows implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel tableModel;
	JTable table;
	JScrollPane sp;
	JPanel p;
	JLabel l_repaint;
	
	//�ēǂݍ��݃{�^��
	Button b_repaint;																		
	
	//�ꗗ�\�����ݗ����オ���Ă��邩�ǂ����BSearch_SQL���ŕK�v
	public static boolean paint_now = false;
	
	//JTable�Ŏg���J������
	private String[] column = {"���i�b�c", "���i��", "�݌ɐ�"};
	//JTable�Ŏg�����R�[�h�i�[�p�񎟌��z��
	String[][] table_Column = new String[Search_SQL.result_Column.size()][3];

	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_List(){
		
		//setLayout(null);								//�p�[�c�z�u�ݒ�(�s�v����)
		setTitle("�݌Ɉꗗ");
		setBounds(400,300,500,300);										//setBounds�ŕ\���ʒu�Ƒ傫���w��
		setLocationRelativeTo(null);											//��ʒ����ɕ\��
		
		//�ĕ`��{�^���p************************************************
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		listenerSet();																	//�������C
		//**********************************************************
		
		//Windows�N���X����I�[�o�[���C�h
		addWindowListener(new WinAda());
		
		//�`��
		paint_window();
		
		//�ꗗ�\�������オ���Ă�����
	    paint_now = true;
	}
	
	//�e��R���|�[�l���g�`��
	public void paint_window() {
		
		//�ĕ`��ʒm���x���𐶐�
		l_repaint = new JLabel("");
		getContentPane().add(l_repaint);
		l_repaint.setBounds(90,220,250,20);
		
		//TableModel�Ɏ��ۂ̕\�z����i�[���邱�ƂɂȂ�
		tableModel = new DefaultTableModel(column,0);
		//JTable�^table�͂����̌^�̂悤�Ȃ���
		table = new JTable(tableModel);
		

		
		//�J�������̒���
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(20);
		TableColumn col1 = table.getColumnModel().getColumn(1);
		col1.setPreferredWidth(50);
		TableColumn col2 = table.getColumnModel().getColumn(2);
		col2.setPreferredWidth(20);
		
		//�������낦
	    DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
	    tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);   
	    
	    TableColumn col_center = table.getColumnModel().getColumn(0);
	    col_center.setCellRenderer(tableCellRenderer);
	    TableColumn col_center2 = table.getColumnModel().getColumn(2);
	    col_center2.setCellRenderer(tableCellRenderer);

		//SQL���ʔz��擾��table_Column�֊i�[��tableModel�ɕ\��ǉ��܂�
		getTable();
		
		//�X�N���[���o�[���K�v�Ȃ�
	    sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(350,150));
	    
	    //���h���̂�������
	    p = new JPanel();

	    p.add(sp);
	    getContentPane().add(p, BorderLayout.CENTER);
	    
	    //���ۂɕ\��
		setVisible(true);	
	}
	
	//Search_SQL��SQL���ʂ�JTable�ɑ������
	public void getTable() {
		//JTable�`��p�̓񎟌��z��table_Column��SQL���ʔz�����
		for(int i = 0 ; i < Search_SQL.result_Column.size(); i++) {
			for(int j = 0;j < 3;j++) {
				table_Column[i][j] = Search_SQL.result_Column.get(i).get(j); 
			}
		}
		
		//�񎟌��z��table_Column��tableModel�ɒǉ�(��s���\)
		for(int i = 0 ; i < Search_SQL.result_Column.size() ; i++){
		      tableModel.addRow(table_Column[i]);
		}
	}
	
//�ēǂݍ��݃{�^������**************************************
	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												
		b_repaint = new Button("�ēǂݍ���");
	}
	
	//�p�[�c�����ԂɃt���[���ɔz�u����
	public void partsSet() {														
		getContentPane().add(b_repaint);
	}
	
	//�p�[�c�̔z�u���蓮�ݒ�
	public void partsLayout() {												
		b_repaint.setBounds(90,170,150,20);
	}

	//���X�i�[�ݒ�p���\�b�h
	public void listenerSet() {												
		b_repaint.addActionListener(this);
	}
//*****************************************************
	
	//�ēǂݍ��݃{�^�������ꂽ���̏���
	public void actionPerformed(ActionEvent arg0) {
		try {
			//JTable�̓񎟌��z��(TableColumn)������������
			tableModel.setRowCount(0);
			
			//�ŐV�̍݌ɐ����擾����(SQL����static�Œ��O�̌���������ێ�)
			new Search_SQL().listGo();

			//�e�[�u���Đ���
			getTable();
			
			l_repaint.setText("�ŐV�̍݌ɐ���ǂݍ��݂܂���");
			
			//�ĕ`��
			repaint();
			
			//�݌Ɉꗗ�\��JTable�z��ɑ���ł������߁A�񎟌��z����N���A����
			Search_SQL.result_Column.clear();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//�E�B���h�E������ꂽ���̏����A�I�[�o�[���C�h�����������e�N���X�ɂ��̃��\�b�h�K�v�Ȃ��E�E�E
	class WinAda extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	//�ꗗ�\�E�B���h�E�������オ���Ă��Ȃ����
	        paint_now = false;
	    }
	}
}
