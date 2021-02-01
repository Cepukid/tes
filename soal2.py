bintang = ""
x = int(input("Masukkan angka :"))
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
	bintang = bintang + "\n\n"
	bar = bar - 1
bar = 1	
while bar <= x:
	kol = bar+1
	while kol > 1:
		bintang = bintang + "   "
		kol = kol - 1	
	kiri = 0
	while kiri < (x - bar):
		bintang = bintang + " * "
		kiri = kiri + 1	
	bintang = bintang + "\n\n"
	bar = bar + 1
print (bintang)