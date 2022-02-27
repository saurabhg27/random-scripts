from PyPDF2 import PdfFileMerger

import comtypes.client
import os

def PPTtoPDF(inputFileName, outputFileName, formatType = 32):
    print("Trying to convert {} to {}".format(inputFileName,outputFileName))
    powerpoint = comtypes.client.CreateObject("Powerpoint.Application")
    powerpoint.Visible = 1

    if outputFileName[-3:] != 'pdf':
        outputFileName = outputFileName + ".pdf"
    deck = powerpoint.Presentations.Open(inputFileName)
    deck.SaveAs(outputFileName, formatType) # formatType = 32 for ppt to pdf
    deck.Close()
    powerpoint.Quit()


def doPPTtoPDFForAllPPtx():
    ppts = [f for f in os.listdir() if f.endswith('.pptx')]
    print(ppts)
    mypath = os.path.abspath(__file__)
    mydir = os.path.dirname(mypath)

    for file in ppts:
        file_input = os.path.join(mydir, file)
        file_output = os.path.join(mydir, file[:-5])
        PPTtoPDF(file_input,file_output)
        

def mergePDFs():
    pdfs = [f for f in os.listdir() if f.endswith('.pdf')]
    merger = PdfFileMerger()

    for pdf in pdfs:
        merger.append(pdf)

    merger.write("result.pdf")
    merger.close()
    
########### main

doPPTtoPDFForAllPPtx()
mergePDFs()

print("Done !!")