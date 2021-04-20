# -*- coding: utf-8 -*-
"""
Created on Tue Mar  9 01:19:04 2021

@author: shadow
"""


import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties

from matplotlib.pyplot import MultipleLocator

date =[1995,2000,2005,2010,2015]
f = open(r'./1.txt')
line = f.readline()
data_list = []
while line:
	num = list(map(float,line.split()))
	data_list.append(num)
	line = f.readline()
f.close()
pay1=data_list





plt.stackplot(date,pay1)
x_major_locator=MultipleLocator(5)
ax=plt.gca()
ax.xaxis.set_major_locator(x_major_locator)#
ax.get_yaxis().get_major_formatter().set_useOffset(False)
plt.xlabel('time')
plt.ylabel('%')
plt.title(u'10')



#标签
#plt.plot([],[],color='green', label="income", lineWidth=5)
#plt.legend()

plt.savefig('myfig.png')
plt.show()
