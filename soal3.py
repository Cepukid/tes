bintang = ""

x = int(input("Inputkan angka :"))
bar = x
while bar >= 0:
	kol = bar
	while kol > 0:
		bintang = bintang + " "
		kol = kol - 1
	kanan = 1
	while kanan < (x - (bar-1)):
		bintang = bintang + "*"
		kanan = kanan + 1
	bintang = bintang + "  "
	kiri = 1
	while kiri < (x - (bar-1)):
		bintang = bintang + "*"
		kiri = kiri + 1
	bintang = bintang + "\n"
	bar = bar - 1
bintang = bintang + "\n"
bar = 1
while bar <= x:
	kol = bar
	while kol > 1:
		bintang = bintang + " "
		kol = kol - 1
	kanan = 0
	while kanan <= (x - bar):
		bintang = bintang + "*"
		kanan = kanan + 1
	bintang = bintang + "  "
	kiri = kanan
	while kiri >= 1:
		bintang = bintang + "*"
		kiri = kiri - 1
	bintang = bintang + "\n"
	bar = bar + 1	
print (bintang)