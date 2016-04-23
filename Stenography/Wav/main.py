'''
Created on Apr 23, 2016

@author: emanuel
'''
import Wav


def main():
    wav = Wav.Wav()
    message = "Hallo Welt was geht denn so bei euch"
    message = wav.strToBin(message)
    print message
    wav.hide(message)
    extractedMessage = wav.extract()
    print wav.binToStr(extractedMessage)

if __name__ == '__main__':
    main()