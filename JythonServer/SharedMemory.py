'''
Created on Nov 22, 2013

@author: vsharovar
'''

temperature = 0
alarmIsOn = False

def temperatureSet(temp):
    global temperature
    temperature = temp

def temperatureGet():
    global temperature
    return temperature

def setTheAlarm(alarmTriggered):
    global alarmIsOn
    alarmIsOn = alarmTriggered

def readTheAlarm():
    global alarmIsOn
    return alarmIsOn