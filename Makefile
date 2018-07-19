IMAGES:=nginx
images:
	for image in $(IMAGES); do \
		cd $$image; \
		docker build . -t johtani/apm-demo-$$image; \
		cd -; \
	done

push:
	for image in $(IMAGES); do \
		docker push johtani/apm-demo-$$image; \
	done
