#!/usr/bin/env python



import socket
import sys
from time import sleep
from readtempnow import readtempnow
from allinone import setup
from allinone import Led_con
from allinone import cleanup
from allinone import flash
from allinone import green
from allinone import red
import wiringpi2 as wiringpi

TCP_IP='192.168.2.18'
TCP_PORT= 5002
BUFFER_SIZE=1024

setup();
i=0
sleep(2)
try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        print "Temp sensor is connecting ... \n"
        s.connect((TCP_IP, TCP_PORT))

except socket.error, (value, message):

        if s:
                s.close
        print "Could not Open socket" + message
        sys.exit(0)

while True:
        try:
         sleep(2)
         try:
                    temp,state= readtempnow()
                    if state:
                        Led_con(temp)
                        print (str(temp))
                        s.sendall(str(temp))
                     
                    else:
                            s.sendall('Error Occured \n')
                            s.close
                            cleanup()
                            break   #need to add ability to  find a way to send the info
                                    # to the user even if this socket error (using Email)
         except socket.error, e:
                    print "Error %s" % e
                    cleanup()
                    sys.exit(0)
        except KeyboardInterrupt:
                print("The program was interrupted")
                s.sendall('Error Occured \n')
                cleanup()
                s.close
                cleanup()
                sys.exit(0)
                        



