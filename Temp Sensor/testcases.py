#!/usr/bin/env python
#written written by Riyadh
from allinone import Led_con
from allinone import cleanup
from allinone import flash
from allinone import green
from allinone import red
from allinone import setup
import wiringpi2 as wiringpi
from time import sleep
from xmlpar import xmlpar
from readtempnow import readtempnow
id,name,pmax,pmin,dmax,dmin= xmlpar()

exvalue= "Expected Result: only the Gread Led is flashing"
print "\n setup will be called "+exvalue+"\n"


setup()

#test the temp sensor only
exvalue= "Expected Result: Temp within the room temp and (test ) not above dmax ; not below dmin"
print "\n readtempnow() will be called "+exvalue+"\n"
temp=readtempnow()
if(temp> dmin and temp <dmax):
                print "\n it is within the plimits and the reading is "+str(temp)+"\n"
else:
                print "\n it is not within the plimits and the reading is "+str(temp)+"\n"

sleep(3)
#test the green only
exvalue="Expected Result: The green will be on for 3 sec"
print "\n green() will be called "+exvalue+"\n"

green(1)
sleep(3)
green(2)
#test the red only
exvalue="Expected Result: The redwill be on for 3 sec"
print "\n red() will be called "+exvalue+"\n"

red(1)
sleep(3)
red(2)

#below you will find 4 test cases for(temp+led) to show if the called method correctly functioning

setup()
#second case
exvalue="Expected Result: only the Red Led is on"

print "\n Led_on with t that's above pmax will be called "+exvalue+"\n"

t=pmax+10
Led_con(t)

sleep(5)
#third case temp=okay

exvalue="Expected Result: only the green Led is on. "

print "\n Led_on with t= 23 will be called "+exvalue+"\n"

t=pmin+2
Led_con(t)
sleep(5)
#forth case cleanup no led on ; flashing red and green
exvalue="Expected Result: the Red and the green Leds are flashin."
print "\n cleanup will be called "+exvalue+"\n"
cleanup()
