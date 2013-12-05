import alsaaudio, time, audioop
import socket
import time
import sys


# Open the device in nonblocking capture mode. The last argument could
# just as well have been zero for blocking mode. Then we could have
# left out the sleep call in the bottom of the loop
card = 'sysdefault:CARD=Microphone'
inp = alsaaudio.PCM(alsaaudio.PCM_CAPTURE,alsaaudio.PCM_NONBLOCK, card)


# Set attributes: Mono, 8000 Hz, 16 bit little endian samples
inp.setchannels(1)
inp.setrate(160)
inp.setformat(alsaaudio.PCM_FORMAT_S16_LE)


# The period size controls the internal number of frames per period.
# The significance of this parameter is documented in the ALSA api.
# For our purposes, it is suficcient to know that reads from the device
# will return this many frames. Each frame being 2 bytes long.
# This means that the reads below will return either 3200 bytes of data
# or 0 bytes of data. The latter is possible because we are in nonblocking
# mode.
inp.setperiodsize(1600)

volume = 0				#initialize volume variable
TCP_IP = '10.0.0.53'			#initialize ip address of server the alarm will be sent to
TCP_PORT = 5003				#initialize the port the server is reading from

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print "Connecting...\n"
    s.connect((TCP_IP, TCP_PORT))
except socket.error, (value,message):
    if s:
        s.close()
    print "Could not open socket: " + message
    sys.exit(1)


while True:
# Read data from device
  l,data = inp.read()
# Return the maximum of the absolute value of all samples in a fragment.
  volume = audioop.max(data, 2)
  if(volume>10000)
     try:
     	socket.sendall("1");	#send 1 to server meaning the alarm should be turned on
     except socket.error, e:
        print "Error sending data: %s" % e
        sys.exit(1)
  else
     try:
     	socket.sendall("0");	#send 0 to server meaning the alarm should be turned off
     except socket.error, e:
        print "Error sending data: %s" % e
        sys.exit(1)
