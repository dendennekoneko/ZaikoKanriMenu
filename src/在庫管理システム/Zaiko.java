package �݌ɊǗ��V�X�e��;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Zaiko{
	
	private static final long serialVersionUID = 1L;
		
    static Connection conn;

	static Statement st;										//SQL���s�p�C���X�^���X
	static ResultSet rset;                                      //SQL���ʊi�[�p�C���X�^���X
	static ResultSetMetaData rsmd;
	
	static String selectSql;
	public static int get_shocd = 0;
	public static String get_shoname = "";
	
	static boolean string_check_ok;								//DB���ɑ��݂��Ȃ������񂪓��͂���Ă��Ȃ����`�F�b�N�p
	
	public static List<ArrayList<String>> result_Column = new ArrayList<>();			//�񎟌��z��(DB���ʂ��i�[����\)
	
    public static void main(String[] args) throws SQLException {
    	
        // (1) �ڑ��p��URI��p�ӂ���(�K�v�ɉ����ĔF�؎w��user/password��t����)
    	String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManager�N���X�̃��\�b�h�Őڑ�����
        conn = DriverManager.getConnection(uri,user,password);
        
        // (3) SQL���M�p�C���X�^���X�̍쐬
       st = conn.createStatement();
       
       //��ʐ����N���X(Super�N���X�̃R���X�g���N�^)��
       Mado m = new Mado();
        
        //updateSql(a);															//SQL��������

    }
    
    //SQL���ʕ\���p���\�b�h 	
    public static void getSql() throws SQLException {
    	
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
        Mado.l_error.setText("");
        string_check_ok = false;

    	rset = st.executeQuery(selectSql);
    	
        //�܂������ȕ����񂪓����Ă��Ȃ����m�F����I�I
    	if(!get_shoname.equals("")) {
            stringCheck();
            if(!string_check_ok) {
            	Mado.l_error.setText("���݂��Ȃ����i�������͂���Ă��܂��B");
            }
    	}else {
    		string_check_ok = true;
    	}

        //���݂��Ȃ�������G���[���o�Ă��Ȃ����
    	if(string_check_ok) {
            search();
    	}
        
        //���X�̃G���[���o�Ă��Ȃ����
    	if(Mado.l_error.getText().equals("")) {
    		
    		rset = st.executeQuery(selectSql);
            
            //���R�[�h���擾
    		while(rset.next()) {
				//@SuppressWarnings("unchecked")					//�R���p�C���x���̖������B���x�����o�邽��
    			
    			//�Ȃ�������(ArrayList����)
    			ArrayList<String> tempArray = new ArrayList<>();
    			
    			tempArray.add(String.valueOf(rset.getInt("shocd")));
    			tempArray.add(rset.getString("shoname"));
    			tempArray.add(String.valueOf(rset.getInt("zaisu")));

    			//�񎟌�List��tempArray��ǉ�
    			result_Column.add(tempArray);
    		}
    		
    		//Result res = new Result();
    		
            //�J���������擾
            rsmd = rset.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i)+"�@�F�@");
             }
            System.out.println("");

            //���ʕ`��
        	for(ArrayList<String> a : result_Column) {
        		for(String b : a) {
        			System.out.print(b.trim() + "�@�F�@");
        		}
        		System.out.println("");
        	}

        //�G���[���x���ɉ��炩�̃��b�Z�[�W�������Ă���B
    	}else {
    		System.out.println("�G���[�ŏI���܂����B");
    	}
    }
    
     //���݂��Ȃ����i�������͂���Ă��Ȃ����Ď�
    public static void stringCheck() throws SQLException {
    	//���i�������͂���Ă���
    	if(!get_shoname.equals("")) {
            while(rset.next()) {
            	//���͂��ꂽ���i���ƁASQL���ʂ̏��i������v����
            	if(rset.getString("shoname").trim().equals(get_shoname)) {
            		string_check_ok = true;
            		break;
            	}
            }
    	}
    }        
    
    //���͂��ꂽ���i������v���Ă��邩�`�F�b�N
    public static void search() throws SQLException {
    	//���i�b�c�����͂���Ă���
    	if(get_shocd != 0) {
    		selectSql = "SELECT * FROM hatzaiko WHERE shocd="+get_shocd+"";
            rset = st.executeQuery(selectSql);
            //���i�������͂���Ă���
            if(!get_shoname.trim().equals("")) {
                while(rset.next()) {
                	//���͂��ꂽ���i�����ƁA���͂��ꂽ���i�b�c�ɑΉ����鏤�i������������v���Ă���
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		break;
                	}else {
                    	Mado.l_error.setText("���i�b�c�Ə��i������v���܂���B");
                    	break;
                	}
                }
            }
         //���i�������͂���Ă���B
    	}else if(!get_shoname.trim().equals("")){
            rset = st.executeQuery(selectSql);
    		while(rset.next()) {
        		//���͂��ꂽ���i����DB��̏��i���ƈ�v���Ă���
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
            		selectSql = "SELECT * FROM hatzaiko WHERE shoname like '%"+get_shoname+"%'";
            		break;
            	}
    		}
    	}
    }
    
    
   /* public static void updateSql(int a) throws SQLException {								//SQL���������p���\�b�h
        String updateAll = "update hatzaiko SET zaisu="+a+"";
        st.executeUpdate(updateAll);
    }*/
    
}