'''
Created on Apr 23, 2016

@author: emanuel
'''
import numpy as np
import scipy.io.wavfile as wav

class Wav(object):
    '''
    classdocs
    '''


    def __init__(self, *params):
        pass

    def hide(self, message):
        rate, data = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2.wav')
        newData = np.copy(data)
                
        for i in range(len(message)):
            newData[i][0] = message[i]

        
        wav.write('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2new.wav', rate, newData)
            
            
    
    def extract(self):        
        rate, data = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2new.wav')
        anzNuller = 0
        message = []
        strValue = ""
        i = 0
        while (anzNuller < 16):
            strValue = str(bin(data[i][0]))
            if strValue[len(strValue) - 1] == "0":
                anzNuller += 1
            else:
                anzNuller = 0
            message.append(int(strValue[len(strValue) - 1]))
            i += 1
            
        return message[:len(message) - 16]
    
    
    def read(self):
        rate, data = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2.wav')
        rate2, data2 = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2new.wav')
        for i in range(100):
            print "~~~~~"
            print str(data[i])
            print str(bin(data2[i]))
    
        
    def manipulate(self, value, lsb):
        binValue = bin(value)
        strValue = str(binValue)
        strValue2 = strValue[:len(strValue)-1] + str(lsb)
        return self.strBinToInt(strValue2)
    
    
    def strBinToInt(self, str):
        intValue = 0
        
        if str[0] == "-":
            str = str[3:len(str)]
            for i in range(len(str)):
                intValue += int(str[i]) * int(np.exp2(len(str) - (i + 1)))
            intValue = intValue * (-1)
        else:
            str = str[2:len(str)]
            for i in range(len(str)):
                intValue += int(str[i]) * int(np.exp2(len(str) - (i + 1)))
        return intValue
    
    
    def strToBin(self, input):
        strBin = ""
        message = []
        for i in input:
            strBin = str(bin(ord(i)))
            strBin = strBin[2:len(strBin)]
            while len(strBin) < 7:
                strBin = "0" + strBin
            for j in strBin:
                message.append(int(j))
        for i in range(16):
            message.append(0)
        return message
    
    def binToStr(self, input):
        x = ""
        for i in np.arange(0, len(input), 7):
            letter = "0b"
            for j in range(7):
                letter += str(input[j+i])
            x += chr(self.strBinToInt(letter))
        return x