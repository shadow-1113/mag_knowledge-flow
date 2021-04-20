package dbhelper;

import java.io.File;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class Neo4jDBHelper {

	private File DB_PATH = null;
	private GraphDatabaseService graphDb = null;

	public Neo4jDBHelper(String dbPath){
		DB_PATH = new File(dbPath);
	}
	
    /* 
     * 创建一个新的图数据库
     * para1:数据库存储路径*/
    public GraphDatabaseService createNewGraph(){
    	graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
    	return graphDb;
    }
    
    /* 
     * 链接一个已经存在的数据库
     * para1:数据库存储路径*/
    public GraphDatabaseService connectGraph(){
    	graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(DB_PATH)
        .setConfig( GraphDatabaseSettings.read_only, "true" )
        .newGraphDatabase();
    	
    	return graphDb;
    }
    
    /* 
     * 确保正确退出*/
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
    
    /* 
     * 添加节点
     * para1：节点标签
     * para2：节点的属性-属性值对*/
    public void addNode(Vector<String> nodeLabel, Map<String, String> pAndPv) {
        Node node = graphDb.createNode();
        
        for(int i = 0; i < nodeLabel.size(); i++){
        	node.addLabel(Label.label(nodeLabel.get(i)));
        }
        
        for(Entry<String, String> entry : pAndPv.entrySet()){
    		node.setProperty(entry.getKey(), entry.getValue());
    	}
    }
    
    
    /* 
     * 在已经有的实体节点中添加
     * para1：节点标签
     * para2：节点的属性-属性值对*/
    public void Nodeadd(Node node,Vector<String> nodeLabel, Map<String, String> pAndPv) {
        
        for(int i = 0; i < nodeLabel.size(); i++){
        	node.addLabel(Label.label(nodeLabel.get(i)));
        }
        
        for(Entry<String, String> entry : pAndPv.entrySet()){
    		node.setProperty(entry.getKey(), entry.getValue());
    	}
    }
    
    /* 
     * 添加关系
     * para1：头节点
     * para2：尾节点
     * para3：关系标签
     * para4：关系的属性-属性值对*/
    public void addRelation(Node hNode, Node rNode, String relationLabel, Map<String,String> pAndPv){
    	
        Relationship relationship = hNode.createRelationshipTo(rNode, RelationshipType.withName(relationLabel));
        
    	for(Entry<String, String> entry : pAndPv.entrySet()){
    		relationship.setProperty(entry.getKey(), entry.getValue());
    	}
    	
    }
    
    /* 
     * 获取节点
     * para1：节点标签
     * para2：节点属性
     * para3：节点属性值
     * return：节点*/
    public Node getNode(String nodeLabel, String nodeProperty, String nodePvalue){
    	
        Node node = graphDb.findNode(Label.label(nodeLabel), nodeProperty, nodePvalue);
        
        return node;
    }
    
    /* 
     * 删除节点
     * para1：节点标签
     * para2：节点属性
     * para3：节点属性值*/
    public void deleteNode(String nodeLabel, String nodeProperty, String nodePvalue){
    	Node node = graphDb.findNode(Label.label(nodeLabel), nodeProperty, nodePvalue);
    	
    	if(node == null){
    		return;
    	}
    	
    	Iterable<Relationship> inRelationships = node.getRelationships(Direction.INCOMING);
    	for(Relationship rel : inRelationships){
    		rel.getStartNode().delete();
    		rel.delete();
    	}
    	
    	Iterable<Relationship> outRelationships = node.getRelationships(Direction.OUTGOING);
    	for(Relationship rel : outRelationships){
    		rel.getEndNode().delete();
    		rel.delete();
    	}
    	
    	node.delete();
    	
    }
    
    /* 
     * 关闭数据库*/
    public void close(){
    	graphDb.shutdown();
    }

}
