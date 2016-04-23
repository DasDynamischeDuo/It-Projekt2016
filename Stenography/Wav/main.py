'''
Created on Apr 23, 2016

@author: emanuel
'''
import Wav
import warnings

def main():
    warnings.simplefilter("ignore")
    wav = Wav.Wav()
    message = "Hallo Welt was geht denn so bei euch"
    message = wav.strToBin(message)
    wav.hide(message)
    extractedMessage = wav.extract()
    print "Die Message lautet: " +wav.binToStr(extractedMessage)

if __name__ == '__main__':
    main()