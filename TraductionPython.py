from py_trans import PyTranslator
import sys

import time

if __name__ == "__main__":
    #print("python execute")
    txt=str(sys.argv[1:][0])
    trans=PyTranslator()
#     file=open("description.txt","w",encoding='utf-8')
    result=str(trans.translate(txt,"en")['translation'])
    print(result)
#     time.sleep(1)
#     print(result)
#     file.write(result)
#     file.close()


#     txt=str(sys.argv[1:][0])
#     print(txt)
