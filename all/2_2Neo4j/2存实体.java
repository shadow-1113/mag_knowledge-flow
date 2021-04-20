package Geo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
import dbhelper.Neo4jDBHelper;

public class _1c_field_e {
	
	private static Neo4jDBHelper ndp = null;
	private static GraphDatabaseService graphDb = null;
	final static String triplePath = "./11";//实体数据使用
	 
	/* 
	 * 实体入库事物处理，每5000个实体为一个单位
	 * para1：已入库实体数
	 * para2：待读文件*/
	public static void tranEntity(int num, BufferedReader br, Map<String,String> pAndPv, Vector<String> tag){
        String triple = null;//triple用于存放文档中每行的数据
        String entity = null;//用于存放三元组数据
        
        int flag = 0;
        while(true){
        	try(Transaction tx = graphDb.beginTx()){//启动数据库
    			int count = 0;
    	        try{
    	            while((triple = br.readLine()) != null){
//    	            	if(triple.charAt(25)=='_') {
//    	            		if((triple.substring(38, 46)).equals("0796A60A")){
//    	            			System.out.println(triple);
//	//    	            	readLine方法
//	//    	            	功能:读取一个文本行。通过下列字符之一即可认为某行已终止:换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。 
//	//    	            	返回:包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
//	    	        		
//	    	        		entity=triple.substring(4, 12);
//	    	        		
//	    	        		
//    	            			entity = triple.substring(0, 8);	
    	            			tag.add("J");
	    	            		pAndPv.put("J_ISSN", triple.split("	")[0]);
	    	            		pAndPv.put("J_f", triple.split("	")[1]);
	    	            		ndp.addNode(tag, pAndPv);
	    	            		pAndPv.clear();
	    			            tag.clear();
	    			            ++num;
	    	            		++count;
	    	            	
	    	            	if(count >= 500){
	            				System.out.println("e："+num);
	            				flag = 1;
	            				break;
	            			}
    	   
    	            }//endOfWhile
   	            
    	            if(flag == 0){
    	            	br.close();
       	            
        	            if(pAndPv.size()>0 || tag.size()>0){	//添加最后一个实体
        	            	tag.add("J");
                			pAndPv.put("field_id", entity);
        	            	ndp.addNode(tag, pAndPv);
        	            }
        	            
        	            System.out.println("e："+num);
        	            tx.success();
            		    tx.close();
        	            break;
    	            }else{
    	            	flag = 0;
    	            }
    	            
    	        }catch(Exception e){
    	            e.printStackTrace();
    	            System.out.println(triple);
    	        }
    		    tx.success();
    		    tx.close();
    		}
        }//endOfWhile
		
	}
	
	/* 
	 * 三元组入库
	 * neo4j的每一个操作称为：事务,每一项事务都必须用下面的结构进行包装:
	 * try(Transaction tx = graphDB.beginTx()){
	 * ....
	 * tx.success();
	 * }*/
	public static void triple2Db(){
		File input = new File(triplePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Map<String,String> pAndPv = new HashMap<String, String>();
        Vector<String> tag = new Vector<String>();
        tranEntity(0,br,pAndPv,tag);
        
	}
	
	
	/* 
	 * 创建索引
	 * 加快创建边的速度*/
	public static void createIndex(){
		try(Transaction tx = graphDb.beginTx()){
			
			graphDb.schema().indexFor(Label.label("AceKG")).on("ID").create();
			
		    tx.success();
		    tx.close();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		System.out.println("start");
		ndp = new Neo4jDBHelper("C:\\Users\\13828\\Desktop\\308_j\\J_14779.db");
		graphDb = ndp.createNewGraph();
		ndp.registerShutdownHook();
		triple2Db();
		ndp.close();
		
		System.out.println("success");
	}

}
