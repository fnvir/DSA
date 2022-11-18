import numpy as np
from neural_network import NeuralNetwork
from loss_functions import MSE,CrossEntropy


x_train,x_test,y_train,y_test=[[] for _ in range(4)]
trainfile=open('d:/users/farha/desktop/mnist_train.csv')
testfile=open('d:/users/farha/desktop/mnist_test.csv')
next(trainfile),next(testfile)

for line in trainfile:
    y,*x=map(int,line.split(','))
    x_train.append(np.array(x).reshape((len(x),-1))/255)
    Y=np.zeros(10)
    Y[y]=1
    y_train.append(Y.reshape(10,-1))

for line in testfile:
    y,*x=map(int,line.split(','))
    x_test.append(np.array(x).reshape((len(x),-1))/255)
    Y=np.zeros(10)
    Y[y]=1
    y_test.append(Y.reshape(10,-1))


x_train,x_test,y_train,y_test=map(np.array,[x_train,x_test,y_train,y_test])

print(x_test.shape,y_train.shape)
print(x_test[0].shape,y_train[0].shape)

import time
st=time.time()
nn=NeuralNetwork((28*28,40,10),MSE)
nn.gradient_descent_stochastic(x_train,y_train,max_iter=500,learning_rate=0.1,show=True,batch_size=1500)
print(time.time()-st,'s')

cnt=0
for i in range(len(x_test)):
    z=nn.predict(x_test[i])
    if np.argmax(z)==np.argmax(y_test[i]): cnt+=1

print(f'{cnt} / {len(x_test)}  -->  accuracy: {100*cnt/len(x_test)} %')
