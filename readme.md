Get Set User một tác vụ nào đó, Microservice, Spring Boot 21, Virtual Thread
+ demo + write a page *
  ++ CRUD API, layered service with applying knowledge tech *
  ++ use database PostgreSQL - OK
  ++ batch processing with kafka ( async ) (multi thread ? virtual thread ? ) -
  ++ Unit test to show how to test services - in progress
  ++ use AWS stack: SQS, S3
  ++ use Redis for caching , how and why to use, apply new advance knowledge technology -

=> show new values or not ?

Docker service
use: Redis, Database PostgresSQL, Kafka,
AWS deploy: cli deploy ECR, ECS với docker | EKS



Technical Stack
Microservices:

Programming language: Java 17
Backend Framework: Spring Boot 3
DB: PostgreSQL, MongoDB(Optional), Redis (optional)
Protocol: restful API, Kafka
IAM: Keycloak
Cloud: AWS


Start project User-Management
+ add common components:
  ++  RESTful APIs for user management (CRUD operations).
  ++  Validation for input data.
  ++ Logging and exception handling.
  ++ Integration with an in-memory database (H2).
  ++ Maven-based structure.
  ++ Health check support ( via endpoint /actuator/health)
  ++ GRPC APIs supoport for user management (CRUD operations) : optional.
  ++



request mapping for Fallback?
https://www.baeldung.com/spring-requestmapping

add spring validation
add logging slf4j
add Controller (pageable), Service, Repository, exception handlers
add database postgre, init schema, seed data
add Unit Test


cd "C:\Users\hung.nguyen\IdeaProjects\indi\onboard\user-management"

git init
git config --global user.email "hung.nguyen@itech.asia"
git config --global user.name "Hung Nguyen"
git remote add <name> <url>



git add .
git commit -m 'init project'


    git push <name>



docker run -d -p [host_port]:[container_port] --name [container_name]

cd /c/Users/hung.nguyen/IdeaProjects/indi/onboard/user-management/
docker build --tag=user-management:latest .

docker run -d -p 8080:8080 --name user-management user-management:latest

cd docker
docker compose up -d

Add logging, tracing: Zipkin, Jaeger, OpenTelemetry
Add monitoring with Prometheus, Grafana

Add Redis
https://www.baeldung.com/spring-boot-redis-cache
https://www.baeldung.com/java-serial-version-uid

Key: "user:777:posts:1001:comments:77"
Value: { comment details }

$ npm install -g redis-commander
$ redis-commander --redis-host localhost --redis-port 6379 --redis-password eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81 -p 8189

Redis UI:
localhost:8189


Add Kafka consumer
https://docs.spring.io/spring-kafka/reference/kafka/configuring-topics.html

apply custom consumer
consumer-tuning
https://strimzi.io/blog/2021/01/07/consumer-tuning/

Kafka UI
localhost:8188

kafka docker version 3.8.1
https://github.com/thingsboard/thingsboard.github.io/blob/9dd8be683fec0b9df6dee442679482a1f71d8c91/_includes/templates/install/docker-queue-kafka.md?plain=1#L18

kafka cluster settings: version 3.9
https://github.com/bitnami/containers/blob/main/bitnami/kafka/docker-compose-cluster.yml


monitoring:
http://localhost:8080/actuator

ref:
https://github.com/nlinhvu/hello-service/blob/main/docker-compose.yml

https://github.com/blueswen/spring-boot-observability
Observability:
+ Traces: tempo
+ Metrics: Prometheus
+ Logs: Loki
+ Visualization: Grafana


-javaagent:libs/opentelemetry-javaagent.jar

Grafana UI
http://localhost:3000/


Monitoring from Hello-Service
cd "C:\Users\hung.nguyen\IdeaProjects\indi\youtube\hello-service"


----
https://mydeveloperplanet.com/2021/09/07/how-to-deploy-a-spring-boot-app-on-aws-ecs-cluster/

ECR
548162446770.dkr.ecr.us-east-1.amazonaws.com/hnqitech/awsplanet


aws configure

login ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 548162446770.dkr.ecr.us-east-1.amazonaws.com


cd /c/Users/hung.nguyen/IdeaProjects/indi/onboard/user-management/
docker build -t hnqitech/awsplanet .

tag image
docker tag hnqitech/awsplanet:latest 548162446770.dkr.ecr.us-east-1.amazonaws.com/hnqitech/awsplanet:latest

push image
docker push 548162446770.dkr.ecr.us-east-1.amazonaws.com/hnqitech/awsplanet:latest


ecr repositories
https://us-east-1.console.aws.amazon.com/ecr/repositories/private/548162446770/hnqitech/awsplanet?region=us-east-1

ECS
https://us-east-1.console.aws.amazon.com/ecs/v2/clusters/DevCluster01/services?region=us-east-1


Amazon Elastic Container Service (ECS) with a Load Balancer | AWS Tutorial with New ECS Experience
https://www.youtube.com/watch?v=rUgZNXKbsrY

ApplicationLoadBalancerSecurityGroup

ContainerFromALBSecurityGroup



monitoring: with prometheus, grafana, alert, mailhog
Monitoring and Metrics for Spring | with Prometheus - Grafana - Actuator
https://www.youtube.com/watch?v=_WdIlz33FKE
(good project: automation test, test container, monitoring)
repo: /c/Users/hung.nguyen/IdeaProjects/indi/youtube/spring-social-2-cloud




+ add aws service SQS, Parameter store, S3

https://howtodoinjava.com/spring-cloud/aws-sqs-with-spring-cloud-aws/

SQS Queue:
bulk-user-sqs
https://sqs.us-east-1.amazonaws.com/548162446770/bulk-user-sqs





docker to connect external host
host.docker.internal:8080

cd /c/Users/hung.nguyen/IdeaProjects/indi/onboard/user-management/

cd docker
docker compose -f monitoring.yml up -d


cd ..
docker build --tag=user-management:latest .

docker compose up -d


optimize docker
eclipse-temurin:21-jre-alpine-3.21

1:46 PM 2/20/2025

flow event

next steps:
+ document: - OK
+ push to github - OK
+ complete : monitoring, add table, centralize logging, tracing

