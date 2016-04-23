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
        rate, data = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall1.wav')
        newData = []
                
        for i in range(len(message)):
            newData.append(self.manipulate(data[i], message[i]))
            
        for i in range(len(data)):
            if i < len(message):
                pass
            else:
                newData.append(data[i])

        newData = np.asarray(newData, dtype=np.int16)

        wav.write('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall1new.wav', rate, newData)
            
            
    
    def extract(self):        
        rate, data = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall1new.wav')
        anzEinser = 0
        message = []
        strValue = ""
        i = 0
        while (anzEinser < 8):
            strValue = str(bin(data[i]))
            if strValue[len(strValue) - 1] == "1":
                anzEinser += 1
            else:
                anzEinser = 0
            message.append(int(strValue[len(strValue) - 1]))
            i += 1
            
        return message[:len(message) - 8]
    
    
    def read(self):
        rate, data = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall1.wav')
        rate2, data2 = wav.read('/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall1new.wav')
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
        for i in range(8):
            message.append(1)
        return message
    
    def binToStr(self, input):
        x = ""
        for i in np.arange(0, len(input), 7):
            letter = "0b"
            for j in range(7):
                letter += str(input[j+i])
            x += chr(self.strBinToInt(letter))
        return x