'''
Created on Nov 1, 2013

@author: vsharovar
'''
import threading
import time
import SocketServer
import socket
import SharedMemory as sh

class JavaServerThreadHandler(SocketServer.BaseRequestHandler):

    def handle(self):
        print "Thread3 started\n"

        while(1):
            try:
                stringToSend = '<temp>'+str(sh.temperatureGet())+'</temp>\n'
                self.request.sendall(stringToSend)
                stringToSend = '<alarm>'+str(sh.readTheAlarm())+'</alarm>\n'
                self.request.sendall(stringToSend)
            except socket.error, msg:
                self.request.close()
                break
            time.sleep(1)


class TemperatureThreadHandler(SocketServer.BaseRequestHandler):

    def handle(self):
        print "Thread1 started\n"
        while(1):
            try:
                self.data = self.request.recv(1024)
                sh.temperatureSet(self.data)
            except socket.error, msg:
                self.request.close()
                break


class AlarmThreadHandler(SocketServer.BaseRequestHandler):

    def handle(self):
        print "Thread2 started\n"
        global invoker
        while(1):
            try:
                self.data = self.request.recv(1024)
                if self.data == 'False':
                    sh.setTheAlarm(False)
                else:
                    sh.setTheAlarm(True)
            except socket.error, msg:
                self.request.close()
                break


class ThreadedTCPServer(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    pass

if __name__ == "__main__":

    HOST = ''
    #TEMP = '10.0.0.53'
    PORT_A = 10000
    PORT_B = 10001
    PORT_C = 10002

    print "Server started.\n"

    server_A = ThreadedTCPServer((HOST, PORT_A), TemperatureThreadHandler)
    server_B = ThreadedTCPServer((HOST, PORT_B), AlarmThreadHandler)
    server_C = ThreadedTCPServer((HOST, PORT_C), JavaServerThreadHandler)

    server_A_thread = threading.Thread(target=server_A.serve_forever)
    server_B_thread = threading.Thread(target=server_B.serve_forever)
    server_C_thread = threading.Thread(target=server_C.serve_forever)

    server_A_thread.setDaemon(True)
    server_B_thread.setDaemon(True)
    server_C_thread.setDaemon(True)

    server_A_thread.start()
    server_B_thread.start()
    server_C_thread.start()

    server_A_thread.join()
    server_B_thread.join()
    server_C_thread.join()
    