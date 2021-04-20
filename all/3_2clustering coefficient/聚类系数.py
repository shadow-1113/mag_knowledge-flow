# -*- coding: utf-8 -*-
"""
Created on Mon Mar 22 18:53:18 2021

@author: shadow
"""

# encoding=utf-8
#求图结点的聚类系数以及平均聚类系数
ce_list = []
node_set = set()
edge_set = set()
node_list = []
edge_list2 = []
#求每个结点的平均聚类系数
def getCE(node,edgeList):
    #找当前node的直接邻接点，存入node_set的集合中
    for edge in edgeList:
        if edge[0] == node:
            node_set.add(edge[1])
        elif edge[1] == node:
            node_set.add(edge[0])
    #找邻接点集合中的点所构成的边的数目
    for edge in edgeList:
        if edge[0] in node_set and edge[1] in node_set:
            s = edge[0]+edge[1]
            edge_set.add(s)

    neighbourNodeNum = len(node_set)  #邻接点结点个数
    neighbouredgeNum = len(edge_set)  #邻接点构成的边的条数
    print("neighbour node Num:", neighbourNodeNum)
    print("neighbour edge Num:", neighbouredgeNum)
    ceNum = 0
    #求聚类系数的公式
    if neighbourNodeNum > 1:
        ceNum = 2*neighbouredgeNum/((neighbourNodeNum-1)*neighbourNodeNum) #无向图要乘2，有向图不需要乘2
    ce_list.append(ceNum)
    node_set.clear()
    edge_set.clear()
def getAverageCE(ce_list):
    total = 0
    for ce in ce_list:
        total += ce
    return total/len(ce_list)
def main():
    #从文件中读取边信息
    with open('facebook_combined.txt',encoding='utf-8') as f1:
        edge_list = f1.readlines()
    #格式化边信息，去掉其末尾的\n
    for i in range(len(edge_list)):
        spiltList = edge_list[i].replace('\n',"").split(" ", 1)
        edge_list2.append(spiltList)
    #读取结点信息，应该从文件中读取，但鉴于结点名称为0-4038，简便起见直接遍历
    for i in range(4039):
        node_list.append(str(i))
    #对每个结点计算聚类系数
    for node in node_list:
        print(node)
        getCE(node, edge_list2)
    #输出每个结点的聚类系数
    print(ce_list)
    #输出平均聚类系数
    print(getAverageCE(ce_list))

if __name__ == "__main__":
    main()
