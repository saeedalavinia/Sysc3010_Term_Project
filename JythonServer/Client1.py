'''
Created on Nov 1, 2013

@author: vsharovar
'''
# Echo client program
import socket
import sys
import time

def alarmClient(alarmIsOn):
    #HOST = '10.0.0.53' # The remote host
    HOST = ''
    PORT = 10001 # The same port as used by the server
    s = None

    for res in socket.getaddrinfo(HOST, PORT, socket.AF_UNSPEC, socket.SOCK_STREAM):

        af, socktype, proto, canonname, sa = res
        try:

            s = socket.socket(af, socktype, proto)

        except socket.error, msg:
            s = None
            continue
        try:
            s.connect(sa)
        except socket.error, msg:
            s.close()
            s = None
            continue

        #while(1):
            #try:
                #s.sendall("1")
                #time.sleep(2)
                #s.sendall("2")
                #time.sleep(2)
                #s.sendall("3")
                #time.sleep(2)
            #except socket.error, msg:
                #s.close()
                #break
        time.sleep(3)
        try:
            print str(alarmIsOn)
            s.sendall(str(alarmIsOn))
        except socket.error, msg:
            s.close()
            break
        time.sleep(10)
        s.close()
        s = None
        break

    if s is None:
        print "could not open socket"
        sys.exit(1)

if __name__ == "__main__":
    alarmClient(True)