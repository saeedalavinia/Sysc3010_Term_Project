#!/usr/bin/env python
# Written By Riyadh
import wiringpi2 as wiringpi
from time import sleep
from xmlpar import xmlpar
st=False

id,name,pmax,pmin,dmax,dmin= xmlpar()
def setup():
        wiringpi.wiringPiSetupGpio()
        wiringpi.pinMode(25,1)        #red light
        wiringpi.digitalWrite(25,0)   #red is off
        wiringpi.pinMode(24,1)        #green light
        wiringpi.digitalWrite(24,0)   #green is off
      
        flash(2)
def Led_con(Temp):

                      
           if(Temp>=dmax):
              flash(3)
              wiringpi.digitalWrite(25,0)   #red is off
              wiringpi.digitalWrite(24,0)   #green is off
              
           elif(Temp<=dmin):
              flash(3)
              wiringpi.digitalWrite(25,0)   #red is off
              wiringpi.digitalWrite(24,0)   #green is off
           elif(Temp>= pmin and Temp<= pmax):
                   wiringpi.digitalWrite(25,0)   #red is off
                   wiringpi.digitalWrite(24,0)   #green is off

                   green(1)
                        
           elif(Temp>10 or Temp<40):
                red(1)
                green(2)
                
        
def cleanup():
        wiringpi.digitalWrite(25,0)   #red is off
        wiringpi.digitalWrite(24,0)   #green is off
        flash(1)
def flash(n):
        if n==1:
         green(1)  #green is on
         red(2)    #red is ff
         sleep(1)
         green(2)  #green is off
         red(1)    #red is on
         sleep(1)
         green(1)  #green is on
         red(2)    #red is ff
         sleep(1)
         green(2)  #green is off
         red(1)    #red is on
         sleep(1)
         green(1)  #green is on
         red(2)    #red is ff
         sleep(1)
         green(2)  #green is off
         red(1)    #red is on
         sleep(1)
         wiringpi.digitalWrite(25,0)   #red is off
         wiringpi.digitalWrite(24,0)   #green is off

        elif n==2:
         red(2)    #red is ff
         green(1)  #green is on
         sleep(1)
         green(2)  #green is off
         sleep(1)
         green(1)  #green is on
         sleep(1)
         green(2)  #green is off
         sleep(1)
         green(1)  #green is on
         sleep(1)
         green(2)  #green is off
         sleep(1)
         wiringpi.digitalWrite(25,0)   #red is off
         wiringpi.digitalWrite(24,0)   #green is off cleanup()
        elif n==3:
         green(2)  #green is off
         red(1)    #red is on
         sleep(1)
         red(2)    #red is ff
         sleep(1)
         red(1)    #red is on
         sleep(1)
         red(2)    #red is ff
         sleep(1)
         red(1)    #red is on
         sleep(1)
         wiringpi.digitalWrite(25,0)   #red is off
         wiringpi.digitalWrite(24,0)   #green is off
         
         
def green(n):
           if n==1:     #only green 
                      wiringpi.digitalWrite(24,1)   #on
           elif n==2:       #just set green
                      wiringpi.digitalWrite(24,0)  #Turn off 
 
def red(n):
           if n==1:     #only red 
                      wiringpi.digitalWrite(25,1)   #on
           elif n==2:        #just set 
                      wiringpi.digitalWrite(25,0)  #Turn off 
