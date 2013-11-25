'''
Created on Nov 22, 2013

@author: vsharovar
'''
import unittest, time, os, Client, Client1, Server, JavaServer
import SharedMemory as sm
import subprocess as subp

class TestServer(unittest.TestCase):
    
    def setUp(self):
        pass

    def testTemperatureGet(self):
        self.assertEqual(0,sm.temperatureGet())
        
    def testAlarmGet(self):
        self.assertFalse(sm.readTheAlarm())
        
    def testServer(self):
        #process = subp.Popen(['jython', 'Server.py'], stdout = subp.PIPE )
        process = subp.Popen("jython Server.py",shell=True)
        time.sleep(1)
        #Client.temperatureClient(0)
        process = subp.Popen("python Client.py",shell=True)
        time.sleep(1)
        #Client1.alarmClient(True)
        process = subp.Popen("python Client1.py",shell=True)
        time.sleep(1)
        JavaServer.javaServer()
        
        try:
            self.subp.kill()
        except OSError:
            # can't kill a dead proc
            pass

