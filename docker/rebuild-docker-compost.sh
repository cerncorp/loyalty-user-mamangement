
docker compose down

cd ..
docker build --tag=user-management:latest .

cd docker
docker compose up -d