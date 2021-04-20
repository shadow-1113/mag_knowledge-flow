import numpy as np
import random
import seaborn as sns
from matplotlib import pyplot as plt

 
def draw():
    #定义热图的横纵坐标
#    xLabel = ['General','Agricultural and Biological Sciences','Arts and Humanities','Biochemistry. Genetics and Molecular Biology','Business. Management and Accounting','Chemical Engineering','Chemistry','Computer Science','Decision Sciences','Earth and Planetary Sciences','Economics, Econometrics and Finance','Energy','Engineering','Environmental Science','Immunology and Microbiology','Materials Science','Mathematics','Medicine','Neuroscience','Nursing','Pharmacology, Toxicology and Pharmaceutics','Physics and Astronomy','Psychology','Social Sciences','Veterinary','Dentistry','Health Professions']
#    yLabel = ['General','Agricultural and Biological Sciences','Arts and Humanities','Biochemistry. Genetics and Molecular Biology','Business. Management and Accounting','Chemical Engineering','Chemistry','Computer Science','Decision Sciences','Earth and Planetary Sciences','Economics, Econometrics and Finance','Energy','Engineering','Environmental Science','Immunology and Microbiology','Materials Science','Mathematics','Medicine','Neuroscience','Nursing','Pharmacology, Toxicology and Pharmaceutics','Physics and Astronomy','Psychology','Social Sciences','Veterinary','Dentistry','Health Professions']
#    xLabel = ['Physical Sciences','Life Sciences','Health Sciences','Social Sciences']
#    yLabel = ['Physical Sciences','Life Sciences','Health Sciences','Social Sciences']
    xLabel = ['Health Professions','Dentistry','Veterinary','Nursing','Medicine','Multidisciplinary','Biochemistry, Genetics and Molecular Biology','Immunology and Microbiology','Pharmacology, Toxicology and Pharmaceutics','Neuroscience','Agricultural and Biological Sciences','Environmental Science','Earth and Planetary Sciences','Chemical Engineering','Chemistry','Engineering','Materials Science','Physics and Astronomy','Mathematics','Computer Science','Energy','Social Sciences','Psychology','Business, Management and Accounting','Economics, Econometrics and Finance','Arts and Humanities','Decision Sciences']
    yLabel = ['Health Professions','Dentistry','Veterinary','Nursing','Medicine','Multidisciplinary','Biochemistry, Genetics and Molecular Biology','Immunology and Microbiology','Pharmacology, Toxicology and Pharmaceutics','Neuroscience','Agricultural and Biological Sciences','Environmental Science','Earth and Planetary Sciences','Chemical Engineering','Chemistry','Engineering','Materials Science','Physics and Astronomy','Mathematics','Computer Science','Energy','Social Sciences','Psychology','Business, Management and Accounting','Economics, Econometrics and Finance','Arts and Humanities','Decision Sciences']

    f = open(r'./111.txt')
    line = f.readline()
    data_list = []
    while line:
        num = list(map(float,line.split()))
        data_list.append(num)
        line = f.readline()
    f.close()
    train = np.array(data_list)
 
    sns.set()
    #ax = sns.heatmap(train1, linewidths = 1, cmap=plt.get_cmap('Oranges'),vmax=1)
    #ax.set_yticklabels(yLabel,rotation=360)
    #ax.set_xticklabels(xLabel,rotation=270)
    #plt.title("category")
    
    plt.figure(figsize=(25, 20),dpi=100)

    ax = sns.heatmap(train, linewidths = 1, cmap=plt.get_cmap('Oranges'),vmax=8.5)
    plt.title("2017")        
    plt.savefig('reli.png')
 
d = draw()
