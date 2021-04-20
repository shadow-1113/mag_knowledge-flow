# -*- coding: utf-8 -*-
"""
Created on Tue Mar  2 19:30:22 2021

@author: shadow
"""

from sklearn.linear_model import LinearRegression
from matplotlib import pyplot

f = open(r'C:\\Users\\13828\\Desktop\\4-.txt')
line = f.readline()
data_list = []
while line:
    num = list(map(float,line.split()))
    data_list.append(num)
    line = f.readline()
f.close()


year =data_list[0]
count =data_list[1]

#生成图表
plt.figure(figsize=(14, 5),dpi=100)
pyplot.plot(year, count)

#设置横坐标为year，纵坐标为population，标题为Population year correspondence
pyplot.xlabel('year', fontdict={'size'   : 20})
pyplot.ylabel('count', fontdict={'size'   : 20})
pyplot.title('Total number of published', fontdict={'size'   : 20})
#设置纵坐标刻度
plt.yscale('log')
plt.xlim(1850,2017)
plt.yticks(size = 18)
plt.xticks(size = 18)
#pyplot.yticks([0, 25, 50, 75, 90])#可设置坐标显示值
pyplot.fill_between(year, count, 0, color = 'b', alpha=0.3)#参数分别对应横坐标，纵坐标，纵坐标填充起始值，填充颜色,透明度
#显示图表

plt.savefig('papercount.png')


