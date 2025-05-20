LocalStack AWS SQS and DynamoDB

Spring Boot 3.4.5 App java 21
Maven project

> Example use of Localstack to test development with Amazon AWS in your local machine for SQS and DynamoDB services
> The configuration is setup to work with two profiles "local" and "production"
> They are two scripts in the folder to create a queue and send a message to it
> and another to create a table and insert a record in it
>


awslocal sqs create-queue --queue-name mi-cola-test
awslocal sqs list-queues


awslocal sqs send-message --queue-url http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/mi-cola-test --message-body "Hello World"

docker run --rm -it -p 127.0.0.1:4566:4566 -p 127.0.0.1:4510-4559:4510-4559 -v /var/run/docker.sock:/var/run/docker.sock localstack/localstack
podman run --rm -it -p 127.0.0.1:4566:4566 -p 127.0.0.1:4510-4559:4510-4559 -v /var/run/docker.sock:/var/run/docker.sock localstack/localstack

awslocal dynamodb list-tables --region us-east-1