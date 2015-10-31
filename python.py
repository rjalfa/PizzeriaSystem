import requests

c = 0
try:
	while True:
		if requests.get("http://localhost:8080/PizzeriaSystem/NewOrder").status_code != 200:
			break
		c+=1

	print c
except:
	print c