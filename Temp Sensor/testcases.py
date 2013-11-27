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
#below you will find 4 test cases to show if the called method does what it should do

exvalue="Expected Result: flashing Green "
                                                                                                
print "\n setup will be called "+exvalue+"\n"


setup()
#second case temp=50
exvalue="Expected Result: Red on and Green off "
                                                                                                
print "\n Led_on with t= 50 will be called "+exvalue+"\n"

t=50
Led_con(t)


#third case temp=23

exvalue="Expected Result: Red off and Green on "
                                                                                                
print "\n Led_on with t= 23 will be called "+exvalue+"\n"

t=23
Led_con(t)
sleep(5)
#forth case cleanup no led on ; flashing red and green
exvalue="Expected Result: flashing Green and red "
print "\n cleanup will be called "+exvalue+"\n"
cleanup()



