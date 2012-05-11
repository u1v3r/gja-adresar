CC = ant

build: 
	cd ext/;
	wget `cat README | grep http`;
	unzip libs.zip -d ext/;
	rm libs.zip;
	cd ../;
	ant

clean: 
	$(CC) clean
