# Originally written by Matthew Kirk http://www.cl.cam.ac.uk/freshers/raspberrypi/tutorials/temperature/LICENSE
# modified By Riyadh
def readtempnow():
    state=False
    tfile = open("/sys/bus/w1/devices/28-0000048cf74f/w1_slave")
    text = tfile.read()
    tfile.close()
    temperature_data = text.split()[-1]
    temperature = float(temperature_data[2:])
    temperature = temperature / 1000
    temperature= int(temperature)
    if temperature>-10 and temperature <53 :
    	state=True
    return temperature,state 
