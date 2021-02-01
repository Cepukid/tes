bintang = ""

x = int(input("Inputkan angka :"))
bar = x
while bar >= 0:
	kol = bar
	while kol > 0:
		bintang = bintang + "   "
		kol = kol - 1
	kiri = 1
	while kiri < (x - (bar-1)):
		bintang = bintang + " * "
		kiri = kiri + 1
	kanan = 1
	while kanan < kiri -1:
		bintang = bintang + " * "
		kanan = kanan + 1	
	bintang = bintang + "\n\n"
	bar = bar - 1
print (bintang)