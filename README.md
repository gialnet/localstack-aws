LocalStack AWS SQS and DynamoDB

Spring Boot 3.4.5 App java 21
Maven project

> Example use of Localstack to test development with Amazon AWS in your local machine for SQS and DynamoDB services
> The configuration is setup to work with two profiles "local" and "production"
> They are two scripts in the folder to create a queue and send a message to it
> and another to create a table and insert a record in it
>

## Install LocalStack
They are several ways to install the environment the easer way is with docker cli command

```
bash
docker run --rm -it -p 4566:4566 -p 4510-4559:4510-4559 localstack/localstack
```

```
python3 -m pip install --upgrade localstack
```

Environment vars to run AWS commands
```
bash
$env:AWS_ACCESS_KEY_ID="test"
$env:AWS_SECRET_ACCESS_KEY="test"
$env:AWS_DEFAULT_REGION="us-east-1"
```

Windows check status
```
Invoke-WebRequest -Uri http://localhost:4566/_localstack/info | ConvertFrom-Json
```

## Two ways to interact through cli console

awslocal sqs create-queue --queue-name mi-cola-aperez

or

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name mi-cola-aperez

aws --endpoint-url=http://localhost:4566 sqs list-queues

### awslocal is a Python utility to install is necessary to have  Phyton 3 and pip

```
pip install awscli-local
````

aws s3 mb s3://my-first-bucket --endpoint-url=http://localhost:4566

aws s3 mb s3://test --profile localstack
aws s3 ls --profile localstack

awslocal sqs create-queue --queue-name mi-cola-test
awslocal sqs list-queues


awslocal sqs send-message --queue-url http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/mi-cola-test --message-body "Hello World"

docker run --rm -it -p 127.0.0.1:4566:4566 -p 127.0.0.1:4510-4559:4510-4559 -v /var/run/docker.sock:/var/run/docker.sock localstack/localstack
podman run --rm -it -p 127.0.0.1:4566:4566 -p 127.0.0.1:4510-4559:4510-4559 -v /var/run/docker.sock:/var/run/docker.sock localstack/localstack

awslocal dynamodb list-tables --region us-east-1




awslocal sqs create-queue --queue-name mi-cola-test
awslocal sqs list-queues


awslocal sqs send-message --queue-url http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/mi-cola-test --message-body "Hello World"

docker run --rm -it -p 127.0.0.1:4566:4566 -p 127.0.0.1:4510-4559:4510-4559 -v /var/run/docker.sock:/var/run/docker.sock localstack/localstack
podman run --rm -it -p 127.0.0.1:4566:4566 -p 127.0.0.1:4510-4559:4510-4559 -v /var/run/docker.sock:/var/run/docker.sock localstack/localstack

awslocal dynamodb list-tables --region us-east-1