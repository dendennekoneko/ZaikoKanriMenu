package �݌ɊǗ��V�X�e��;

import java.sql.SQLException;

//SQL�����p���C���N���X
public class Zaiko{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
		
    //static Connection conn;								//SQL�ڑ��C���X�^���X

	//static Statement st;										//SQL���s�p�C���X�^���X
	//static ResultSet rset;                                    //SQL���ʊi�[�p�C���X�^���X
	//static ResultSetMetaData rsmd;					//SQL�J�������擾�p�C���X�^���X	
	
	//static String selectSql;									//SQL�����i�[����
	//public static int get_shocd = 0;					//���͂��ꂽ���i�b�c
	//public static String get_shoname = "";		//���͂��ꂽ���i��
	
	//static boolean string_check_ok;					//DB���ɑ��݂��Ȃ������񂪓��͂���Ă��Ȃ����`�F�b�N�p
	
	//public static List<ArrayList<String>> result_Column = new ArrayList<>();			//�񎟌��z��(DB���ʂ��i�[����\)
	
	
	//******************************************************************************************
    public static void main(String[] args) throws SQLException {
    	
        // (1) �ڑ��p��URI��p�ӂ���(�K�v�ɉ����ĔF�؎w��user/password��t����)
    	//String uri = "jdbc:postgresql://localhost:5432/sample";
        //String user = "postgres";
        //String password = "1818";
        
        // (2) DriverManager�N���X�̃��\�b�h�Őڑ�����
        //conn = DriverManager.getConnection(uri,user,password);
        
        // (3) SQL���M�p�C���X�^���X�̍쐬
       //st = conn.createStatement();
       
       //��ʐ����N���X(Super�N���X�̃R���X�g���N�^)��
       new Zaiko_Menu();
       
        //updateSql(a);															//SQL��������

    }
  //******************************************************************************************
    
   /* public static void updateSql(int a) throws SQLException {								//SQL���������p���\�b�h
        String updateAll = "update hatzaiko SET zaisu="+a+"";
        st.executeUpdate(updateAll);
    }*/
    
}