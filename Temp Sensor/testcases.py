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
from readtempnow import readtempnow

exvalue="Expected Result: only the Green Led is flashing "
                                                                                                
print "\n setup will be called "+exvalue+"\n"


setup()

#test the temp sensor only
exvalue="Expected Result: Temp within the room temp limits(20 to 27)"
temp=readtempnow()
if(temp> 20 and temp <27):
                 print "\n it is within the limits and the reading is "+str(temp)+"\n"
else:
                print "\n it is not within the limits and the reading is "+str(temp)+"\n"           


#test the green only
exvalue="Expected Result: The green will be on for 3 sec"
green(1)
sleep(3)
green(2)
#test the red only
exvalue="Expected Result: The redwill be on for 3 sec"
red(1)
sleep(3)
red(2)

#below you will find 4 test cases for(temp+led) to show if the called method correctly functioning 

exvalue="Expected Result: only the Green Led is flashing "
                                                                                                
print "\n setup will be called "+exvalue+"\n"


setup()
#second case temp=50
exvalue="Expected Result: only the Red Led is flashing"
                                                                                                
print "\n Led_on with t= 50 will be called "+exvalue+"\n"

t=50
Led_con(t)


#third case temp=23

exvalue="Expected Result: only the green Led is on. "
                                                                                                
print "\n Led_on with t= 23 will be called "+exvalue+"\n"

t=23
Led_con(t)
sleep(5)
#forth case cleanup no led on ; flashing red and green
exvalue="Expected Result: the Red and the green Leds are flashin."
print "\n cleanup will be called "+exvalue+"\n"
cleanup()



