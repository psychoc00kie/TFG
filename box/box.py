import requests
import cv2
from pyzbar.pyzbar import decode
import time

def validateCourrier(data):

	cap = cv2.VideoCapture(0)
	cap.set(3, 640)
	cap.set(4, 480)
	timeout = time.time()+20

	print("\n please place the QR code infront of the camera")


	while time.time() <= timeout: ## unless we get a time out or we succeed in finding the code

		success, frame = cap.read()	# we read from the webcam
		
		for code in decode(frame):
			print("\n procesing code:",code.data.decode('utf-8'))
			for purchase in data:
				print(purchase['courier_id'])
				_courrierID = code.data.decode('utf-8')
				if purchase['courier_id'] == int(_courrierID):
					print("\n Credentials have been validated")
					return [True,_courrierID]

	print("\n There was no QR that matched our Couriers, please check it and try again")
	_errorCode = 1000
	return [False,_errorCode]

def validatePackage(data):

	cap = cv2.VideoCapture(0)
	cap.set(3, 640)
	cap.set(4, 480)
	timeout = time.time()+20
	print("\n please place the QR  of the packet infront of the camera")
	while time.time() <= timeout: ## unless we get a time out or we succeed in finding the code

		success, frame = cap.read()	# we read from the webcam
		
		for code in decode(frame):
			print("\n procesing code:",code.data.decode('utf-8'))
			for purchase in data:
				_productID = code.data.decode('utf-8')
				if purchase['product_id'] == int(_productID):
					print("\n Product has been validated")
					return [True,_productID]

	print("\n There was no QR that matched our Couriers, please check it and try again")
	_errorCode = 1001
	return [False,_errorCode]




print("Waiting for RFID to be activated \n")

time.sleep(3) ## we simulate that we are waiting for the RFID to be activated

##after 

try:
    r = requests.get('http://192.168.0.21:8080/box/deliveries', auth=('box2', 'box'))
except requests.exceptions.ConnectionError:
    r.status_code = "Connection refused"

data = r.json()

type(data)

print(data)
	
## we now initiate the scaning proces 

success,result = validateCourrier(data)

if success:

	_courrierID = result
	pkgSuccess,pkgResult = validatePackage(data)
	if pkgSuccess:
		print("\n Delivery proces has been successful and this were the details: Courierid: %s , Package Id: %s" % (_courrierID,pkgResult))
		## post the resutl to the api 
	else :
		##post error code api
		print("\n Unsuccessful delivery, error code %d, Explaination: blalbbakasjdfs" %(pkgResult))
else:
	#post error code api 
	print("\n Unsuccessful delivery, error code %d, Explaination: blalbbakasjdfs"%(result))


