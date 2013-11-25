'''
Created on Nov 9, 2013

@author: ssasnous
'''
# Echo client program
import socket
import sys
import time

def javaServer():
    #HOST = '10.0.0.53' # The remote host
    HOST = ''
    PORT = 10002 # The same port as used by the server
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

        while(1):
            data = s.recv(1024)
            if data:
                print data
                if data == '<alarm>True</alarm>\n':
                    s.close()
                    s = None
                    break

        break

    if s is None:
        print "could not open socket"
        sys.exit(1)

if __name__ == "__main__":
    javaServer()