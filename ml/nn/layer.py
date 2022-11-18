import numpy as np


class Layer:
    def __init__(self, input_size, output_size, activation=None):
        self.weights = np.random.randn(output_size, input_size)
        self.bias = np.random.randn(output_size, 1)
        self.inp=None
        self.Z=None
        self.activation=activation

    def predict(self,X):
        return self.f(np.dot(self.weights,X)+self.bias)

    def f(self,z):
        if self.activation is None: return z
        return self.activation.call(z)

    def forward(self,inp):
        self.inp=inp
        self.Z=np.dot(self.weights,inp)+self.bias
        return self.f(self.Z)

    def backward(self, output_gradient, alpha): # alpha: learning rate
        if self.activation is not None:
            output_gradient=np.multiply(output_gradient, self.activation.call(self.Z,True))
        weights_gradient=np.dot(output_gradient, self.inp.T) # dE/dW
        input_gradient=np.dot(self.weights.T,output_gradient) # dE/dX
        self.weights-=alpha*weights_gradient
        self.bias-=alpha*output_gradient # dE/dB = dE/dY
        return input_gradient
