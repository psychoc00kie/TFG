import cv2
from pyzbar.pyzbar import decode
import time

cap = cv2.VideoCapture(0)
cap.set(3, 640)
cap.set(4, 480)

courier_id = '1111'

used_codes = []

scann = False

timeout = time.time()+20

_exit = True

while (time.time() <= timeout) or(_exit == True):

	success, frame = cap.read()

	for code in decode(frame):
		print(code.data.decode('utf-8'))
		if code.data.decode('utf-8') not in used_codes:
			print('approved. you can enter')
			used_codes.append(code.data.decode('utf-8'))
			if code.data.decode('utf-8') == courier_id:
				scann = True
				print('exit')
				_exit = False
		elif code.data.decode('utf-8') in used_codes:
			print('allready scanned')
			time.sleep(2)

	cv2.imshow('testing', frame)
	cv2.waitKey(1)


print('The scann was')