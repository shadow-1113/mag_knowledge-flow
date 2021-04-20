# -*- coding: utf-8 -*-
"""
Created on Fri Mar 12 11:36:17 2021

@author: shadow
"""

# -*- coding: UTF-8 -*-
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt

#这里导入你自己的数据
#......
#......
#x_axix，train_pn_dis这些都是长度相同的list()

f = open(r'./314_s.txt')
line = f.readline()
data_list = []
while line:
    num = list(map(float,line.split()))
    data_list.append(num)
    line = f.readline()
f.close()
x=data_list[0]
y1=data_list[1]
y2=data_list[2]
y3=data_list[3]
y4=data_list[4]
y5=data_list[5]
y6=data_list[6]
y7=data_list[7]
y8=data_list[8]
y9=data_list[9]
y10=data_list[10]
y11=data_list[11]
y12=data_list[12]
y13=data_list[13]
y14=data_list[14]
y15=data_list[15]
y16=data_list[16]
y17=data_list[17]
y18=data_list[18]
y19=data_list[19]
y20=data_list[20]
y21=data_list[21]
y22=data_list[22]
y23=data_list[23]
y24=data_list[24]
y25=data_list[25]
y26=data_list[26]
y27=data_list[27]
#开始画图
plt.figure(figsize=(18, 6),dpi=100)




#plt.plot(x,y1, color='steelblue', label='Multidisciplinary')
#plt.plot(x,y3, color='darkgrey', label='Biochemistry')
plt.plot(x,y5, color='gainsboro')
plt.plot(x,y6, color='gainsboro')
plt.plot(x,y7, color='gainsboro')
plt.plot(x,y8, color='gainsboro')
plt.plot(x,y9, color='gainsboro')
plt.plot(x,y10, color='gainsboro')
plt.plot(x,y11, color='gainsboro')
plt.plot(x,y12, color='gainsboro')
plt.plot(x,y13, color='gainsboro')
plt.plot(x,y14, color='gainsboro')
plt.plot(x,y16, color='gainsboro')
plt.plot(x,y17, color='gainsboro')
plt.plot(x,y22, color='gainsboro')
plt.plot(x,y24, color='gainsboro')
#plt.plot(x,y18, color='darkgrey', label='Chemical Engineering')

plt.plot(x,y20, color='darkred', label='Nursing',linestyle='--')

plt.plot(x,y25, color='darkgreen', label='Veterinary',linestyle='--')

plt.plot(x,y27, color='navy', label='Health Professions',linestyle='--')

plt.plot(x,y4, color='r',linewidth=2.5)
plt.plot(x,y19, color='gold',linewidth=2.5)
plt.plot(x,y23, color='dodgerblue',linewidth=2.5)
plt.plot(x,y2, color='m',linewidth=2.5)
plt.plot(x,y21, color='chartreuse',linewidth=2.5)
plt.plot(x,y15, color='saddlebrown',linewidth=2.5)

plt.plot(x,y26, color='black', label='Dentistry',linestyle='--')


plt.yscale('log')

plt.ylim(0.05,5)
plt.xlim(1950,2017)
#plt.legend(loc=2, bbox_to_anchor=(1.05,1.0),borderaxespad = 0.) 

leg = plt.legend(loc='lower right',fontsize ='xx-large')

# set the linewidth of each legend object
for legobj in leg.legendHandles:
    legobj.set_linewidth(3.0)
plt.xticks(size = 15)
plt.yticks(size = 15)
plt.ylabel('reference strength to Medicine',fontsize='xx-large')
plt.savefig('beiyinqiangdu.png')
plt.show()
