#!/bin/bash
echo "Creating DynamoDB tables..."
awslocal dynamodb create-table \
    --table-name Product \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

echo "Adding sample data..."
awslocal dynamodb put-item \
    --table-name Product \
    --item '{"id": {"S": "1"}, "name": {"S": "Example Product"}, "description": {"S": "This is a sample product"}, "price": {"N": "19.99"}}'

