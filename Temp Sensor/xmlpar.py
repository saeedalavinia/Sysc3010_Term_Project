from elementtree import ElementTree as ET

def xmlpar():

	content = ET.parse('settings.xml')


	device = content.find("device")
	first_name = device.find("name/first")
	

# Get the sensor info 
	info = device.find("info")
	maxtemp= info.find("maxtemp")
	mintemp=info.find("mintemp")
	devicemax= info.find("devicemax")
	devicemin = info.find("devicemin")

# Print output
	id = device.attrib.get('id')
	name= first_name.text
	pmax=int(maxtemp.text)
	pmin= int(mintemp.text)
	dmax= int(devicemax.text)
	dmin=int(devicemin.text)
	return id,name,pmax,pmin,dmax,dmin
