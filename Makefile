.PHONY: migrate clean info validate baseline repair new-migration

migrate:
	./mvnw flyway:migrate

clean:
	./mvnw flyway:clean

info:
	./mvnw flyway:info

validate:
	./mvnw flyway:validate

baseline:
	./mvnw flyway:baseline

repair:
	./mvnw flyway:repair

seed:
	./mvnw spring-boot:run -Dspring-boot.run.arguments=--seed -Dspring-boot.run.profiles=dev

new-migration:
	@echo "Creating new migration file..."
	@TIMESTAMP=$$(date +%Y%m%d%H%M%S) && \
	FILENAME="src/main/resources/db/migration/V$${TIMESTAMP}__${NAME}.sql" && \
	touch $$FILENAME && \
	echo "-- Migration: ${NAME}" > $$FILENAME && \
	echo "Created migration file $$FILENAME"
