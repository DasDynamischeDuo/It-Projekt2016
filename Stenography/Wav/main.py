'''
Created on Apr 23, 2016

@author: emanuel
'''
import Wav
import warnings

def main():
    print bin(ord("t"))
    warnings.simplefilter("ignore")
    wav = Wav.Wav()
    sourceURL = '/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2.wav'
    targetURL = '/home/emanuel/git/It-Projekt2016/Stenography/Sounds/waterfall2new.wav'
    message = raw_input("Nachrricht eingeben: ")
    
    message = wav.strToBin(message)
    wav.hide(targetURL=targetURL, sourceURL=sourceURL, message=message)
    extractedMessage = wav.extract(targetURL=targetURL)
    
    print "Die Nachrricht lautet: " +wav.binToStr(extractedMessage)

if __name__ == '__main__':
    main()